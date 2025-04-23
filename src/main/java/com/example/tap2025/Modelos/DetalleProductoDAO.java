package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class DetalleProductoDAO {
    private int idInsumo;
    private int idProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }
    public void INSERT(){
        String query="INSERT INTO DetalleProducto (idInsumo,idProducto) " +
                "values('"+idInsumo+"','"+idProducto+"')";
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
        String query="UPDATE DetalleProducto SET idInsumo = '"+idInsumo+"'," +
                "idProducto = '"+idProducto+
                "' WHERE idInsumo = "+idInsumo+"and idProducto = "+idProducto;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM DetalleProducto where idInsumo = "+idInsumo+
        "and idProducto = "+idProducto;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<DetalleProductoDAO> SELECT(){
        String query="SELECT * FROM DetalleProducto";
        ObservableList<DetalleProductoDAO> listaDP= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        DetalleProductoDAO objetoDP;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoDP=new DetalleProductoDAO();
                objetoDP.setIdInsumo(res.getInt("idInsumo"));
                objetoDP.setIdProducto(res.getInt("idProducto"));
                listaDP.add(objetoDP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDP;
    }
}
