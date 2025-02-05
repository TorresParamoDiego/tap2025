package com.example.tap2025;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private MenuItem mitCalculadora;
    private Scene escena;
    void creaUI(){
        mitCalculadora = new MenuItem("Calculadora");
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalculadora);
        mnbPrincipal=new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompetencia1);
        vBox = new VBox();
    }
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Hola Mundo de Eventos :)");
        stage.setScene(new Scene(vBox));
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