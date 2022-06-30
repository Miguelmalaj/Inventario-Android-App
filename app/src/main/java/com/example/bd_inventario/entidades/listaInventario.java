package com.example.bd_inventario.entidades;

public class listaInventario {
    int Id_inventario;
    String VIN;
    String Id_fecha;
    String Nombre_ubicacion;
    int Empresa;
    int Sucursal;
    int Id_usuario;
    String Auditor;
    String QRCapturado;

    public listaInventario(int id_inventario, String VIN, String id_fecha, String nombre_ubicacion, int empresa, int sucursal, int id_usuario, String auditor, String qrCapturado) {
        Id_inventario = id_inventario;
        this.VIN = VIN;
        Id_fecha = id_fecha;
        Nombre_ubicacion = nombre_ubicacion;
        Empresa = empresa;
        Sucursal = sucursal;
        Id_usuario = id_usuario;
        Auditor = auditor;
        QRCapturado = qrCapturado;
    }

    public int getId_inventario() {
        return Id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        Id_inventario = id_inventario;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getAuditor() {
        return Auditor;
    }

    public void setAuditor(String auditor) {
        this.Auditor = auditor;
    }

    public String QRCapturado() {
        return QRCapturado;
    }

    public void setQRCapturado(String qrCapturado) {
        this.QRCapturado = qrCapturado;
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
