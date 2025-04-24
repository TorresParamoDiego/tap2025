package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.PuestoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Puesto extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre,txtSueldo;
    private TextArea txtDescripcion;
    private Scene escena;
    private PuestoDAO obj;
    private TableView tblPuestos;
    public Puesto(TableView tblPuestos, PuestoDAO obj) {
        this.tblPuestos = tblPuestos;
        creaUI();
        if(obj == null) {
            this.obj = new PuestoDAO();
        }
        else {
            this.obj = obj;
            txtNombre.setText(obj.getNomPuesto());
            txtDescripcion.setText(String.valueOf(obj.getSueldoPuesto()));
            txtDescripcion.setText(obj.getDescripcion());
        }
        this.setTitle("Registrar los puestos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtSueldo = new TextField();
        txtSueldo.setPromptText("Sueldo");
        txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Descripcion");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setNomPuesto(txtNombre.getText());
            obj.setDescripcion(txtDescripcion.getText());
            obj.setSueldoPuesto(Integer.parseInt(txtSueldo.getText()));
            if(obj.getIdPuesto()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblPuestos.setItems(obj.SELECT());
            tblPuestos.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtSueldo,txtDescripcion,btnGuardar);
        escena = new Scene(vBox);
    }
}
