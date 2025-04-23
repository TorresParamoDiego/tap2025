package com.example.tap2025.Modelos;

import javafx.scene.control.Alert;

public class Selectores {
    public static void creaAlerta(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al agregar");
        alerta.setHeaderText("No se pudo agregar el registro");
        alerta.setContentText("Por favor ingrese un valor valido");
        alerta.showAndWait();
    }
}
