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

        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resultBd;
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
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void RegistrarEmpresas(){

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
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

    public void RegistrarUbicaciones(){
        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            ContentValues registro = new ContentValues();

            //UBICACIONES AGENCIA LOS MOCHIS
            registro.put("Nombre_ubicacion","PREVIAS");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","ESQUINA RIO FTE");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","SALA VENTAS");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","CULIACAN");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","GUASAVE");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","OTRO DISTRIBUIDOR");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-1");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-2");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-3");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-4");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-5");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1-6");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-1");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-2");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-3");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-4");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-5");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-6");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-7");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-8");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-9");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-10");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-11");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-12");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-13");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-14");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-14");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-15");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-16");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2-17");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-1A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-1B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-2A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-2B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-3A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-3B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-4A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-4B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-5A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-5B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-6A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-6B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-7A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-7B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-8A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-8B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-9A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-9B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-10A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-10B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-11A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-11B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-12A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-12B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-13A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-13B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-14A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-14A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","P-14B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-1A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-1B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-2A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-2B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-3A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-3B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-4A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-4B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-5A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","R-5B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-1A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-1B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-2A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-2B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-3A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-3B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-4A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-4B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-5A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-5B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-6A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-6B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-6B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-7A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-8A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-8B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-9A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-9B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-10A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-10B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-11A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-11B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-12A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-12B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-13A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-13B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-14A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-14B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-15A");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3-15B");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","PATIO");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","PISO");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","TRANSITO");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E1");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E2");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","E3");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","TALLER");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","HYP");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","SEMINUEVOS");
            registro.put("Empresa",1);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            //UBICACIONES AGENCIA ZAPATA
            registro.put("Nombre_ubicacion","ZAPATA EST SUPERIOR");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","TRANSITO AEROPUERTO");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","PATIO");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","ZAPATA PISO DE EXH");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","EXHIB PLAZA");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","TRANSITO ZAPATA");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","ZAPATA");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","AEROPUERTO");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","ZAPATA FLOT");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","ZAPATA PATIO");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","EN SEMINUEVOS ZAPATA");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);

            registro.put("Nombre_ubicacion","EN SEMINUEVOS AEROPUERTO");
            registro.put("Empresa",5);
            registro.put("Sucursal",1);
            bd.insert("Ubicaciones",null,registro);


            bd.close();

        }catch (SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

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

    public Bundle getDatosUsuarioBD(String nombreusuario, String clave){
        Usuario usuario;
        Bundle bundleUsuario = new Bundle();
//        boolean peticion = false;

        try{
            SQLiteDatabase bd = this.getWritableDatabase();

            Cursor cursor = bd.rawQuery(
                    "SELECT Id_usuario, Nombre_usuario, Empresa, Sucursal FROM Usuarios WHERE Nombre_usuario='"+ nombreusuario +"' AND Clave='"+clave+"'",null
            );

            if(cursor.moveToFirst()){
                String Id_usuario = cursor.getString(0);
                String Nombre_usuario = cursor.getString(1);
                String Empresa = cursor.getString(2);
                String Sucursal = cursor.getString(3);

                /*Log.d("Nombre_usuario:==",String.valueOf(Nombre_usuario));
                Log.d("Empresa:==",String.valueOf(Empresa));
                Log.d("Sucursal:==",String.valueOf(Sucursal));*/

                //1.-crear el objeto usuario
                //2.-poner el objeto usuario en el Bundle
                //3.-Retornar el bundle
                usuario = new Usuario(Id_usuario, Nombre_usuario, Empresa, Sucursal);
                bundleUsuario.putSerializable("usuario", usuario);

            }
            bd.close();


        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return bundleUsuario;
    }

    public Cursor getUbicaciones(int Empresa, int Sucursal){

        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            Cursor filas = bd.rawQuery(
                    "SELECT Nombre_ubicacion FROM Ubicaciones WHERE Empresa="+Empresa+" AND Sucursal="+Sucursal+"",null
            );

            if(filas.moveToFirst()){

                return filas;
            }else{

                return null;
            }

        }catch(SQLiteException e){

            return null;
        }

    }

    public String getNombreAgencia(int Empresa, int Sucursal){
        String agencia = "";

        try{
            SQLiteDatabase bd = this.getWritableDatabase();
            Cursor cursor = bd.rawQuery(
                    "SELECT Nombre FROM Empresas WHERE Empresa="+Empresa+" AND Sucursal="+Sucursal+"",null
            );

            if(cursor.moveToFirst()){
                agencia = cursor.getString(0);
            }
            bd.close();

        }catch(SQLiteException e){
            try {
                throw new IOException(e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return agencia;
    }

}
