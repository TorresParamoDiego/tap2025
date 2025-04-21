package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.*;
import com.example.tap2025.vistas.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell extends TableCell<Object,String>{

    private Button btnCelda;
    private String strLableBtn;
    public ButtonCell(String label) {
        strLableBtn = label;
        btnCelda = new Button(strLableBtn);
        btnCelda.setOnAction(event -> {eventoBoton();});
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(btnCelda);
        }
    }
    private void eventoBoton(){
        Object obj=this.getTableView().getItems().get(this.getIndex());
        ObservableList<Object> lista=null;
        if(strLableBtn.equals("Editar")){
            if(obj instanceof ClientesDAO objC) {
                new Cliente(this.getTableView(), objC);
                lista = FXCollections.observableArrayList(objC.SELECT());
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("¿Estás seguro de borrar este registro?");
            Optional<ButtonType> opcion = alert.showAndWait();
            if(opcion.get() == ButtonType.OK){
                if(obj instanceof ClientesDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof CategoriaDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof DetalleOrdenDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof OrdenDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof DetalleProductoDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof EmpleadoDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof InsumoDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof MesaDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof ProveedorDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                }
                else if (obj instanceof ProductoDAO objC){
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof PuestoDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof ReservacionDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                } else if (obj instanceof ReservacionMesaDAO objC) {
                    objC.DELETE();
                    lista = FXCollections.observableArrayList(objC.SELECT());
                }
            }
        }
        this.getTableView().setItems(lista);
        this.getTableView().refresh();
    }

}
/*
ClientesDAO objC=this.getTableView().getItems().get(this.getIndex()); para
* recuperar el objeto de la posicion donde esta el boton
 */