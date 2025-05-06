package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.ProductoDAO;

public class ProductoOrden extends ProductoDAO {
    int cantidad;
    public ProductoOrden(){
        super();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}