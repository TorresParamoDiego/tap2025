package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.DetalleOrdenDAO;
import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.Selectores;
import com.example.tap2025.vistas.Restaurante;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

import java.util.LinkedList;

public class ButtonAgregar extends TableCell<ProductoDAO,String> {
    Button boton;
    LinkedList<Integer> array;
    OrdenDAO orden;
    public ButtonAgregar(LinkedList<Integer> array, OrdenDAO orden,String lblBoton,TableView<ProductoDAO> table) {
        super();
        this.array = array;
        boton = new Button(lblBoton);
        this.orden = orden;
        boton.setOnAction(e -> {
            if(lblBoton.equals("Agregar"))
                agregarProducto(table);
            else
                eliminarProducto(table);
        });
    }
    private void agregarProducto(TableView tabla) {
        array.add(getTableView().getItems().get(getIndex()).getIdProducto());
        orden.setPrecioOrden((float)Selectores.calcPrecioOrden(orden.getIdOrden()));
        orden.UPDATE();
        tabla.getItems().add(this.getTableView().getItems().get(getIndex()));
        tabla.refresh();
    }
    private void eliminarProducto(TableView tabla) {
        array.remove((Integer) getTableView().getItems().get(getIndex()).getIdProducto());
        orden.setPrecioOrden((float)Selectores.calcPrecioOrden(orden.getIdOrden()));
        orden.UPDATE();
        tabla.getItems().remove(this.getTableView().getItems().get(getIndex()));
        tabla.refresh();
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(boton);
        }
    }

}
