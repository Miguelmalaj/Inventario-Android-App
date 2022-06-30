package com.example.bd_inventario.entidades;

//CLASE PARA ENVIAR EL objeto o json
//ES EL BODY DE LA PETICION POST
public class Objectparametros {
    int Empresa;
    int Sucursal;
    String Id_fecha;
    String Auditor;

    public Objectparametros(int empresa, int sucursal, String id_fecha, String auditor) {
        Empresa = empresa;
        Sucursal = sucursal;
        Id_fecha = id_fecha;
        Auditor = auditor;
    }

    public int getEmpresa() {
        return Empresa;
    }
    public void setEmpresa(int empresa) {
        Empresa = empresa;
    }

    public String getAuditor() {
        return Auditor;
    }
    public void setAuditor(String auditor) {
        Auditor = auditor;
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
