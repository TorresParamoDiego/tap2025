package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MetodosPagoDAO {
    private int idMetodoPago;
    private String tipoMetodo;
    private String descripcion;

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getTipoMetodo() {
        return tipoMetodo;
    }

    public void setTipoMetodo(String tipoMetodo) {
        this.tipoMetodo = tipoMetodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void INSERT(){
        String query = "INSERT INTO MetodoPago (tipoMetodo, descripcion) " +
                "VALUES('" + tipoMetodo + "', '" + descripcion + "')";
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            Selectores.creaAlerta();
        }
    }

    public void UPDATE(){
        String query = "UPDATE MetodoPago SET tipoMetodo = '" + tipoMetodo + "', " +
                "descripcion = '" + descripcion + "' " +
                "WHERE idMetodoPago = " + idMetodoPago;
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }

    public void DELETE(){
        String query = "DELETE FROM MetodoPago WHERE idMetodoPago = " + idMetodoPago;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<MetodosPagoDAO> SELECT(){
        String query = "SELECT * FROM MetodoPago";
        ObservableList<MetodosPagoDAO> listaMp = FXCollections.observableArrayList();
        MetodosPagoDAO objetoMp;

        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while(res.next()){
                objetoMp = new MetodosPagoDAO();
                objetoMp.setIdMetodoPago(res.getInt("idMetodoPago"));
                objetoMp.setTipoMetodo(res.getString("tipoMetodo"));
                objetoMp.setDescripcion(res.getString("descripcion"));
                listaMp.add(objetoMp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaMp;
    }
}