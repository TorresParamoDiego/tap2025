package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Celayork extends Stage {
    private Scene escena;
    private VBox vbox;
    private GridPane gdpCalles;
    private Button btnIniciar;
    private Label[] arrlblRutas;
    private ProgressBar pgbRutas;


    public Celayork() {
        this.setTitle("Calles de Celaya");
        creaUI();
        this.setScene(escena);
        this.show();
    }

    private void creaUI() {
        escena=new Scene();
    }
}
