package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CompraInsumosDAO {
    private int idCompra;
    private int idInsumo;
    private int cantidadInsumo;
    private String fechCompra;
    private float costoCompra;

    public float getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(float costoCompra) {
        this.costoCompra = costoCompra;
    }

    public String getFechCompra() {
        return fechCompra;
    }

    public void setFechCompra(String fechCompra) {
        this.fechCompra = fechCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(int cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }

    public void INSERT(){
        String query="INSERT INTO CompraInsumos (idInsumo,cantidadInsumo,fechCompra,costoCompra) " +
                "values('"+idInsumo+"','"+cantidadInsumo+"','"+fechCompra+"','"+costoCompra+"')";
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
        String query="UPDATE ReservacionMesa SET idInsumo = '"+idInsumo+"'," +
                "cantidadInsumo = '"+cantidadInsumo+"',fechCompra = '"+fechCompra+
                "',costoCompra = '"+costoCompra+
                "' WHERE idCompra = "+idCompra;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM ReservacionMesa where idCompra = "+idCompra;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<CompraInsumosDAO> SELECT(){
        String query="SELECT * FROM CompraInsumos";
        ObservableList<CompraInsumosDAO> listaCI= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        CompraInsumosDAO objetoCI;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoCI=new CompraInsumosDAO();
                objetoCI.setIdCompra(res.getInt("idCompra"));
                objetoCI.setIdInsumo(res.getInt("idInsumo"));
                objetoCI.setCantidadInsumo(res.getInt("cantidadInsumo"));
                objetoCI.setFechCompra(res.getString("fechCompra"));
                objetoCI.setCostoCompra(res.getFloat("costoCompra"));
                listaCI.add(objetoCI);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaCI;
    }
}
