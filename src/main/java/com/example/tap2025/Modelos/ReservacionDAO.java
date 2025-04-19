package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

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
    public void INSERT(){
        String query="INSERT INTO Reservacion (duracionRese,horarioFechRese,idCte) " +
                "values('"+duracionRese+"','"+horarioFechRese+"','"+idCte+"')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query="UPDATE Reservacion set duracionRese = '"+duracionRese+"' horarioFechRese = '"+
                horarioFechRese+"' idCte = '"+idCte+"' "+
                " WHERE idReservacion = "+idReservacion;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Reservacion where idReservacion = "+idReservacion;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ReservacionDAO> SELECT(){
        String query="SELECT * FROM Reservacion";
        ObservableList<ReservacionDAO> listaR= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ReservacionDAO objetoR;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoR =new ReservacionDAO();
                objetoR.setIdReservacion(res.getInt("idReservacion"));
                objetoR.setDuracionRese(res.getInt("duracionRese"));
                objetoR.setHorarioFechRese(res.getString("horarioFechRese"));
                objetoR.setIdCte(res.getInt("idCte"));
                listaR.add(objetoR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaR;
    }
}
