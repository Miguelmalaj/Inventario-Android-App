package com.example.bd_inventario.Retrofit;

import com.example.bd_inventario.entidades.Usuarios;
import com.example.bd_inventario.entidades.UsuariosEnviados;
import com.example.bd_inventario.response.responseGetUsuarios;
import com.example.bd_inventario.response.responsePostUsuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiRest {

   @GET("api/usuarios/")
   Call<responseGetUsuarios> obtenerUsuarios();
//   public Call<List<Usuarios>> leerTodo();

   @POST("api/usuarios/")
   Call<responsePostUsuarios> agregarUsuarios(@Body UsuariosEnviados usuarios);

}
