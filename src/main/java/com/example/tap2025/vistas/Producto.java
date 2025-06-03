package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Producto extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre,txtPrecio,txtCosto,txtUrlImagen,txtCategoria;
    private Scene escena;
    private ProductoDAO obj;
    private TableView tblProducto;
    public Producto(TableView tblProducto, ProductoDAO obj) {
        this.tblProducto = tblProducto;
        creaUI();
        if(obj == null) {
            this.obj = new ProductoDAO();
        }
        else {
            this.obj = obj;
            txtNombre.setText(obj.getNomProd());
            txtPrecio.setText(String.valueOf(obj.getPrecioProd()));
            txtCosto.setText(String.valueOf(obj.getCostoProd()));
            txtUrlImagen.setText(obj.getUrlImagenProd());
            txtCategoria.setText(String.valueOf(obj.getIdCategoria()));
        }
        this.setTitle("Registrar los productos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        txtCosto = new TextField();
        txtCosto.setPromptText("Costo");
        txtUrlImagen = new TextField();
        txtUrlImagen.setPromptText("UrlImagen");
        txtCategoria = new TextField();
        txtCategoria.setPromptText("Id Categoria");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setNomProd(txtNombre.getText());
            obj.setPrecioProd(Float.parseFloat(txtPrecio.getText()));
            obj.setCostoProd(Float.parseFloat(txtCosto.getText()));
            obj.setUrlImagenProd(txtUrlImagen.getText());
            obj.setIdCategoria(Integer.parseInt(txtCategoria.getText()));
            if(obj.getIdProducto()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblProducto.setItems(obj.SELECT());
            tblProducto.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtPrecio,txtCosto,txtUrlImagen,txtCategoria,btnGuardar);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
}
