package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.Selectores;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import java.util.LinkedList;

public class ButtonAgregar extends TableCell<ProductoDAO,String> {
    Button boton;
    LinkedList<Integer> array;
    OrdenDAO orden;
    public ButtonAgregar(LinkedList<Integer> array, OrdenDAO orden) {
        super();
        this.array = array;
        boton = new Button();
        this.orden = orden;
        boton.setOnAction(e -> {agregarProducto();});
    }
    private void agregarProducto() {
        array.add(getTableView().getItems().get(getIndex()).getIdProducto());
        orden.setPrecioOrden((float)Selectores.calcPrecioOrden(orden.getIdOrden()));
        orden.UPDATE();
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(boton);
        }
    }
}
