package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.MetodosPagoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MetodoPago extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre;
    private TextArea txtDescripcion;
    private Scene escena;
    private MetodosPagoDAO objC;
    private TableView tblMetodo;
    public MetodoPago(TableView tblMetodo, MetodosPagoDAO objC) {
        this.tblMetodo = tblMetodo;
        creaUI();
        if(objC == null) {
            this.objC = new MetodosPagoDAO();
        }
        else {
            this.objC = objC;
            txtNombre.setText(objC.getTipoMetodo());
            txtDescripcion.setText(objC.getDescripcion());
        }
        this.setTitle("Registrar Metodo de pago");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Tipo de pago");
        txtDescripcion = new TextArea();
        txtDescripcion.setPromptText("Descripcion");

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objC.setTipoMetodo(txtNombre.getText());
            objC.setDescripcion(txtDescripcion.getText());
            if (objC.getIdMetodoPago() > 0)
                objC.UPDATE();
            else
                objC.INSERT();
            tblMetodo.setItems(objC.SELECT());
            tblMetodo.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtDescripcion,btnGuardar);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
}
