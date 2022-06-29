package com.example.bd_inventario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    Button btnIniciar;
    TextView txtLogin;
    EditText edtxtNombre;
    EditText edtxtClave;
    consultas_db dbstart;
    boolean registrosEntabla;
    Usuario userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIniciar = findViewById(R.id.btnIniciar);
        txtLogin = findViewById(R.id.txtLogin);
        edtxtNombre = findViewById(R.id.edtxtNombre);
        edtxtClave = findViewById(R.id.edtxtClave);

        //validar si existe la Bd == COMPROBAMOS SI EL LA PRIMERA VEZ QUE SE INICIA LA APP
        dbstart = new consultas_db(this, "Inventarios", null, 1);
        registrosEntabla = dbstart.ExisteRegistrosEnTabla();

        if(!registrosEntabla){
            //creamos registros en tablas usuarios, ubicaciones y empresas
            dbstart.RegistrarUsuarios();
            dbstart.RegistrarEmpresas();
            dbstart.RegistrarUbicaciones();

            Toast.makeText(this, "BD local ha sido creada correctamente.", Toast.LENGTH_LONG).show();
        }

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    PruebaInicio();
                IniciarSesion();
            }
        });

    }

    public void IniciarSesion(){
        Bundle bundleUsuario = new Bundle();
        Usuario user = null;
        String nombre = edtxtNombre.getText().toString();
        String clave = edtxtClave.getText().toString();

        if(nombre.length() == 0) Toast.makeText(this, "Debes ingresar un nombre de usuario", Toast.LENGTH_LONG).show();
        if(clave.length() == 0) Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();

        if(nombre.length() != 0  && clave.length() != 0){

            Toast.makeText(this, "Credenciales correctas", Toast.LENGTH_LONG).show();
            boolean existeusuarioBD = dbstart.autenticar(nombre.trim(), clave.trim());
            if(!existeusuarioBD){
                Toast.makeText(this, "Las credenciales no son correctas", Toast.LENGTH_LONG).show();
                return;
            }

            bundleUsuario = dbstart.getDatosUsuarioBD(nombre.trim(), clave.trim());

            if(bundleUsuario != null){
                //usaremos las variables por separado porque se maneja la activity principal varias veces con el scanner
                userLogged = (Usuario)bundleUsuario.getSerializable("usuario");

                Intent log_in = new Intent(this,MainActivity.class);
//                log_in.putExtras(bundleUsuario);
                log_in.putExtra("Empresa", userLogged.getEmpresa().toString());
                log_in.putExtra("Sucursal", userLogged.getSucursal().toString());
                log_in.putExtra("Nombre_usuario", userLogged.getNombre());
                log_in.putExtra("Id_usuario", userLogged.getId_usuario());
                log_in.putExtra("Auditor", userLogged.getAuditor());

                startActivity(log_in);

            }
        }
    }

    public void PruebaInicio(){}



}