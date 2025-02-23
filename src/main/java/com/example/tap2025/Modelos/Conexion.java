package com.example.tap2025.Modelos;


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "admin";
    static private String PWD = "1234";
    static private String HOST = "localhost"; // 127.0.0.1 (loopback)
    static private String PORT = "3306"; //puerto de mysql o mariadb
    public static Connection connection; //para la conexion

    public static void createConnection() {//para aperturar la conexion a la BD

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//etiqueta para generar conexion, java
            connection=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD);//se abre un socket, conexion a host
            System.out.println("Conexion establecida °3°");
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
/*siempre que se hace acceso a recursos externos es necesario un try catch
socket (mecanismo de comunicacion para intercambiar informacion entre 2 aplicaciones de mismo o diferente tipo)
 */