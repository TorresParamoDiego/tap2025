package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class DetalleOrdenDAO {
    private int idOrden;
    private int idProducto;
    private int cantidad;
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public void INSERT(){
        String query="INSERT INTO DetalleOrden (idOrden,idProducto,cantidad) " +
                "values('"+idOrden+"','"+idProducto+"','"+cantidad+"')";
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
        String query="UPDATE DetalleOrden SET idOrden = '"+idOrden+"'," +
                "idProducto = '"+idProducto+
                "' cantidad = '"+cantidad+"' WHERE idOrden ="+idOrden+" AND idProducto = "+idProducto;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM DetalleOrden where idProducto = "+idProducto+"" +
                " and idOrden = "+idOrden;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<DetalleOrdenDAO> SELECT(){
        String query="SELECT * FROM DetalleOrden";
        ObservableList<DetalleOrdenDAO> listaDO= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        DetalleOrdenDAO objetoDO;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoDO=new DetalleOrdenDAO();
                objetoDO.setIdOrden(res.getInt("idOrden"));
                objetoDO.setIdProducto(res.getInt("idProducto"));
                listaDO.add(objetoDO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDO;
    }
}
