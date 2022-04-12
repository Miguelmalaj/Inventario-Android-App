package com.example.bd_inventario.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitCliente {

    private static Retrofit retrofit = null;

    public static Retrofit getCliente(String urlServidorRest){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(urlServidorRest)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
