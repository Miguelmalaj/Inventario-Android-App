package com.example.bd_inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    Button btnIniciar;
    TextView txtLogin;
    EditText edtxtNombre;
    EditText edtxtClave;
    consultas_db dbstart;
    boolean registrosEntabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIniciar = findViewById(R.id.btnIniciar);
        txtLogin = findViewById(R.id.txtLogin);
        edtxtNombre = findViewById(R.id.edtxtNombre);
        edtxtClave = findViewById(R.id.edtxtClave);

        //validar si existe la Bd
        //crear variable, verificar con variable,
        dbstart = new consultas_db(this, "Inventarios", null, 1);
        registrosEntabla = dbstart.ExisteRegistrosEnTabla();
        if(!registrosEntabla){
            //creamos registros en tablas usuarios, ubicaciones y empresas
            dbstart.RegistrarUsuarios();
            dbstart.RegistrarEmpresas();
            dbstart.RegistrarUbicaciones();
            Toast.makeText(this, "Las tablas han sido creadas en BD correctamente.", Toast.LENGTH_LONG).show();
        }

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    PruebaInicio();
//                IniciarSesion();
            }
        });

    }

    public void IniciarSesion(){
        Intent log_in = new Intent(this,MainActivity.class);
        startActivity(log_in);
    }

    public void PruebaInicio(){
        String nombre = edtxtNombre.getText().toString();
        String clave = edtxtClave.getText().toString();
        /*Log.d("Nombre:==",String.valueOf(nombre));
        Log.d("Cllave:==",String.valueOf(clave));*/

        if(nombre.length() == 0) Toast.makeText(this, "Debes ingresar un nombre de usuario", Toast.LENGTH_LONG).show();
        if(clave.length() == 0) Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
        if(nombre.length() != 0  && clave.length() != 0){
            Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_LONG).show();
            boolean existeusuarioBD = dbstart.autenticar(nombre.trim(), clave.trim());
            if(!existeusuarioBD){
                Toast.makeText(this, "Las credenciales no son correctas", Toast.LENGTH_LONG).show();
            return;
            }
            //si existen las credenciales en la bd, enviamos a la pantalla princiapl
            Intent log_in = new Intent(this,MainActivity.class);
            startActivity(log_in);
        }

    }



}