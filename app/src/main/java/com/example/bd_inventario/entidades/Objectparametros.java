package com.example.bd_inventario.entidades;

//CLASE PARA ENVIAR EL objeto o json
//ES EL BODY DE LA PETICION POST
public class Objectparametros {
    int Empresa;
    int Sucursal;
    String Id_fecha;

    public Objectparametros(int empresa, int sucursal, String id_fecha) {
        Empresa = empresa;
        Sucursal = sucursal;
        Id_fecha = id_fecha;
    }

    public int getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(int empresa) {
        Empresa = empresa;
    }

    public int getSucursal() {
        return Sucursal;
    }

    public void setSucursal(int sucursal) {
        Sucursal = sucursal;
    }

    public String getId_fecha() {
        return Id_fecha;
    }

    public void setId_fecha(String id_fecha) {
        Id_fecha = id_fecha;
    }
}
