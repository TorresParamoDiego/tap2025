package com.example.tap2025;

import com.example.tap2025.Modelos.Conexion;
import com.example.tap2025.vistas.Calculadora;
import com.example.tap2025.vistas.ListaClientes;
import com.example.tap2025.vistas.Rompecabezas;
import com.example.tap2025.vistas.VentasRestaurante;
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
    private MenuItem mitCalculadora,mitRestaurante,mitRompecabezas;
    private Scene escena;
    void creaUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitRestaurante = new MenuItem("Restaurante");
        mitRompecabezas = new MenuItem("Rompecabezas");
        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante.setOnAction(event -> {new ListaClientes();});
        mitRompecabezas.setOnAction(event -> {new Rompecabezas();});
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalculadora,mitRestaurante,mitRompecabezas);
        mnbPrincipal=new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompetencia1);
        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/main.css").toString());
    }
    @Override
    public void start(Stage stage) throws IOException {
        Conexion.createConnection();
        creaUI();
        stage.setTitle("Hola Mundo de Eventos :)");
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