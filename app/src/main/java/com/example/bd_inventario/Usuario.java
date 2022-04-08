package com.example.bd_inventario;
import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre;
    private String clave;
    private String empresa;
    private String sucursal;

    public Usuario() {

    }

    public Usuario(String nombre, String empresa, String sucursal){
        this.nombre = nombre;
        this.empresa = empresa;
        this.sucursal = sucursal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
