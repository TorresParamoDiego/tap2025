package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO {
    private int idMesa;
    private int capacidadMesa;

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidadMesa() {
        return capacidadMesa;
    }

    public void setCapacidadMesa(int capacidadMesa) {
        this.capacidadMesa = capacidadMesa;
    }
    public void INSERT(){
        String query="INSERT INTO Mesa (capacidadMesa) " +
                "values('"+capacidadMesa+"')";
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
        String query="UPDATE Mesa SET capacidadMesa = '"+capacidadMesa+"'" +
                " WHERE idPuesto = "+idMesa;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Mesa where idMesa = "+idMesa;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<MesaDAO> SELECT(){
        String query="SELECT * FROM Mesa";
        ObservableList<MesaDAO> listaM= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        MesaDAO objetoM;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoM=new MesaDAO();
                objetoM.setIdMesa(res.getInt("idMesa"));
                objetoM.setCapacidadMesa(res.getInt("capacidadMesa"));
                listaM.add(objetoM);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaM;
    }
}
