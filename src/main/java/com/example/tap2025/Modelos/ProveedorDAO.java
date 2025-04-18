package com.example.tap2025.Modelos;

public class ProveedorDAO {
    private int idProveedor;
    private String nomProv;
    private String descripcionProv;
    private String emailProv;
    private String direccionProv;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProv() {
        return nomProv;
    }

    public void setNomProv(String nomProv) {
        this.nomProv = nomProv;
    }

    public String getDescripcionProv() {
        return descripcionProv;
    }

    public void setDescripcionProv(String descripcionProv) {
        this.descripcionProv = descripcionProv;
    }

    public String getEmailProv() {
        return emailProv;
    }

    public void setEmailProv(String emailProv) {
        this.emailProv = emailProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }
}
