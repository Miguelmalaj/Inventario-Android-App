package com.example.bd_inventario.entidades;

//CLASE PARA RECIBIR LA RESPUESTA DEL SERVER CUANDO UN USER ES CREADO ==SALIDA DE OPER
public class UsuariosCreados {
    String nombre_usuario;
    String clave;

    public UsuariosCreados(String nombre_usuario, String clave) {
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
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
}
