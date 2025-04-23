package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.DetalleOrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalleOrden extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtIdOrden,txtIdProducto,txtCantidad;
    private Scene escena;
    private DetalleOrdenDAO obj;
    private TableView tblCategoria;
    public DetalleOrden(TableView tblDetalleOrden, DetalleOrdenDAO obj) {
        this.tblCategoria = tblCategoria;
        creaUI();
        if(obj == null) {
            this.obj = new DetalleOrdenDAO();
        }
        else {
            this.obj = obj;
            txtIdOrden.setText(String.valueOf(obj.getIdOrden()));
            txtIdProducto.setText(String.valueOf(obj.getIdProducto()));
        }
        this.setTitle("Registrar los productos de la orden");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtIdOrden = new TextField();
        txtIdOrden.setPromptText("ID Orden");
        txtIdProducto = new TextField();
        txtIdProducto.setPromptText("ID Producto");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setIdOrden(Integer.parseInt(txtIdOrden.getText()));
            obj.setIdProducto(Integer.parseInt(txtIdProducto.getText()));
            if(obj.getIdOrden()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblCategoria.setItems(obj.SELECT());
            tblCategoria.refresh();
            this.close();
        });
        vBox = new VBox(txtIdOrden,txtIdProducto,txtCantidad,btnGuardar);
        escena = new Scene(vBox);
    }
}
