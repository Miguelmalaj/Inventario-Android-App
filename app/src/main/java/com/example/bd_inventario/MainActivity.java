package com.example.bd_inventario;

import static java.security.AccessController.getContext;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bd_inventario.Retrofit.Utilidades;
import com.example.bd_inventario.Retrofit.apiRest;
import com.example.bd_inventario.entidades.Objectparametros;
import com.example.bd_inventario.entidades.Usuarios;
import com.example.bd_inventario.entidades.UsuariosEnviados;
import com.example.bd_inventario.entidades.inventarioEnviado;
import com.example.bd_inventario.entidades.listaInventario;
import com.example.bd_inventario.response.responseGetInventario;
import com.example.bd_inventario.response.responseGetUsuarios;
import com.example.bd_inventario.response.responsePostInventario;
import com.example.bd_inventario.response.responsePostUsuarios;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private apiRest mAPIService;
    private List<Usuarios> lista = new ArrayList<>();

    Button btnFecha;
    Button btnSalir;
    EditText txtFecha;
//    Spinner spubicacion;
    Button btnguardar;
    Button btnscan;
    Button btnSync;

    EditText txtVin;
    private int dia, mes, anio;
    private Spinner spubica;
    //variable global para selección de ubicación
    String ubication_selected;
    Bundle bundleUsuario;
    Usuario userLogged;
    TextView nombreAgencia;



//    consultas_db dbstart;
//    boolean registrosEntabla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFecha = findViewById(R.id.btnFecha);
        txtFecha = findViewById(R.id.txtFecha);
        btnFecha.setOnClickListener(this);
        spubica = findViewById(R.id.spubicacion);
        btnguardar = findViewById(R.id.btnguardar);
        btnscan = findViewById(R.id.btnScaner);
        txtVin = findViewById(R.id.txtVin);
        nombreAgencia = findViewById(R.id.txtAgencia);

        btnSalir = findViewById(R.id.btnSalir);
        btnSync = findViewById(R.id.btnSync);
        bundleUsuario = getIntent().getExtras();
        //objeto api rest
        mAPIService = Utilidades.getAPIService();

        if(bundleUsuario != null){
            userLogged = (Usuario)bundleUsuario.getSerializable("usuario");

            //metodo para hacer la sincronización de bd local -remota.
            //sincronizarEntornoBD();
        }



        setNombreAgencia(Integer.parseInt(userLogged.getEmpresa().toString()), Integer.parseInt(userLogged.getSucursal().toString()));

        List<Ubicaciones> listaUbicacionesUsuario = llenarUbicaciones();

        ArrayAdapter<Ubicaciones> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, listaUbicacionesUsuario);

        spubica.setAdapter(adapter);

        //método que está a la escucha de la ubicación seleccionada por el usuario
        spubica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubication_selected = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - VIN");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
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

                //metodo GET INVENTARIO
                /*mAPIService.obtenerInventario().enqueue(new Callback<responseGetInventario>() {
                    @Override
                    public void onResponse(Call<responseGetInventario> call, Response<responseGetInventario> response) {
                        Gson objetoConsola = new Gson();
                        for (listaInventario objeto: response.body().getInventario()) {
//                            Log.i("pruebaREST", objetoConsola.toJson(objeto));
                            Log.d("pruebaREST", objetoConsola.toJson(objeto));

                        }


                    }

                    @Override
                    public void onFailure(Call<responseGetInventario> call, Throwable t) {
                        Log.d("pruebaREST", "faallo");
                    }
                });*/


                //METODO POST INVENTARIO
                mAPIService.agregarInventario(new inventarioEnviado("QQASDFF","2022-09-12","PISO",2,2,2))
                        .enqueue(new Callback<responsePostInventario>() {
                            @Override
                            public void onResponse(Call<responsePostInventario> call, Response<responsePostInventario> response) {
                                Gson objetoConsola = new Gson();
                                Log.i("POST=REST", objetoConsola.toJson(response.body()));

                            }

                            @Override
                            public void onFailure(Call<responsePostInventario> call, Throwable t) {
                                Log.i("POST=REST", "there was an error");
                            }
                        });




                /*
                METODO GET USUARIOS========================================================

                mAPIService.obtenerUsuarios().enqueue(new Callback<responseGetUsuarios>() {
                    @Override
                    public void onResponse(Call<responseGetUsuarios> call, Response<responseGetUsuarios> response) {

                        Gson objetoConsola = new Gson();
                        for (Usuarios objeto: response.body().getUsuarios()) {
                            Log.i("pruebaREST", objetoConsola.toJson(objeto.getId_usuario()));
//                            Log.d("pruebaREST", objetoConsola.toJson(objeto));

                        }
                    }

                    @Override
                    public void onFailure(Call<responseGetUsuarios> call, Throwable t) {
                        Log.d("pruebaREST", "faallo");
                    }
                });
                METODO GET USUARIOS========================================================

                */

                //METODO POST USUARIOS
                /*
                mAPIService.agregarUsuarios(new UsuariosEnviados("androiduser","12345",1))
                        .enqueue(new Callback<responsePostUsuarios>() {
                            @Overridegit 
                            public void onResponse(Call<responsePostUsuarios> call, Response<responsePostUsuarios> response) {
                                Gson objetoConsola = new Gson();
                                Log.i("POST=REST", objetoConsola.toJson(response.body()));

                            }

                            @Override
                            public void onFailure(Call<responsePostUsuarios> call, Throwable t) {
                                Log.i("POST=REST", "there was an error");
                            }
                        });
                 */





                /*  ULTIMA PRUEBA FUNCIONAL de otro tuto


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.10.10.52:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiRest apirest = retrofit.create(apiRest.class);

                Call<List<Usuarios>> rest = apirest.leerTodo();

                rest.enqueue(new Callback<List<Usuarios>>() {
                    @Override
                    public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                        lista.clear();
                        lista.addAll(response.body());

                        //PRUEBAS
                        Gson objetoConsola = new Gson();
                        for (Usuarios objeto: response.body()) {
                            Log.i("pruebaREST", objetoConsola.toJson(objeto.getClave() + objeto.getNombre_usuario()));

                        }
                        //PRUEBAS


                        Log.d("data leida", "data leida");
                        Log.d("data leida", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                        Log.d("data no leida", "data no leida");
                        Log.d("data no leida 1", t.getMessage());

                    }
                });*/



            }
        });
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
                    Integer.parseInt(userLogged.getId_usuario()) //Id usuario
            )).enqueue(new Callback<responseGetInventario>() {
                @Override
                public void onResponse(Call<responseGetInventario> call, Response<responseGetInventario> response) {
                    /*
                    * 1.CREAR UN METODO EN CONSULTAS_DB PARA REGISTRAR LOS ROWS OBTENIDOS DE BD REMOTO
                    *   1.1- ENVIAR EL OBJETO RESPONSE.BODY() - COMO PARAMETRO
                    *   1.2- DENTRO DE LA CONSULTA REALIZAR EL FOR EACH Y AGREGAR TODOS LOS REGISTROS
                    * */


                    /*Gson objetoConsola = new Gson();
                    for (listaInventario objeto: response.body().getInventario()) {
//                            Log.i("pruebaREST", objetoConsola.toJson(objeto));
                        Log.d("pruebaREST", objetoConsola.toJson(objeto.getVIN()));

                    }*/

                    //VALIDAR SI DATA ES NULL
                    //REGISTRAR DATA REMOTO
                    boolean registrados = sync.registroRemotoALocal(response.body());
                    if(registrados) Log.d("LOG:==","Se han registrados rows remoto-local");
//                    imprimirPrueba(response.body());

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

        Calendar cal = Calendar.getInstance();
        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH);
        anio = cal.get(Calendar.YEAR);
        //agregar cero a los días antes del 10 y a los meses antes del 10
        /*Log.d("DIA:==",String.valueOf(dia));*/

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //agregar cero a los días antes del 10 y mes
                month++;
                String convertday = String.valueOf(day);
                String convertmonth = String.valueOf(month);
                //String convertyear = String.valueOf(year);

                if(convertday.length() == 1) convertday = "0" + convertday;
                if(convertmonth.length() == 1) convertmonth = "0" + convertmonth;

                String fecha = (year + "-" + (convertmonth) + "-" + convertday);

                txtFecha.setText(fecha);

            }
        }, anio, mes, dia);
        dpd.show();

    }

    // Método para guardar datos
    public void guardar(View view) {
        //Inventarios es el nombre de la bd en SQlite
        consultas_db admin = new consultas_db(this, "Inventarios", null, 1);

        //POR AHORA ENVIAREMOS MANUALMENTE EL ID USUARIO, EMPRESA, SUCURSAL
        int Id_usuario = Integer.parseInt(userLogged.getId_usuario().toString());
        int Empresa = Integer.parseInt(userLogged.getEmpresa().toString());
        int Sucursal = Integer.parseInt(userLogged.getSucursal().toString());

        // Alta de variables para guardar en la base de datos
        String fecha_db = txtFecha.getText().toString();
        String ubicacion_db = ubication_selected;
        String Vin_db = txtVin.getText().toString();


        if ((!fecha_db.isEmpty()) && (!ubicacion_db.isEmpty()) && (!Vin_db.isEmpty())) {
                //validar si el registro se realiza correctamente
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
                Toast.makeText(this, "El registro ya existe en la BD", Toast.LENGTH_LONG).show();

               }

        } else {
            //  Log.d("no validado","no validado");
            Toast.makeText(this, "Favor de llenar todos los campos", Toast.LENGTH_LONG).show();
        }

        //limpiar campos
        txtFecha.setText("");
        txtVin.setText("");
    }

    // Método para escanear
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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

    }

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

    public void imprimirPrueba(responseGetInventario objecto){
        Toast.makeText(this, objecto.toString(), Toast.LENGTH_LONG).show();
            return;
    }

}