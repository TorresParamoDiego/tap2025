package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsumoDAO {
    private int idInsumo;
    private String nomIns;
    private float precioIns;
    private int idProveedor;

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNomIns() {
        return nomIns;
    }

    public void setNomIns(String nomIns) {
        this.nomIns = nomIns;
    }

    public float getPrecioIns() {
        return precioIns;
    }

    public void setPrecioIns(float precioIns) {
        this.precioIns = precioIns;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    public void INSERT(){
        String query="INSERT INTO Insumo (nomIns,precioIns,idProovedor) " +
                "values('"+nomIns+"','"+precioIns+"','"+idProveedor+"')";
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
        String query="UPDATE Insumo SET nomIns = '"+nomIns+"'," +
                "precioIns = '"+precioIns+"' idProovedor = '"+idProveedor+
                "' WHERE idInsumo = "+idInsumo;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Insumo where idInsumo = "+idInsumo;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<InsumoDAO> SELECT(){
        String query="SELECT * FROM Insumo";
        ObservableList<InsumoDAO> listaI= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        InsumoDAO objetoI;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoI=new InsumoDAO();
                objetoI.setIdInsumo(res.getInt("idInsumo"));
                objetoI.setNomIns(res.getString("nomIns"));
                objetoI.setPrecioIns(res.getFloat("precioIns"));
                objetoI.setIdProveedor(res.getInt("idProveedor"));
                listaI.add(objetoI);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaI;
    }
}

