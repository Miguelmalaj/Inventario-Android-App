package com.example.bd_inventario;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFecha;
    Button btnSalir;
    EditText txtFecha;
    Spinner spubicacion;
    Button btnguardar;
    Button btnscan;
    EditText txtVin;
    private int dia, mes, anio;
    private Spinner spubica;
    //variable global para selección de ubicación
    String ubication_selected;
    consultas_db dbstart;
    boolean registrosEntabla;


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

        btnSalir = findViewById(R.id.btnSalir);

        //llenamos tablas usuarios, ubicaciones y empresas
        dbstart = new consultas_db(this, "Inventarios", null, 1);
        registrosEntabla = dbstart.ExisteRegistrosEnTabla();

        if(!registrosEntabla){
            Log.d("REGISTRAMOS TABLAS:==", "REGISTRAMOS TABLAS.");
            dbstart.RegistrarUsuarios();
            dbstart.RegistrarEmpresas();
            dbstart.RegistrarUbicaciones();
        }
            Log.d("NO REGISTRAMOS TABLAS:==", "YA EXISTEN REGISTROS.");


        String[] opciones = {"Transito", "Patio", "Piso", "E1", "E2", "E3", "Taller", "HyP", "Seminuevos", "Previas", "Esquina Rio Fte",
                "Culican", "Guasave", "Otro distribuidor"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, opciones);

        spubica.setAdapter(adapter);

        //método que está a la escucha de la ubicación seleccionada por el usuario
        spubica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubication_selected = adapterView.getSelectedItem().toString();
//                Log.d("VALOR SPINNER OnItemSelected: ",ubication_selected);

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

        //administracion es el nombre de la bd en SQlite
        consultas_db admin = new consultas_db(this, "Inventarios", null, 1);

        //POR AHORA ENVIAREMOS MANUALMENTE EL ID USUARIO, EMPRESA, SUCURSAL
        int Id_usuario = 1;
        int Empresa = 1;
        int Sucursal = 1;

        // Alta de variables para guardar en la base de datos
        String fecha_db = txtFecha.getText().toString();
        String ubicacion_db = ubication_selected;
        String Vin_db = txtVin.getText().toString();
        // ------ termina alta de variables para base de datos

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

}