package com.example.bd_inventario.response;

import com.example.bd_inventario.entidades.UsuariosCreados;

public class responsePostUsuarios {
    UsuariosCreados usuarios;

    public responsePostUsuarios(UsuariosCreados usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosCreados getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(UsuariosCreados usuarios) {
        this.usuarios = usuarios;
    }
}
