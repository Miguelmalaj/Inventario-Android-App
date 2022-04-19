package com.example.bd_inventario.entidades;

public class responseRegistrosInventario {
    String mensaje;
    int estado;

    public responseRegistrosInventario(String mensaje, int estado) {
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
