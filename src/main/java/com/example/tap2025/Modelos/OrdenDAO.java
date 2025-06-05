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
    private int idMetodoPago;
    private String fechHora;
    private float precioOrden;

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public float getPrecioOrden() {
        return precioOrden;
    }

    public void setPrecioOrden(float precioOrden) {
        this.precioOrden = precioOrden;
    }

    public String getFechHora() {
        return fechHora;
    }

    public void setFechHora(String fechHora) {
        this.fechHora = fechHora;
    }

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
        String query="INSERT INTO Orden (idCte,idEmpl,idMesa,precioOrden,fechHora,idMetodoPago) " +
                "values('"+idCte+"','"+idEmpl+"','"+idMesa+"','"+precioOrden+"','"+fechHora+"','"+idMetodoPago+"')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(query);
            System.err.println(query+"\n");
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query="UPDATE Orden SET idCte = '"+idCte+"'," +
                "idEmpl = '"+idEmpl+"',idMesa = '"+idMesa+
                "', fechHora ='"+fechHora+"', precioOrden =" +
                "'"+precioOrden+"', idMetodoPago = '"+idMetodoPago+"' WHERE idOrden = "+idOrden;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(query+"\n\n");
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Orden where idOrden = "+idOrden;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            Selectores.creaAlerta();
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
                objetoO.setFechHora(res.getString("fechHora"));
                objetoO.setPrecioOrden(res.getFloat("precioOrden"));
                objetoO.setIdMetodoPago(res.getInt("idMetodoPago"));
                listaO.add(objetoO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaO;
    }
}
