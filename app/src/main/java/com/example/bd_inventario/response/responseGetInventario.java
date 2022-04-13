package com.example.bd_inventario.response;

import com.example.bd_inventario.entidades.listaInventario;

import java.util.List;

public class responseGetInventario {
    List<listaInventario> inventario;

    public responseGetInventario(List<listaInventario> inventario) {
        this.inventario = inventario;
    }

    public List<listaInventario> getInventario() {
        return inventario;
    }

    public void setInventario(List<listaInventario> inventario) {
        this.inventario = inventario;
    }
}
