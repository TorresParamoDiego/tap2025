package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriaDAO {
    private int idCategoria;
    private String nomCategoria;
    private String descripcionCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public void INSERT()  {
        String query="INSERT INTO Categoria (nomCategoria,descripcionCategoria) " +
                "values('"+nomCategoria+"','"+descripcionCategoria+"')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            Selectores.creaAlerta();
        }
    }
    public void UPDATE()   {
        String query="UPDATE Categoria SET nomCategoria = '"+nomCategoria+"'," +
                "descripcionCategoria = '"+descripcionCategoria+"'"+
                "WHERE idCategoria = "+idCategoria;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Categoria where idCategoria = "+idCategoria;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<CategoriaDAO> SELECT(){
        String query="SELECT * FROM Categoria";
        ObservableList<CategoriaDAO> listaC= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        CategoriaDAO objetoC;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoC=new CategoriaDAO();
                objetoC.setIdCategoria(res.getInt("idCategoria"));
                objetoC.setNomCategoria(res.getString("nomCategoria"));
                objetoC.setDescripcionCategoria(res.getString("descripcionCategoria"));
                listaC.add(objetoC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}

