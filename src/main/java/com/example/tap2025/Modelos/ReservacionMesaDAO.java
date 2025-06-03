package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionMesaDAO {
    private int idReservacion;
    private int idMesa;

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    public void INSERT(){
        String query="INSERT INTO ReservacionMesa (idMesa,idReservacion) " +
                "values('"+idMesa+"','"+idReservacion+"')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            Selectores.creaAlerta();
        }
    }
    public void UPDATE(){
        String query="UPDATE ReservacionMesa SET idMesa = '"+idMesa+"'," +
                "idReservacion = '"+idReservacion+
                "' WHERE idMesa = "+idMesa+" AND idReservacion = "+idReservacion;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(query+"\n\n");
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM ReservacionMesa where idMesa = "+idMesa+" AND idReservacion = "+idReservacion;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ReservacionMesaDAO> SELECT(){
        String query="SELECT * FROM ReservacionMesa";
        ObservableList<ReservacionMesaDAO> listaRM= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ReservacionMesaDAO objetoRM;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoRM=new ReservacionMesaDAO();
                objetoRM.setIdReservacion(res.getInt("idReservacion"));
                objetoRM.setIdMesa(res.getInt("idMesa"));
                listaRM.add(objetoRM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaRM;
    }
}
