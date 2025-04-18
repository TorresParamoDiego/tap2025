package com.example.tap2025.Modelos;

public class PuestoDAO {
    private int idPuesto;
    private String nomPuesto;
    private String descripcion;
    private float sueldoPuesto;

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNomPuesto() {
        return nomPuesto;
    }

    public void setNomPuesto(String nomPuesto) {
        this.nomPuesto = nomPuesto;
    }

    public String descripcion() {
        return descripcion;
    }

    public void descripcion(String descripcionPuesto) {
        this.descripcion = descripcionPuesto;
    }

    public float getSueldoPuesto() {
        return sueldoPuesto;
    }

    public void setSueldoPuesto(float sueldoPuesto) {
        this.sueldoPuesto = sueldoPuesto;
    }
}
