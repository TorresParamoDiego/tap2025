package com.example.tap2025.Modelos;

public class InsumoDAO {
    private int idInsumo;
    private String nomIns;
    private float precioIns;
    private int cantidad;
    private int idProveedor;

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNomIns() {
        return nomIns;
    }

    public void setNomIns(String nomIns) {
        this.nomIns = nomIns;
    }

    public float getPrecioIns() {
        return precioIns;
    }

    public void setPrecioIns(float precioIns) {
        this.precioIns = precioIns;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
}
