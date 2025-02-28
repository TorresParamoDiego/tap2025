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
    private TextField txtDisplay;
    private VBox vBox;
    private GridPane gridTeclas;
    private float numero1,numero2;
    private String operacion;
    private Button[][] arrBtnTeclado;
    private String[] teclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "=", "0", ".", "-"};

    void crearUI(){
        crearTeclado();
        txtDisplay = new TextField("0");
        //textDisplay.setPromptText("Tecla");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        vBox = new VBox(txtDisplay, gridTeclas);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        scene = new Scene(vBox, 200,200);
        scene.getStylesheets().add(getClass().getResource("/Styles/calcu.css").toString());
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
                if(teclas[pos].equals("=")) {
                    arrBtnTeclado[i][j].setId("fontButton");
                }
                arrBtnTeclado[i][j].setOnAction(event -> EventoTeclado(teclas[finalPos]));
                arrBtnTeclado[i][j].setPrefSize(50,50);
                gridTeclas.add(arrBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String tecla) {
        switch (tecla) {
            case "*":
            case "/":
            case "+":
            case "-":
                if(!txtDisplay.getText().equals("ERROR")&&!txtDisplay.getText().equals(".")) {
                    operacion = tecla;
                    numero1 = Float.parseFloat(txtDisplay.getText());
                    txtDisplay.setText("0");
                }
                break;
            case "=":
                if(!txtDisplay.getText().equals("ERROR")&&!txtDisplay.equals(".")) {
                    numero2 = Float.parseFloat(txtDisplay.getText());
                    decidirOperacion();
                    if (!txtDisplay.getText().equals("ERROR"))
                        txtDisplay.setText("" + numero2);
                }
                numero1=numero2;
                numero2=0;
                operacion="";
                break;
            case ".":
                if(!txtDisplay.getText().equals(""+numero1))
                    if (txtDisplay.getText().contains(".")) {
                        mostrarMensajeError();
                        break;
                    }
            default:
                if(txtDisplay.getText().equals("0")||txtDisplay.getText().equals(""+numero1)&&operacion.isEmpty()||txtDisplay.getText().equals("ERROR"))
                    txtDisplay.setText("");
                txtDisplay.appendText(tecla);
        }
    }
    void decidirOperacion(){
        if(!operacion.isEmpty()) {
            switch (operacion) {
                case "*":
                    multiplicar();
                    break;
                case "/":
                    dividir();
                    break;
                case "+":
                    sumar();
                    break;
                case "-":
                    restar();
                    break;
            }
        }
        else {
            numero1 = Float.parseFloat(txtDisplay.getText());
            numero2 = numero1;
        }
    }

    private void mostrarMensajeError() {
        txtDisplay.setText("ERROR");
        numero1=0;
        numero2=0;
        operacion="";
        //textDisplay.setText("0");
    }

    void dividir(){
        if(numero2!=0)
            numero2 = numero1/numero2;
        else
            mostrarMensajeError();
    }
    void sumar(){
        numero2=numero1+numero2;
    }
    void restar(){
        numero2=numero1-numero2;
    }
    void multiplicar(){
        numero2=numero1*numero2;
        if((numero2+"").equals("-0.0")){
            numero2=0;
        }
    }
    public Calculadora() {
        numero1=0;
        numero2=0;
        operacion="";
        crearUI();
        this.setScene(scene);
        this.setTitle("Calculadora");
        this.show();
    }
}