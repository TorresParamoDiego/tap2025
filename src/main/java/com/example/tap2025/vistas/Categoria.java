package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.CategoriaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Categoria extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre;
    private TextArea txtDescripcion;
    private Scene escena;
    private CategoriaDAO objC;
    private TableView tblCategoria;
    public Categoria(TableView tblCategoria, CategoriaDAO objC) {
        this.tblCategoria = tblCategoria;
        creaUI();
        if(objC == null) {
            this.objC = new CategoriaDAO();
        }
        else {
            this.objC = objC;
            txtNombre.setText(objC.getNomCategoria());
            txtDescripcion.setText(objC.getDescripcionCategoria());
        }
        this.setTitle("Registrar categoria");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Descripcion");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objC.setNomCategoria(txtNombre.getText());
            objC.setDescripcionCategoria(txtDescripcion.getText());
            if (objC.getIdCategoria() > 0)
                objC.UPDATE();
            else
                objC.INSERT();
            tblCategoria.setItems(objC.SELECT());
            tblCategoria.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtDescripcion,btnGuardar);
        escena = new Scene(vBox);
    }
}
