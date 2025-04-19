package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getSueldoPuesto() {
        return sueldoPuesto;
    }

    public void setSueldoPuesto(float sueldoPuesto) {
        this.sueldoPuesto = sueldoPuesto;
    }
    public void INSERT(){
        String query="INSERT INTO puesto (nomPuesto,sueldoPuesto,descripcionPuesto) " +
                "values('"+nomPuesto+"','"+sueldoPuesto+"','"+descripcion+"')";
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
        String query="UPDATE puesto SET nomPuesto = '"+nomPuesto+"'," +
                "sueldoPuesto = '"+sueldoPuesto+"',descripcion = '"+descripcion+
                "' WHERE idPuesto = "+idPuesto;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM puesto where idPuesto = "+idPuesto;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<PuestoDAO> SELECT(){
        String query="SELECT * FROM puesto";
        ObservableList<PuestoDAO> listaP= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        PuestoDAO objetoP;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoP=new PuestoDAO();
                objetoP.setIdPuesto(res.getInt("idPuesto"));
                objetoP.setNomPuesto(res.getString("nomPuesto"));
                objetoP.setDescripcion(res.getString("descripcion"));
                objetoP.setSueldoPuesto(res.getFloat("sueldoPuesto"));
                listaP.add(objetoP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaP;
    }
}
