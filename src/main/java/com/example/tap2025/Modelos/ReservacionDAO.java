package com.example.tap2025.Modelos;

public class ReservacionDAO {
    private int idReservacion;
    private int duracionRese;
    private String horarioFechRese;
    private int idCte;

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public int getDuracionRese() {
        return duracionRese;
    }

    public void setDuracionRese(int duracionRese) {
        this.duracionRese = duracionRese;
    }

    public String getHorarioFechRese() {
        return horarioFechRese;
    }

    public void setHorarioFechRese(String horarioFechRese) {
        this.horarioFechRese = horarioFechRese;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }
}
