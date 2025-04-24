package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ClientesDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNomCte,txtDireccion,txtTelCte,txtEmail;
    private Scene escena;
    private ClientesDAO objC;
    private TableView tblCliente;
    public Cliente(TableView tblCliente,ClientesDAO objC) {
        this.tblCliente = tblCliente;
        creaUI();
        if(objC == null) {
            this.objC = new ClientesDAO();
        }
        else {
            this.objC = objC;
            txtNomCte.setText(objC.getNomCte());
            txtDireccion.setText(objC.getDireccion());
            txtTelCte.setText(objC.getTelCte());
            txtEmail.setText(objC.getEmailCte());
        }
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
        btnGuardar.setOnAction(event -> {
            objC.setNomCte(txtNomCte.getText());
            objC.setDireccion(txtDireccion.getText());
            objC.setTelCte(txtTelCte.getText());
            objC.setEmailCte(txtEmail.getText());
            if (objC.getIdCte() > 0)
                objC.UPDATE();
            else
                objC.INSERT();
            tblCliente.setItems(objC.SELECT());
            tblCliente.refresh();
            this.close();
        });
        vBox = new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail,btnGuardar);
        escena = new Scene(vBox);
    }
}
/* la forma de comunicar objetos es por metodos
 */