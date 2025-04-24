package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ProveedorDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Proovedor extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre, txtEmail,txtDireccion;
    private TextArea txtDescripcion;
    private Scene escena;
    private ProveedorDAO obj;
    private TableView tblProveedor;
    public Proovedor(TableView tblProveedor, ProveedorDAO obj) {
        this.tblProveedor = tblProveedor;
        creaUI();
        if(obj == null) {
            this.obj = new ProveedorDAO();
        }
        else {
            this.obj = obj;
            txtNombre.setText(obj.getNomProv());
            txtEmail.setText(obj.getEmailProv());
            txtDescripcion.setText(obj.getDescripcionProv());
            txtDireccion.setText(obj.getDireccionProv());
        }
        this.setTitle("Registrar los proveedores");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Descripcion");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setNomProv(txtNombre.getText());
            obj.setEmailProv(txtEmail.getText());
            obj.setDescripcionProv(txtDescripcion.getText());
            obj.setDireccionProv(txtDireccion.getText());
            if(obj.getIdProveedor()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblProveedor.setItems(obj.SELECT());
            tblProveedor.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtEmail,txtDireccion,txtDescripcion,btnGuardar);
        escena = new Scene(vBox);
    }
}
