package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.InsumoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Insumo extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre,txtPrecio,txtIdProveedor;
    private Scene escena;
    private InsumoDAO obj;
    private TableView tblInsumo;
    public Insumo(TableView tblInsumo, InsumoDAO obj) {
        this.tblInsumo = tblInsumo;
        creaUI();
        if(obj == null) {
            this.obj = new InsumoDAO();
        }
        else {
            this.obj = obj;
            txtNombre.setText(obj.getNomIns());
            txtPrecio.setText(String.valueOf(obj.getPrecioIns()));
            txtIdProveedor.setText(String.valueOf(obj.getIdProveedor()));
        }
        this.setTitle("Registrar los insumos del producto");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        txtIdProveedor = new TextField();
        txtIdProveedor.setPromptText("Id Proveedor");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setNomIns(txtNombre.getText());
            obj.setPrecioIns(Float.parseFloat(txtPrecio.getText()));
            obj.setIdProveedor(Integer.parseInt(txtIdProveedor.getText()));
            if(obj.getIdInsumo()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblInsumo.setItems(obj.SELECT());
            tblInsumo.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtPrecio,txtIdProveedor,btnGuardar);
        escena = new Scene(vBox);
    }
}
