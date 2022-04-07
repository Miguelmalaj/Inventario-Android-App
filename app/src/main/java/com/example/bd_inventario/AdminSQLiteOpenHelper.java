package com.example.bd_inventario;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL(
                "create table Inventario(" +
                        "Id_inventario integer primary key autoincrement, " +
                        "VIN text unique, " +
                        "Id_fecha text, " +
                        "Nombre_ubicacion text, " +
                        "Empresa integer, "+
                        "Sucursal integer, "+
                        "Id_usuario integer)"
        );

        //crear tabla usuarios
          BaseDeDatos.execSQL(
            "create table Usuarios(" +
                        "Id_usuario integer primary key autoincrement, " +
                        "Nombre_usuario text unique, " +
                        "Empresa integer, " +
                        "Sucursal integer," +
                        "Clave text)"
          );


         //crear tabla Empresas
          BaseDeDatos.execSQL(
            "create table Empresas(" +
                        "Empresa integer, " +
                        "Sucursal integer, " +
                        "Nombre text)"
          );

          /*
          //crear tabla Ubicaciones
          BaseDeDatos.execSQL(
            "create table Ubicaciones(" +
                        "Id_ubicacion integer primary key autoincrement, " +
                        "Nombre_ubicacion text unique," +
                        "Empresa integer, " +
                        "Sucursal integer)"
          );*/

    }

    /*public void AgregarNuevoRegistro(String fecha_db, String ubicacion_db, String Vin_db){

        Log.d("prueba: ","prueba");
        Log.d("fecha_db: ",fecha_db);
        Log.d("ubicacion_db: ",ubicacion_db);
        Log.d("Vin_db: ", Vin_db);
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
