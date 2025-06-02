package com.example.tap2025.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoDAO {
    private int idEmpl;
    private String nomEmpl;
    private String RFCEmpl;
    private String CurpEmpl;
    private String nssEmpl;
    private String telEmpl;
    private String fechIngresoEmpl;
    private String horarioEntradaEmpl;
    private String horarioSalidaEmpl;
    private int idPuesto;
    private String password;

    public int getIdEmpl() {
        return idEmpl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdEmpl(int idEmpl) {
        this.idEmpl = idEmpl;
    }

    public String getNomEmpl() {
        return nomEmpl;
    }

    public void setNomEmpl(String nomEmpl) {
        this.nomEmpl = nomEmpl;
    }

    public String getRFCEmpl() {
        return RFCEmpl;
    }

    public void setRFCEmpl(String RFCEmpl) {
        this.RFCEmpl = RFCEmpl;
    }

    public String getCurpEmpl() {
        return CurpEmpl;
    }

    public void setCurpEmpl(String curpEmpl) {
        CurpEmpl = curpEmpl;
    }

    public String getNssEmpl() {
        return nssEmpl;
    }

    public void setNssEmpl(String nssEmpl) {
        this.nssEmpl = nssEmpl;
    }

    public String getTelEmpl() {
        return telEmpl;
    }

    public void setTelEmpl(String telEmpl) {
        this.telEmpl = telEmpl;
    }

    public String getFechIngresoEmpl() {
        return fechIngresoEmpl;
    }

    public void setFechIngresoEmpl(String fechIngresoEmpl) {
        this.fechIngresoEmpl = fechIngresoEmpl;
    }

    public String getHorarioEntradaEmpl() {
        return horarioEntradaEmpl;
    }

    public void setHorarioEntradaEmpl(String horarioEntradaEmpl) {
        this.horarioEntradaEmpl = horarioEntradaEmpl;
    }

    public String getHorarioSalidaEmpl() {
        return horarioSalidaEmpl;
    }

    public void setHorarioSalidaEmpl(String horarioSalidaEmpl) {
        this.horarioSalidaEmpl = horarioSalidaEmpl;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }
    public void INSERT(){
        String query = "INSERT INTO Empleado " +
                "(nomEmpl, RFCEmpl, CurpEmpl, nssEmpl, horarioEntradaEmpl, horarioSalidaEmpl, fechIngresoEmpl, telEmpl, idPuesto) " +
                "VALUES ('" + nomEmpl + "', '" + RFCEmpl + "', '" + CurpEmpl + "', '" + nssEmpl + "', '" + horarioEntradaEmpl + "', '" + horarioSalidaEmpl + "', '" + fechIngresoEmpl + "', '" + telEmpl + "', '" + idPuesto + "', '" + password + "')";
        //instanciar un statement
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(query+"\n\n");
            e.printStackTrace();
            Selectores.creaAlerta();
        }
    }
    public void UPDATE(){
        String query = "UPDATE Empleado SET nomEmpl = '" + nomEmpl + "', " +
                "RFCEmpl = '" + RFCEmpl + "', " +
                "CurpEmpl = '" + CurpEmpl + "', " +
                "nssEmpl = '" + nssEmpl + "', " +
                "horarioEntradaEmpl = '" + horarioEntradaEmpl + "', " +
                "horarioSalidaEmpl = '" + horarioSalidaEmpl + "', " +
                "fechIngresoEmpl = '" + fechIngresoEmpl + "', " +
                "telEmpl = '" + telEmpl + "', " +
                "idPuesto = '" + idPuesto + "', " +
                "password = '" + password + "' " +
                "WHERE idEmpl = " + idEmpl;
        try{
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(query+"\n\n");
            Selectores.creaAlerta();
        }
    }
    public void DELETE(){
        String query="DELETE FROM Empleado where idEmpleado = "+idEmpl;
        try {
            Statement stmt=Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<EmpleadoDAO> SELECT(){
        String query="SELECT * FROM empleado";
        ObservableList<EmpleadoDAO> listaE= FXCollections.observableArrayList();
        //El observable list se retornara cuando se llene
        EmpleadoDAO objetoE;
        try {
            Statement stmt=Conexion.connection.createStatement();
            ResultSet res=stmt.executeQuery(query);
            //res coleccion de renglones
            while(res.next()){//manda false cuando no se puede posicionar en un renglon
                objetoE=new EmpleadoDAO();
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
                objetoE.setPassword(res.getString("password"));
                listaE.add(objetoE);
            }
        }catch (Exception e){
            System.out.println(query+"\n\n");
            e.printStackTrace();
        }
        return listaE;
    }
}
