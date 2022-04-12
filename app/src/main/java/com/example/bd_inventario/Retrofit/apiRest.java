package com.example.bd_inventario.Retrofit;

import com.example.bd_inventario.response.responseGetUsuarios;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiRest {

   @GET("usuarios")
   Call<responseGetUsuarios> obtenerUsuarios();
}
