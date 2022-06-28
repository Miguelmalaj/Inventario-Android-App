package com.example.bd_inventario;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bd_inventario.Retrofit.Utilidades;
import com.example.bd_inventario.Retrofit.apiRest;
import com.example.bd_inventario.entidades.Objectparametros;
import com.example.bd_inventario.entidades.Usuarios;
import com.example.bd_inventario.entidades.listaInventario;
import com.example.bd_inventario.entidades.responseRegistrosInventario;
import com.example.bd_inventario.response.responseGetInventario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private apiRest mAPIService;
    private List<Usuarios> lista = new ArrayList<>();

    Button btnSalir;
    TextView txtDate;
    Button btnguardar;
    Button btnscan;
    Button btnSync;
    String ubicacionSpinner;
    String VinScaneado;

    EditText txtVin;
    private int dia, mes, anio;
    private Spinner spubica;
    String ubication_selected;
    Bundle bundleUsuario;
    Usuario userLogged;
    TextView nombreAgencia;
    int PERMI_REQ_CODE = 11;

    String[] permissions = {
            Manifest.permission.CAMERA
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkpermission();
        txtDate = findViewById(R.id.txtDate);
        spubica = findViewById(R.id.spubicacion);
        btnguardar = findViewById(R.id.btnguardar);
        btnscan = findViewById(R.id.btnScaner);
        txtVin = findViewById(R.id.txtVin);
        nombreAgencia = findViewById(R.id.txtAgencia);
        btnSalir = findViewById(R.id.btnSalir);
        btnSync = findViewById(R.id.btnSync);
        //objeto api rest
        mAPIService = Utilidades.getAPIService();
        setFecha();

        //bundleUsuario = getIntent().getExtras();
       /* if(bundleUsuario != null){
            userLogged = (Usuario)bundleUsuario.getSerializable("usuario");
        }*/
        userLogged = new Usuario(
                    getIntent().getStringExtra("Id_usuario"),
                    getIntent().getStringExtra("Nombre_usuario"),
                    getIntent().getStringExtra("Empresa"),
                    getIntent().getStringExtra("Sucursal")
        );

        setNombreAgencia(
                Integer.parseInt(userLogged.getEmpresa()),
                Integer.parseInt(userLogged.getSucursal())
        );

//        ubicacionSpinner = getIntent().getStringExtra("ubicacionSpinner");
        VinScaneado = getIntent().getStringExtra("valorVIN");
        setVinEscaneado(VinScaneado);
        ubicacionSpinner = setUbicacionSpinner(getIntent().getStringExtra("ubicacionSpinner"));


        List<Ubicaciones> listaUbicacionesUsuario = llenarUbicaciones();
        ArrayAdapter<Ubicaciones> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, listaUbicacionesUsuario);

        spubica.setAdapter(adapter);
        spubica.setSelection(Integer.parseInt(ubicacionSpinner));

        spubica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubication_selected = adapterView.getSelectedItem().toString();
                ubicacionSpinner = String.valueOf(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent datos = new Intent(MainActivity.this, ScannerActivity.class);
                datos.putExtra("Nombre_usuario", getIntent().getStringExtra("Nombre_usuario"));
                datos.putExtra("Empresa", getIntent().getStringExtra("Empresa"));
                datos.putExtra("Sucursal", getIntent().getStringExtra("Sucursal"));
                datos.putExtra("Id_usuario", getIntent().getStringExtra("Id_usuario"));
                datos.putExtra("ubicacionSpinner", ubicacionSpinner);
                startActivity(datos);

                /*IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - VIN");
                integrador.setCameraId(0);


                integrador.addExtra("MAX_HEIGHT", 100);
                integrador.addExtra("MAX_WIDTH", 100);

                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);

                integrador.initiateScan();*/
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnSalir.setEnabled(false);
                btnSync.setEnabled(false);
                btnguardar.setEnabled(false);

                btnSalir.setBackgroundColor(0xff778899); //color gris
                btnSync.setBackgroundColor(0xff778899);
                btnguardar.setBackgroundColor(0xff778899);

                //1.- Revisar conexion wifi
                //2.- Crear funcion para obtener todos los registros de inventarios de HOY.
                //3.- Obtener los registros (validar si es null)
                //4.- Crear modelo donde serán guardados todos los registros.
                //5.-

                if(!revisarConexion()){
                    btnguardar.setEnabled(true);
                    btnSync.setEnabled(true);
                    btnSalir.setEnabled(true);

                    btnSalir.setBackgroundColor(0xff33b5e5); //color azul
                    btnguardar.setBackgroundColor(0xff33b5e5);
                    btnSync.setBackgroundColor(0xff33b5e5);
                    Toast.makeText(MainActivity.this, "Favor de conectarse a la red antes de realizar la sincronización.", Toast.LENGTH_LONG).show();
                    return;

                }

                mAPIService.existeRegistrosDeHoy(new Objectparametros(Integer.parseInt(userLogged.getEmpresa()),Integer.parseInt(userLogged.getSucursal()),getFecha()))
                        .enqueue(new Callback<responseRegistrosInventario>() {
                            @Override
                            public void onResponse(Call<responseRegistrosInventario> call, Response<responseRegistrosInventario> response) {
                                Log.i("RESPUESTA=","200");

                                if(response.body().getEstado() == 2){ //server response 2 si no hay registros
                                    Log.i("RESPUESTA=",response.body().getMensaje());
                                    sincronizarPrimeraVez();
                                }

                                if(response.body().getEstado() == 1){ //server response 1 si hay registros
                                    Log.i("RESPUESTA=",response.body().getMensaje());
                                    EliminarRegistrosRemotos();
                                }

                            }

                            @Override
                            public void onFailure(Call<responseRegistrosInventario> call, Throwable t) {
                                Log.i("RESPUESTA=","500");
                                Toast.makeText(MainActivity.this, "Error al conectar con servidor, verificar conexión VPN" , Toast.LENGTH_LONG).show();
                                btnguardar.setEnabled(true);
                                btnSync.setEnabled(true);
                                btnSalir.setEnabled(true);
                                btnSalir.setBackgroundColor(0xff33b5e5); //color azul
                                btnguardar.setBackgroundColor(0xff33b5e5);
                                btnSync.setBackgroundColor(0xff33b5e5);
                            }
                        });

            }

        });
    }

    private String setUbicacionSpinner(String ubicacionSpinner) {
        if(ubicacionSpinner == null){
            ubicacionSpinner = String.valueOf(0);
        }
        return ubicacionSpinner;
    }

    private void setVinEscaneado(String vinScaneado) {
        if( VinScaneado != null){
            if (VinScaneado.length() > 17) {
                txtVin.setText(VinScaneado.substring(0, 17));
            } else {
                txtVin.setText(VinScaneado);
                Toast.makeText(MainActivity.this, "VIN con menos de 17 caracteres.", Toast.LENGTH_LONG).show();
            }

        }
    }

    private boolean checkpermission(){
        List<String> listPermissions = new ArrayList<>();
        for(String perm: permissions){
            if(ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED){
                listPermissions.add(perm);
            }
        }
        if(!listPermissions.isEmpty()){
            ActivityCompat.requestPermissions(this, listPermissions.toArray(new String[listPermissions.size()]), PERMI_REQ_CODE);
            return false;
        }
        return true;
    }

    private void EliminarRegistrosRemotos() {
        Log.i("RESPUESTA=","accedimos a metodo sincrnoizarActualizar");
        String cadena = userLogged.getEmpresa() + "-" +userLogged.getSucursal() + "-"+getFecha();

        mAPIService.eliminarRegistrosDeHoy(cadena)
                .enqueue(new Callback<responseRegistrosInventario>() {
                    @Override
                    public void onResponse(Call<responseRegistrosInventario> call, Response<responseRegistrosInventario> response) {
                        Toast.makeText(MainActivity.this, response.body().getMensaje() , Toast.LENGTH_LONG).show();
                        sincronizarPrimeraVez();
                    }

                    @Override
                    public void onFailure(Call<responseRegistrosInventario> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Ocurrió un error con el servidor: No fue posible sincronizar" , Toast.LENGTH_LONG).show();
                        btnguardar.setEnabled(true);
                        btnSync.setEnabled(true);
                        btnSalir.setEnabled(true);
                        btnSalir.setBackgroundColor(0xff33b5e5); //color azul
                        btnguardar.setBackgroundColor(0xff33b5e5);
                        btnSync.setBackgroundColor(0xff33b5e5);
                    }
                });

    }

    private void sincronizarPrimeraVez() {
        Log.i("RESPUESTA=", "accedimos a metodo sincrnoizarPrimeraVez");
        consultas_db queryLocal = new consultas_db(MainActivity.this, "Inventarios", null, 1);
        List<listaInventario> registrosInventarioHoy = queryLocal.getInventarioDeHoy(
                Integer.parseInt(userLogged.getEmpresa()),
                Integer.parseInt(userLogged.getSucursal()),
                Integer.parseInt(userLogged.getId_usuario()),
                getFecha().trim()
        );
        queryLocal.close();

        mAPIService
                .sincronizaInventario(registrosInventarioHoy)
                .enqueue(new Callback<responseRegistrosInventario>() {

                    @Override
                    public void onResponse(Call<responseRegistrosInventario> call, Response<responseRegistrosInventario> response) {
                        Log.i("RESPUESTA=","200");
                        Log.i("RESPUESTA=",response.body().getMensaje());

                        if(response.body().getEstado() == 2){ //server response 2 si no hay registros
                            btnguardar.setEnabled(true);
                            btnSync.setEnabled(true);
                            btnguardar.setBackgroundColor(0xff33b5e5);
                            btnSync.setBackgroundColor(0xff33b5e5);
                        }
                        Toast.makeText(MainActivity.this, response.body().getMensaje() , Toast.LENGTH_LONG).show();
                        btnSalir.setEnabled(true);
                        btnSalir.setBackgroundColor(0xff33b5e5);

                    }

                    @Override
                    public void onFailure(Call<responseRegistrosInventario> call, Throwable t) {
                        Log.i("RESPUESTA=","500");
                        Log.i("RESPUESTA=",t.getMessage());
                        Toast.makeText(MainActivity.this, "Ocurrió un error con el servidor: No fue posible sincronizar" , Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void setFecha() {
        Calendar cal = Calendar.getInstance();
        String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(cal.get(Calendar.YEAR));

        if(dia.length() == 1) dia = "0" + dia;
        if(mes.length() == 1) mes = "0" + mes;

        String fechaHoy = ("FECHA: "+dia + "-" + mes + "-" +anio);
        txtDate.setText(fechaHoy);

    }

    public String getFecha(){
        String fechaHoy = "";
        Calendar cal = Calendar.getInstance();
        String dia = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(cal.get(Calendar.YEAR));

        if(dia.length() == 1) dia = "0" + dia;
        if(mes.length() == 1) mes = "0" + mes;

        fechaHoy = (anio+ "-"+mes+"-"+dia);

        return fechaHoy;
    }

    public void sincronizarEntornoBD(){
        boolean registrosEnTablaInv = false;
        consultas_db sync = new consultas_db(this, "Inventarios", null, 1);
        registrosEnTablaInv = sync.ExisteRegistrosEnTablaInventario();

        if(!registrosEnTablaInv){
            Toast.makeText(this, "Debes sincronizar la BD.", Toast.LENGTH_LONG).show();
            /*
            * 0.- REVISAR SI HAY CONEXION A WIFI !!!!!!!!!!!!!!!!!!!!!
            * 1.- REALIZAR PETICION GET PARA OBTENER REGISTROS DEL USUARIO()
            * 2.- ACTUALIZAR BD LOCAL DE LOS DATOS DESCARGADOS DE LA BD REMOTA.
            * 3.- (paso 2) INSERTAR LOS REGISTROS DESCARGADOS DESDE BD REMOTA, EN CASO DE OBTENER REGISTROS DE BD REMOTA
            * */

            mAPIService.getInventarioAgencia(new Objectparametros(
                    Integer.parseInt(userLogged.getEmpresa()), //Empresa
                    Integer.parseInt(userLogged.getSucursal()), //Sucursal
                    getFecha()
//                    Integer.parseInt(userLogged.getId_usuario()) //Id usuario
            )).enqueue(new Callback<responseGetInventario>() {
                @Override
                public void onResponse(Call<responseGetInventario> call, Response<responseGetInventario> response) {
                    /*
                    * 1.CREAR UN METODO EN CONSULTAS_DB PARA REGISTRAR LOS ROWS OBTENIDOS DE BD REMOTO
                    *   1.1- ENVIAR EL OBJETO RESPONSE.BODY() - COMO PARAMETRO
                    *   1.2- DENTRO DE LA CONSULTA REALIZAR EL FOR EACH Y AGREGAR TODOS LOS REGISTROS
                    * */

                    //VALIDAR SI DATA ES NULL
                    //REGISTRAR DATA REMOTO
                    boolean registrados = sync.registroRemotoALocal(response.body());
                    if(registrados) Log.d("LOG:==","Se han registrados rows remoto-local");

                }

                @Override
                public void onFailure(Call<responseGetInventario> call, Throwable t) {
                    Log.d("pruebaREST", "faallo");
                }
            });


        }else{
            Toast.makeText(this, "La BD ya se encuentra actualizada.", Toast.LENGTH_LONG).show();
        }
    }

    public void cerrarSesion(){
        Intent log_out = new Intent(this,LoginActivity.class);
        startActivity(log_out);
    }

    @Override
    public void onClick(View view) {
    }

    // Método para guardar datos
    public void guardar() {
        //Inventarios es el nombre de la bd en SQlite

        consultas_db admin = new consultas_db(this, "Inventarios", null, 1);

        //POR AHORA ENVIAREMOS MANUALMENTE EL ID USUARIO, EMPRESA, SUCURSAL
        int Id_usuario = Integer.parseInt(userLogged.getId_usuario());
        int Empresa = Integer.parseInt(userLogged.getEmpresa());
        int Sucursal = Integer.parseInt(userLogged.getSucursal());

        // Alta de variables para guardar en la base de datos

        String fecha_db = getFecha();
        String ubicacion_db = ubication_selected;
        String Vin_db = txtVin.getText().toString();


        if ((!fecha_db.isEmpty()) && (!ubicacion_db.isEmpty()) && (!Vin_db.isEmpty())) {
            //1.- Validar si el VIN YA existe
            //1.1.- Si ya existe lo actualizamos
            //1.2.- Si no existe solo registramos
            //2.- Validar si el registro se realiza correctamente

                Long resultado_registro = admin.RegistrarInventario(
                        fecha_db,
                        ubicacion_db,
                        Vin_db,
                        Id_usuario,
                        Empresa,
                        Sucursal
                );

               if(resultado_registro != -1L){
                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();

               }else{
                int resultado_actualizar = admin.ActualizarInventario(
                        fecha_db,
                        ubicacion_db,
                        Vin_db,
                        Id_usuario,
                        Empresa,
                        Sucursal
                );

                if(resultado_actualizar != 1){
                    Toast.makeText(this, "No se logró actualizar el registro", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this, "El registro se actualizó en la BD", Toast.LENGTH_LONG).show();

               }

        } else {

            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
        }

        //limpiar campos
//        admin.close();

        txtVin.setText("");
    }

    // Método para escanear
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txtVin.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }*/

    private List<Ubicaciones> llenarUbicaciones(){
        int Empresa = Integer.parseInt(userLogged.getEmpresa().toString());
        int Sucursal = Integer.parseInt(userLogged.getSucursal().toString());

        List<Ubicaciones> listaUbi = new ArrayList<>();
        consultas_db queryUbi = new consultas_db(this, "Inventarios", null, 1);
        Cursor cursor = queryUbi.getUbicaciones(Empresa, Sucursal);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Ubicaciones ubi = new Ubicaciones();
                    ubi.setNombre_ubicacion(cursor.getString(0));
                    listaUbi.add(ubi);
                } while(cursor.moveToNext());
            }
        }
        queryUbi.close();
        return listaUbi;
    }

    public void setNombreAgencia(int Empresa, int Sucursal){
        consultas_db bdagencia = new consultas_db(this, "Inventarios", null, 1);

        String nomAgencia = bdagencia.getNombreAgencia(Empresa, Sucursal);
        nombreAgencia.setText(nomAgencia);
    }

    //método para verificar la conexión phone | wifi
    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public boolean revisarConexion(){
        if(checkNetworkConnection(this)) {
            return true;
        }else{
            return false;
        }
    }

}