package com.example.bd_inventario.response;

import com.example.bd_inventario.entidades.inventarioCreado;

public class responsePostInventario {
    inventarioCreado inventario;

    public responsePostInventario(inventarioCreado inventario) {
        this.inventario = inventario;
    }

    public inventarioCreado getInventario() {
        return inventario;
    }

    public void setInventario(inventarioCreado inventario) {
        this.inventario = inventario;
    }
}
