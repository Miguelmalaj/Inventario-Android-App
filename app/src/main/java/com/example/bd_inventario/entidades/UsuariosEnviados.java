package com.example.bd_inventario.entidades;

//CLASE PARA ENVIAR EL CUERPO DE LA PETICION O (JSON)
//ES EL BODY DE LA PETICION POST
public class UsuariosEnviados {
    String nombre_usuario;
    String clave;
    int tipo_usuario;

    public UsuariosEnviados(String nombre_usuario, String clave, int tipo_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
        this.tipo_usuario = tipo_usuario;
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
