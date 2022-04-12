package com.example.bd_inventario.Retrofit;

public class Utilidades {
    //url general de la api, cambiará cuando se monte al server
    public static final String BASE_URL = "http://10.10.10.52:3000/";

    //metodo para retornar el cliente retrofit
    public static apiRest getAPIService(){
        return retrofitCliente.getCliente(BASE_URL).create(apiRest.class);
    }
}
