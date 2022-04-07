package com.example.bd_inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity{

    Button btnIniciar;
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIniciar = findViewById(R.id.btnIniciar);
        txtLogin = findViewById(R.id.txtLogin);

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