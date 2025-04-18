package com.example.tap2025.Modelos;

public class ProductoDAO {
    private int idProducto;
    private String nomProd;
    private float precioProd;
    private float costoProd;
    private String UrlImagenProd;
    private int idCategoria;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public float getCostoProd() {
        return costoProd;
    }

    public void setCostoProd(float costoProd) {
        this.costoProd = costoProd;
    }

    public String getUrlImagenProd() {
        return UrlImagenProd;
    }

    public void setUrlImagenProd(String urlImagenProd) {
        UrlImagenProd = urlImagenProd;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
