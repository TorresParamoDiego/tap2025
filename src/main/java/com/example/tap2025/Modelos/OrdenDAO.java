package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO {
    private int idOrden;
    private int idCte;
    private int idEmpl;
    private int idMesa;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public int getIdEmpl() {
        return idEmpl;
    }

    public void setIdEmpl(int idEmpl) {
        this.idEmpl = idEmpl;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
    public void INSERT(){
        String query="INSERT INTO Orden (idCte,idEmpl,idMesa) " +
                "values('"+idCte+"','"+idEmpl+"','"+idMesa+"')";
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
        String query="UPDATE Orden SET idCte = '"+idCte+"'," +
                "idEmpl = '"+idEmpl+"',idMesa = '"+idMesa+
                "' WHERE idOrden = "+idOrden;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query="DELETE FROM orden where idOrden = "+idOrden;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<OrdenDAO> SELECT(){
        String query="SELECT * FROM Orden";
        ObservableList<OrdenDAO> listaO= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        OrdenDAO objetoO;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoO=new OrdenDAO();
                objetoO.setIdOrden(res.getInt("idOrden"));
                objetoO.setIdCte(res.getInt("idCte"));
                objetoO.setIdEmpl(res.getInt("idEmpl"));
                objetoO.setIdMesa(res.getInt("idMesa"));
                listaO.add(objetoO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaO;
    }
}
