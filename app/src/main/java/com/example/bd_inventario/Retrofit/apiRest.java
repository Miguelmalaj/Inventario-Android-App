package com.example.bd_inventario.Retrofit;

import com.example.bd_inventario.entidades.Objectparametros;
import com.example.bd_inventario.entidades.Usuarios;
import com.example.bd_inventario.entidades.UsuariosEnviados;
import com.example.bd_inventario.entidades.inventarioEnviado;
import com.example.bd_inventario.entidades.listaInventario;
import com.example.bd_inventario.entidades.responseRegistrosInventario;
import com.example.bd_inventario.response.responseGetInventario;
import com.example.bd_inventario.response.responseGetUsuarios;
import com.example.bd_inventario.response.responsePostInventario;
import com.example.bd_inventario.response.responsePostUsuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface apiRest {

   @GET("api/usuarios/")
   Call<responseGetUsuarios> obtenerUsuarios();
//   public Call<List<Usuarios>> leerTodo();

   @POST("api/usuarios/")
   Call<responsePostUsuarios> agregarUsuarios(@Body UsuariosEnviados usuarios);

   //INVENTARIO endpoints
   @GET("api/inventario/")
   Call<responseGetInventario> obtenerInventario();

   @POST("api/inventario/")
   Call<responsePostInventario> agregarInventario(@Body inventarioEnviado inventario);

   //NUEVOS ENDPOINTS ==================================================
   //==================================================================
   //GET como POST INVENTARIO POR AGENCIA - USUARIO
   @POST("api/inventarioagenciaModificado")
   Call<responseGetInventario> getInventarioAgencia(@Body Objectparametros parBody);

   //prueba final
   @POST("api/invagencia")
   Call<responseRegistrosInventario> sincronizaInventario(@Body List<listaInventario> body);
//   Call<responseRegistrosInventario> sincronizaInventario(@Body listaInventario body);
//   Call<responseRegistrosInventario> sincronizaInventario(@Body responseGetInventario body);

   /*@PATCH("api/invagenciaupd")
   Call<responseRegistrosInventario> sincronizaActualizaInventario(@Body List<listaInventario> body);*/

   @DELETE("api/inveliminar/{id}")
   Call<responseRegistrosInventario> eliminarRegistrosDeHoy(@Path("id") String id);

   //valida si hay registros en la bd - hoy
   @POST("api/invexiste")
   Call<responseRegistrosInventario> existeRegistrosDeHoy(@Body Objectparametros body);

}
