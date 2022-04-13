package com.example.bd_inventario.entidades;

//CLASE PARA RECIBIR LA RESPUESTA DEL SERVER CUANDO
public class inventarioCreado {
    String VIN;

    public inventarioCreado(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
}
