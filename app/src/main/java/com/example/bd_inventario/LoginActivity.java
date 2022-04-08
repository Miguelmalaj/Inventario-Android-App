package com.example.bd_inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    Button btnIniciar;
    TextView txtLogin;
    consultas_db dbstart;
    boolean registrosEntabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIniciar = findViewById(R.id.btnIniciar);
        txtLogin = findViewById(R.id.txtLogin);

            //validar si existe la Bd..
            //toast enviar
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
                IniciarSesion();
            }
        });

    }

    public void IniciarSesion(){
        Intent log_in = new Intent(this,MainActivity.class);
        startActivity(log_in);
    }



}