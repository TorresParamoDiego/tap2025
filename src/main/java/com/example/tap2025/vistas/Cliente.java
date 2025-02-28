package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNomCte,txtDireccion,txtTelCte,txtEmail;
    private Scene escena;

    public Cliente() {
        creaUI();
        this.setTitle("Registrar cliente");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNomCte = new TextField();
        txtNomCte.setPromptText("Nombre");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtTelCte = new TextField();
        txtTelCte.setPromptText("Telefono");
        txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        btnGuardar = new Button("Guardar");
        vBox = new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail,btnGuardar);
        escena = new Scene(vBox);
    }
}
