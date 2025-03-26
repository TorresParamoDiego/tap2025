package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.Hilo;
import javafx.geometry.Insets;
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
    private Label[] lblRutas;
    private ProgressBar[] pgbRutas;
    private String[] rutas={"Correcaminos","Basurero","Lemus","Monteblanco","Flash"};
    private Hilo[] thrRutas;
    public Celayork() {
        this.setTitle("Calles de Celaya");
        creaUI();
        this.setScene(escena);
        this.show();
    }

    private void creaUI() {
        pgbRutas = new ProgressBar[5];
        lblRutas = new Label[5];
        thrRutas = new Hilo[5];
        gdpCalles= new GridPane();
        for (int i= 0; i<pgbRutas.length; i++){
            lblRutas[i] = new Label(rutas[i]);
            lblRutas[i].setStyle(String.format("-fx-font-weight: bold;-fx-font-size: %dpx;-fx-font-family: 'Comic Sans MS';",12));
            pgbRutas[i] = new ProgressBar(0);//para que el pgb inicie vacio, va desde 0 a 1
            gdpCalles.add(lblRutas[i], 0, i); //columna-renglon
            gdpCalles.add(pgbRutas[i], 1, i);
            thrRutas[i] = new Hilo(rutas[i],pgbRutas[i]);

        }
        gdpCalles.setHgap(10);
        gdpCalles.setVgap(10);
        btnIniciar = new Button("Iniciar");

        btnIniciar.setOnAction(e -> {
            for (int i = 0; i < pgbRutas.length; i++) {
                thrRutas[i].start();
            }
        });
        vbox = new VBox(gdpCalles, btnIniciar);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 10, 5, 10));
        escena=new Scene(vbox,250,200);
    }
}
