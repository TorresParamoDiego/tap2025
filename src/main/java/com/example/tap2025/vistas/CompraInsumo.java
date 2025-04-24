package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.CompraInsumosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompraInsumo extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtIdInsumo,txtFecha,txtCantidad;
    private Scene escena;
    private CompraInsumosDAO obj;
    private TableView tblCompraInsumos;
    public CompraInsumo(TableView tblCompraInsumos, CompraInsumosDAO obj) {
        this.tblCompraInsumos = tblCompraInsumos;
        creaUI();
        if(obj == null) {
            this.obj = new CompraInsumosDAO();
        }
        else {
            this.obj = obj;
            txtIdInsumo.setText(String.valueOf(obj.getIdInsumo()));
            txtCantidad.setText(String.valueOf(obj.getCantidadInsumo()));
            txtFecha.setText(obj.getFechCompra());
        }
        this.setTitle("Registrar los productos de la orden");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtIdInsumo=new TextField();
        txtIdInsumo.setPromptText("ID del insumo");
        txtFecha=new TextField();
        txtFecha.setPromptText("Fecha");
        txtCantidad=new TextField();
        txtCantidad.setPromptText("Cantidad");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setIdInsumo(Integer.parseInt(txtIdInsumo.getText()));
            obj.setFechCompra(txtFecha.getText());
            obj.setCantidadInsumo(Integer.parseInt(txtCantidad.getText()));
            if(obj.getIdCompra()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblCompraInsumos.setItems(obj.SELECT());
            tblCompraInsumos.refresh();
            this.close();
        });
        vBox = new VBox(txtIdInsumo,txtCantidad,txtFecha,txtCantidad,btnGuardar);
        escena = new Scene(vBox);
    }
}
