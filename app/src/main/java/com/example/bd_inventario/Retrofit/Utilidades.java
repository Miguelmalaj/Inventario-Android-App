package com.example.bd_inventario.Retrofit;

public class Utilidades {
    public static final String BASE_URL = "http://10.10.10.52:3000/";
//    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static apiRest getAPIService(){
        return retrofitCliente.getCliente(BASE_URL).create(apiRest.class);
    }
}
