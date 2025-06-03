package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.Statement;

public class Selectores {
    public static boolean flag = false;
    public static void creaAlerta() {
        flag = true;
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al agregar");
        alerta.setHeaderText("No se pudo agregar el registro");
        alerta.setContentText("Por favor ingrese un valor valido");
        alerta.showAndWait();
    }

    public static ObservableList<ProductoDAO> listaProductos(int idCategoria) {
        String query = "SELECT * FROM Producto where idCategoria= " + idCategoria;
        ObservableList<ProductoDAO> listaP = FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ProductoDAO objetoP;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            //res coleccion de renglones
            while (res.next()) {//manda false cuando no se puede posicionar en un renglon
                objetoP = new ProductoDAO();
                objetoP.setIdProducto(res.getInt("idProducto"));
                objetoP.setNomProd(res.getString("nomProd"));
                objetoP.setPrecioProd(res.getFloat("precioProd"));
                objetoP.setCostoProd(res.getFloat("costoProd"));
                objetoP.setUrlImagenProd(res.getString("UrlImagenProd"));
                objetoP.setIdCategoria(res.getInt("idCategoria"));
                listaP.add(objetoP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaP;
    }

    public static ObservableList<EmpleadoDAO> listaEmpleados() {
        String query = "SELECT * FROM Empleado where idPuesto= " + 8;
        ObservableList<EmpleadoDAO> listaE = FXCollections.observableArrayList();
        EmpleadoDAO objetoE;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            //res coleccion de renglones
            while (res.next()) {//manda false cuando no se puede posicionar en un renglon
                objetoE = new EmpleadoDAO();
                objetoE.setIdEmpl(res.getInt("idEmpl"));
                objetoE.setNomEmpl(res.getString("nomEmpl"));
                objetoE.setRFCEmpl(res.getString("RFCEmpl"));
                objetoE.setCurpEmpl(res.getString("CurpEmpl"));
                objetoE.setNssEmpl(res.getString("nssEmpl"));
                objetoE.setHorarioEntradaEmpl(res.getString("horarioEntradaEmpl"));
                objetoE.setHorarioSalidaEmpl(res.getString("horarioSalidaEmpl"));
                objetoE.setFechIngresoEmpl(res.getString("fechIngresoEmpl"));
                objetoE.setTelEmpl(res.getString("telEmpl"));
                objetoE.setIdPuesto(res.getInt("idPuesto"));
                listaE.add(objetoE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaE;
    }

    public static double calcPrecioOrden(int idOrden) {
        double precio = 0;
        String query = "SELECT sum(precioProd*cantidad) FROM DetalleOrden inner join Producto on Producto.idProducto = " +
                "DetalleOrden.idProducto where idOrden= " + idOrden;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                precio = res.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return precio;
    }
}
