package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.Statement;

public class Selectores {
    public static void creaAlerta(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al agregar");
        alerta.setHeaderText("No se pudo agregar el registro");
        alerta.setContentText("Por favor ingrese un valor valido");
        alerta.showAndWait();
    }
    public static ObservableList<ProductoDAO> listaProductos(int idCategoria) {
        String query="SELECT * FROM Producto where idCategoria= "+idCategoria;
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
