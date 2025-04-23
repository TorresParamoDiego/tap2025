package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedorDAO {
    private int idProveedor;
    private String nomProv;
    private String descripcionProv;
    private String emailProv;
    private String direccionProv;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNomProv() {
        return nomProv;
    }

    public void setNomProv(String nomProv) {
        this.nomProv = nomProv;
    }

    public String getDescripcionProv() {
        return descripcionProv;
    }

    public void setDescripcionProv(String descripcionProv) {
        this.descripcionProv = descripcionProv;
    }

    public String getEmailProv() {
        return emailProv;
    }

    public void setEmailProv(String emailProv) {
        this.emailProv = emailProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }
    public void INSERT(){
        String query="INSERT INTO Proveedor (nomProv,descripcionProv,emailProv,direccionProv) " +
                "values('"+nomProv+"','"+descripcionProv+"','"+emailProv+"','"+direccionProv+"')";
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
        String query="UPDATE Proveedor SET nomProv = '"+nomProv+"'," +
                "descripcionProv = '"+descripcionProv+"',emailProv = '"+emailProv+
                "' direccionProv = '"+direccionProv+"' WHERE idProveedor = "+idProveedor;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Proveedor where idProveedor = "+idProveedor;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ProveedorDAO> SELECT(){
        String query="SELECT * FROM Proveedor";
        ObservableList<ProveedorDAO> listaP= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ProveedorDAO objetoP;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoP=new ProveedorDAO();
                objetoP.setIdProveedor(res.getInt("idProveedor"));
                objetoP.setNomProv(res.getString("nomProv"));
                objetoP.setDescripcionProv(res.getString("descripcionProv"));
                objetoP.setEmailProv(res.getString("emailProv"));
                objetoP.setDireccionProv(res.getString("direccionProv"));
                listaP.add(objetoP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaP;
    }
}
