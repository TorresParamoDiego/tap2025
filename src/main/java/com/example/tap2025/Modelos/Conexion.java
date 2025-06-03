package com.example.tap2025.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "admin";
    static private String PWD = "1234";
    static private String HOST = "localhost";
    static private String PORT = "3306";
    public static Connection connection;

    public static void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//etiqueta para generar conexion, javaMore actions
            connection=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD);//se abre un socket, conexion a host
            System.out.println("Conexion establecida °3°");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
