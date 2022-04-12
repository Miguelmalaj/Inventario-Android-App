package com.example.bd_inventario.response;

import com.example.bd_inventario.entidades.Usuarios;

import java.util.List;

public class responseGetUsuarios {
    List<Usuarios> usuarios;

    public responseGetUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}
