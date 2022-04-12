package com.example.bd_inventario.entidades;

public class Usuarios {
    int id_usuario;
    String nombre_usuario;
    String clave;
    int tipo_usuario;

    public Usuarios(int id_usuario, String nombre_usuario, String clave, int tipo_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
        this.tipo_usuario = tipo_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
