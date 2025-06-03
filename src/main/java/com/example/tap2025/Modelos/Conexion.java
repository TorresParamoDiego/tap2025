package com.example.tap2025.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "admin";
    static private String PWD = "123";
    static private String HOST = "localhost";
    static private String PORT = "3306";
    public static Connection connection;

    public static void createConnection(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://" + HOST + ":" + PORT + "/" + DB, USER, PWD);
            System.out.println("Conexión establecida a la Base de Datos :)");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}