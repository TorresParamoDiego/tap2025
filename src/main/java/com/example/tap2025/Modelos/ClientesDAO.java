package com.example.tap2025.Modelos;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientesDAO {
    private int idCte;
    private String nomCte;
    private String telCte;
    private String direccion;
    private String emailCte;

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public String getNomCte() {
        return nomCte;
    }

    public void setNomCte(String nomCte) {
        this.nomCte = nomCte;
    }

    public String getTelCte() {
        return telCte;
    }

    public void setTelCte(String telCte) {
        this.telCte = telCte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmailCte() {
        return emailCte;
    }

    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }

    public void INSERT() {
        String query="INSERT INTO clientes (nomCte,telCte,direccion,emailCte) " +
                "values('"+nomCte+"','"+telCte+"','"+direccion+"','"+emailCte+"')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e) {
            Selectores.creaAlerta();
        }
    }
    public void UPDATE() {
        String query="UPDATE clientes SET nomCte = '"+nomCte+"'," +
                "telCte = '"+telCte+"',direccion = '"+direccion+"'," +
                "emailCte = '"+emailCte+"' WHERE idCte = "+idCte;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM clientes where idCte = "+idCte;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ClientesDAO> SELECT(){
        String query="SELECT * FROM clientes";
        ObservableList<ClientesDAO> listaC= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        ClientesDAO objetoC;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoC=new ClientesDAO();
                objetoC.setIdCte(res.getInt("idCte"));
                objetoC.setNomCte(res.getString("nomCte"));
                objetoC.setTelCte(res.getString("telCte"));
                objetoC.setDireccion(res.getString("direccion"));
                objetoC.setEmailCte(res.getString("emailCte"));
                listaC.add(objetoC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}
/*para hacer consultas es necesario un try-catch
Elemento que me lleva a la instruccion = statement (para llegar a la BD)
executeQuery para select
conjunto de renglones tranformar a un conjunto de objetos
ObservableList arreglo de elementos, Est de datos como el arrayList, es el tipo de objeto
*que se requiere para mostrar visualmente
 */
