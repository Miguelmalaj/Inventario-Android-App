package com.example.bd_inventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class consultas_db extends AdminSQLiteOpenHelper{
    /*Map<String,String> usuarios = new HashMap<String,String>();
    Map*/

    public consultas_db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Long RegistrarInventario(
            String fecha_db,
            String ubicacion_db,
            String Vin_db,
            int Id_usuario,
            int Empresa,
            int Sucursal
    ){
        Long resultBd = -1L;
        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("VIN", Vin_db);
            registro.put("Id_fecha", fecha_db);
            registro.put("Nombre_ubicacion", ubicacion_db);
            registro.put("Empresa", Empresa);
            registro.put("Sucursal", Sucursal);
            registro.put("Id_usuario", Id_usuario);
            resultBd = bd.insert("Inventario", null, registro);
//            Log.d("RESULTADO REGISTRO: ",resultBd.toString());
            bd.close();
            return resultBd;

        }catch(SQLiteException e){
            Log.d("ENTRO EN CATCH: ","ENTRAMOS AL CATCH");

            return resultBd;

        }
    }

    public void RegistrarUsuarios(){
//        Log.d("Registrar usuarios:==", "tabla usuarios creada.");
        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues registro = new ContentValues();

            //primer usuario mochis
            registro.put("Nombre_usuario", "inventariomochis");
            registro.put("Empresa", 1);
            registro.put("Sucursal", 1);
            registro.put("Clave", "invmochis1");
            bd.insert("Usuarios",null,registro);

            //segundo usuario guasave
            registro.put("Nombre_usuario", "inventarioguasave");
            registro.put("Empresa", 3);
            registro.put("Sucursal", 1);
            registro.put("Clave", "invguasave2");
            bd.insert("Usuarios",null,registro);//segundo usuario guasave

            //tercer usuario zapata
            registro.put("Nombre_usuario", "inventariozap");
            registro.put("Empresa", 5);
            registro.put("Sucursal", 1);
            registro.put("Clave", "invzapata3");
            bd.insert("Usuarios",null,registro);//segundo usuario guasave

            //cuarto usuario aeropuerto
            registro.put("Nombre_usuario", "inventarioaero");
            registro.put("Empresa", 5);
            registro.put("Sucursal", 2);
            registro.put("Clave", "invaero4");
            bd.insert("Usuarios",null,registro);//segundo usuario guasave

            //quinto usuario cadillac
            registro.put("Nombre_usuario", "inventariocad");
            registro.put("Empresa", 7);
            registro.put("Sucursal", 1);
            registro.put("Clave", "invcad5");
            bd.insert("Usuarios",null,registro);

            bd.close();

        }catch (SQLiteException e){
            Log.d("CATCH EXCEPTION USERS: ","exception threw");
        }
    }

    public void RegistrarEmpresas(){
        //Log.d("Registrar Empresas:==", "tabla empresas creada.");
        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues registro = new ContentValues();

            //agencia mochis
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            registro.put("Nombre","FELIX AUTOMOTORES");
            bd.insert("Empresas", null, registro);

            //agencia guasave
            registro.put("Empresa",3);
            registro.put("Sucursal",1);
            registro.put("Nombre","FELIX AUTOMOTRIZ");
            bd.insert("Empresas", null, registro);

            //agencia zapata
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            registro.put("Nombre","CULIACAN MOTORS ZAPATA");
            bd.insert("Empresas", null, registro);

            //agencia aeropuerto
            registro.put("Empresa",5);
            registro.put("Sucursal",2);
            registro.put("Nombre","CULIACAN MOTORS AEROPUERTO");
            bd.insert("Empresas", null, registro);

            //agencia cadillac
            registro.put("Empresa",7);
            registro.put("Sucursal",1);
            registro.put("Nombre","NOROESTE MOTORS");
            bd.insert("Empresas", null, registro);

            bd.close();

        }catch(SQLiteException e){

        }


    }

    public void RegistrarUbicaciones(){
        Log.d("Registrar Ubicaciones:==", "tabla ubicaciones creada.");

    }

    public boolean ExisteRegistrosEnTabla(){
        boolean existeregistro = false;
        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            Cursor cursor = bd.rawQuery("SELECT * FROM Usuarios", null);
//            cursor.moveToFirst(); == se va al primer registro en caso de existencia
//            if(cursor.getCount() == cuenta el total de registros obtenidos
            if(cursor.moveToFirst()) existeregistro =true;
            bd.close();

        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return existeregistro;
    }

    public boolean autenticar(String nombreusuario, String clave){
        boolean existeusuario = false;
        try{
            SQLiteDatabase bd = this.getWritableDatabase();

            Cursor cursor = bd.rawQuery(
                    "SELECT Nombre_usuario, Clave FROM Usuarios WHERE Nombre_usuario='"+ nombreusuario +"' AND Clave='"+clave+"'",null
            );

            if(cursor.moveToFirst()) existeusuario = true;
            bd.close();


        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return existeusuario;
    }

    public void getDatosUsuarioBD(String nombreusuario, String clave){
        Usuario usuario;
        Bundle datosUsuario = new Bundle();
        boolean peticion = false;

        try{
            SQLiteDatabase bd = this.getWritableDatabase();

            Cursor cursor = bd.rawQuery(
                    "SELECT Nombre_usuario, Empresa, Sucursal FROM Usuarios WHERE Nombre_usuario='"+ nombreusuario +"' AND Clave='"+clave+"'",null
            );

            if(cursor.moveToFirst()){
                String Nombre_usuario = cursor.getString(0);
                String Empresa = cursor.getString(1);
                String Sucursal = cursor.getString(2);

                Log.d("Nombre_usuario:==",String.valueOf(Nombre_usuario));
                Log.d("Empresa:==",String.valueOf(Empresa));
                Log.d("Sucursal:==",String.valueOf(Sucursal));

                //1.-crear el objeto usuario
                //2.-poner el objeto usuario en el Bundle
                //3.-Retornar el bundle
                Usuario user = new Usuario(Nombre_usuario, Empresa, Sucursal);

            }
            bd.close();


        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

//        return datosUsuario;
    }

}
