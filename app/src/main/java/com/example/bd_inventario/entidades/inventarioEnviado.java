package com.example.bd_inventario.entidades;

//CLASE PARA ENVIAR EL CUERPO DE LA PETICION O (JSON)
//ES EL BODY DE LA PETICION POST
public class inventarioEnviado {
    String VIN;
    String Id_fecha;
    String Nombre_ubicacion;
    int Empresa;
    int Sucursal;
    int Id_usuario;

    public inventarioEnviado(String VIN, String id_fecha, String nombre_ubicacion, int empresa, int sucursal, int id_usuario) {
        this.VIN = VIN;
        Id_fecha = id_fecha;
        Nombre_ubicacion = nombre_ubicacion;
        Empresa = empresa;
        Sucursal = sucursal;
        Id_usuario = id_usuario;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getId_fecha() {
        return Id_fecha;
    }

    public void setId_fecha(String id_fecha) {
        Id_fecha = id_fecha;
    }

    public String getNombre_ubicacion() {
        return Nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        Nombre_ubicacion = nombre_ubicacion;
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
