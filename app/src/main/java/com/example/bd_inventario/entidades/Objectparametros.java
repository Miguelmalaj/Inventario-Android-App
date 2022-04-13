package com.example.bd_inventario.entidades;

//CLASE PARA ENVIAR EL objeto o json
//ES EL BODY DE LA PETICION POST
public class Objectparametros {
    int Empresa;
    int Sucursal;
    int Id_usuario;

    public Objectparametros(int empresa, int sucursal, int id_usuario) {
        Empresa = empresa;
        Sucursal = sucursal;
        Id_usuario = id_usuario;
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

    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        Id_usuario = id_usuario;
    }
}
