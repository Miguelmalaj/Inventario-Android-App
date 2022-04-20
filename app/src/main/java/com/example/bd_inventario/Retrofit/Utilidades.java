package com.example.bd_inventario.Retrofit;

public class Utilidades {
    //url general de la api, cambiará cuando se monte al server
//    public static final String BASE_URL = "http://10.10.10.52:4001/"; //local: trabajo /fisico /emulador
    public static final String BASE_URL = "http://10.10.10.100:4001/"; //en servidor
//    public static final String BASE_URL = "http://192.168.0.13:3000/"; //casa




    //metodo para retornar el cliente retrofit
    public static apiRest getAPIService(){
        return retrofitCliente.getCliente(BASE_URL).create(apiRest.class);
    }
}
