package com.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {
    private Scene scene;
    private TextField textDisplay;
    private VBox vBox;
    private GridPane gridTeclas;
    private Button[][] arrBtnTeclado;
    private String[] teclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "=", "0", ".", "-"};
    void crearUI(){
        crearTeclado();
        textDisplay = new TextField("0");
        //textDisplay.setPromptText("Tecla");
        textDisplay.setEditable(false);
        textDisplay.setAlignment(Pos.BASELINE_RIGHT);
        vBox = new VBox(textDisplay, gridTeclas);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        scene = new Scene(vBox, 200,200);
    }

    void crearTeclado(){
        arrBtnTeclado = new Button[4][4];
        gridTeclas = new GridPane();
        gridTeclas.setHgap(10);
        gridTeclas.setVgap(5);
        byte pos=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int finalPos=pos;
                arrBtnTeclado[i][j] = new Button(teclas[pos]);
                arrBtnTeclado[i][j].setOnAction(event -> EventoTeclado(teclas[finalPos]));
                arrBtnTeclado[i][j].setPrefSize(50,50);
                gridTeclas.add(arrBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String tecla) {
        textDisplay.appendText(tecla);
    }

    public Calculadora() {
        crearUI();
        this.setScene(scene);
        this.setTitle("Calculadora");
        this.show();
    }
}

