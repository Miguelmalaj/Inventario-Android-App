package com.example.bd_inventario;
import java.io.Serializable;

public class Usuario implements Serializable{
    private String nombre;
    private String clave;
    private String empresa;
    private String sucursal;
    private String id_usuario;
    private String auditor;

    public Usuario() {

    }

    public Usuario(String id_usuario, String nombre, String empresa, String sucursal, String auditor){
        this.nombre = nombre;
        this.empresa = empresa;
        this.sucursal = sucursal;
        this.id_usuario = id_usuario;
        this.auditor = auditor;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
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
