package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoDAO {
    private int idProducto;
    private String nomProd;
    private float precioProd;
    private float costoProd;
    private String UrlImagenProd;
    private int idCategoria;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public float getCostoProd() {
        return costoProd;
    }

    public void setCostoProd(float costoProd) {
        this.costoProd = costoProd;
    }

    public String getUrlImagenProd() {
        return UrlImagenProd;
    }

    public void setUrlImagenProd(String urlImagenProd) {
        UrlImagenProd = urlImagenProd;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public void INSERT(){
        String query="INSERT INTO Producto (nomProd,precioProd,costoProd,UrlImagenProd,idCategoria) " +
                "values('"+nomProd+"','"+precioProd+"','"+costoProd+"','" +
                UrlImagenProd+"','"+idCategoria+"')";
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
        String query="UPDATE Producto SET nomProd = '"+nomProd+"'," +
                "precioProd = '"+precioProd+"',costoProd = '"+costoProd+
                "' UrlImagenProd = '"+UrlImagenProd+"' idCategoria" +
                "= '"+idCategoria+"' WHERE idProducto = "+idProducto;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Producto where idProducto = "+idProducto;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ProductoDAO> SELECT(){
        String query="SELECT * FROM Producto";
        ObservableList<ProductoDAO> listaP= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ProductoDAO objetoP;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoP=new ProductoDAO();
                objetoP.setIdProducto(res.getInt("idProducto"));
                objetoP.setNomProd(res.getString("nomProd"));
                objetoP.setPrecioProd(res.getFloat("precioProd"));
                objetoP.setCostoProd(res.getFloat("costoProd"));
                objetoP.setUrlImagenProd(res.getString("UrlImagenProd"));
                objetoP.setIdCategoria(res.getInt("idCategoria"));
                listaP.add(objetoP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaP;
    }
}
