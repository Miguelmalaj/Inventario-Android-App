package com.example.bd_inventario.Retrofit;

import com.example.bd_inventario.entidades.Usuarios;
import com.example.bd_inventario.response.responseGetUsuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiRest {

   @GET("api/usuarios/")
//   @GET("comments/")
//   Call<responseGetUsuarios> obtenerUsuarios();
   public Call<List<Usuarios>> leerTodo();
}
