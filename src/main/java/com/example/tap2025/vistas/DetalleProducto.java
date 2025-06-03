package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.DetalleProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalleProducto extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtIdInsumo,txtIdProducto;
    private Scene escena;
    private DetalleProductoDAO obj;
    private TableView tblDetalleProducto;
    public DetalleProducto(TableView tblDetalleProducto, DetalleProductoDAO obj) {
        this.tblDetalleProducto = tblDetalleProducto;
        creaUI();
        if(obj == null) {
            this.obj = new DetalleProductoDAO();
        }
        else {
            this.obj = obj;
            txtIdProducto.setText(String.valueOf(obj.getIdProducto()));
            txtIdInsumo.setText(String.valueOf(obj.getIdInsumo()));
        }
        this.setTitle("Registrar los insumos del producto");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtIdProducto = new TextField();
        txtIdProducto.setPromptText("Id Producto");
        txtIdInsumo = new TextField();
        txtIdInsumo.setPromptText("Id Insumo");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setIdProducto(Integer.parseInt(txtIdProducto.getText()));
            obj.setIdInsumo(Integer.parseInt(txtIdInsumo.getText()));
            if(obj.getIdProducto()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblDetalleProducto.setItems(obj.SELECT());
            tblDetalleProducto.refresh();
            this.close();
        });
        vBox = new VBox(txtIdProducto,txtIdInsumo,btnGuardar);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }

}
