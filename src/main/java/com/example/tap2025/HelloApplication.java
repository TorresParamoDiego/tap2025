package com.example.tap2025;

import com.example.tap2025.Componentes.Hilo;
import com.example.tap2025.Modelos.Conexion;
import com.example.tap2025.vistas.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

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
        mitRestaurante.setOnAction(event -> new Restaurante());
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
        /*new Hilo("Ruta Pinos").start();
        new Hilo("Ruta Laureles").start();
        new Hilo("Ruta San Juan de la Vega").start();
        new Hilo("Ruta Teneria").start();
        new Hilo("Ruta Monteblanco").start();*/
        Conexion.createConnection();
        creaUI();
        stage.setTitle("Hola Mundo de Eventos ʕ•́ᴥ•̀ʔっ");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
}
/*
        btnSaludo=new Button("Hola amigo :)");
        btnSaludo.setOnAction(event -> clickEvent());
        btnSaludo2=new Button("Hola amiga :)");
        btnSaludo2.setOnAction(event ->clickEvent());
        btnSaludo3=new Button("Hola amige :)");
        vBox=new VBox(btnSaludo,btnSaludo2,btnSaludo3);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(10,0,0,0));
        stage.setTitle("Hola Mundo de Eventos :)");
        stage.setScene(new Scene(vBox,200,200));
        stage.show();
        stage.setMaximized(true);


*/