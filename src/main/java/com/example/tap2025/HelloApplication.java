package com.example.tap2025;

import com.example.tap2025.Modelos.Conexion;
import com.example.tap2025.vistas.Calculadora;
import com.example.tap2025.vistas.Celayork;
import com.example.tap2025.vistas.Login;
import com.example.tap2025.vistas.Restaurante;
import com.example.tap2025.vistas.Rompecabezas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1,menCompetencia2;
    private MenuItem mitCalculadora,mitRestaurante,mitRompecabezas,mitHilos;
    private Scene escena;
    void creaUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitRestaurante = new MenuItem("Restaurante");
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitHilos = new MenuItem("Celayork");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante.setOnAction(event -> new Login());
        mitRompecabezas.setOnAction(event -> new Rompecabezas());
        mitHilos.setOnAction(event -> new Celayork());
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalculadora,mitRestaurante,mitRompecabezas);
        menCompetencia2 = new Menu("Competencia 2");
        menCompetencia2.getItems().addAll(mitHilos);
        mnbPrincipal=new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompetencia1,menCompetencia2);
        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/main.css").toString());
    }
    @Override
    public void start(Stage stage) throws IOException {
        Conexion.createConnection();
        creaUI();
        stage.setTitle("Hola Mundo de Eventos ʕ•́ᴥ•̀ʔっ");
        stage.setScene(escena);
        stage.show();
        //new RestauranteAdmin();
        //new Restaurante(2, "");
        stage.setMaximized(true);
        stage.setOnCloseRequest(event -> {
            try {
                Conexion.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}