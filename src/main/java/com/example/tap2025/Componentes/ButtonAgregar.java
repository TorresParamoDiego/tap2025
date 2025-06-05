package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

import java.util.LinkedList;

public class ButtonAgregar extends TableCell<ProductoDAO,String> {
    Button boton;
    LinkedList<Integer> array;
    OrdenDAO orden;
    TableView tabla;
    public ButtonAgregar(LinkedList<Integer> array, OrdenDAO orden,String lblBoton,TableView<ProductoOrden> table,Label lblPrecio) {
        super();
        this.array = array;
        boton = new Button(lblBoton);
        this.orden = orden;
        this.tabla = table;
        boton.setOnAction(e -> {
            if(lblBoton.equals("Agregar"))
                agregarProducto();
            else
                eliminarProducto();
            calcularPrecio(lblPrecio);
        });
    }
    private void calcularPrecio(Label lblPrecio) {
        double precio = 0;
        for(int i=0;i<tabla.getItems().size();i++){
            ProductoOrden prod= (ProductoOrden) tabla.getItems().get(i);
            precio+=prod.getPrecioProd()*prod.getCantidad();
        }
        lblPrecio.setText("Total: $"+precio);
    }
    private void agregarProducto() {
        boolean bandera=false;
        ProductoOrden obj=null;
        int cont=0;
        ProductoDAO prod=this.getTableView().getItems().get(getIndex());
        array.add(getTableView().getItems().get(getIndex()).getIdProducto());

        for(int i=0;i<array.size()&&!bandera;i++){
            if(array.get(i)==prod.getIdProducto()){
                cont++;
                if(cont==2)
                    bandera=true;
            }
        }
        if(!bandera) {
            obj = new ProductoOrden();
            obj.setIdProducto(prod.getIdProducto());
            obj.setCantidad(1);
            obj.setCostoProd(prod.getCostoProd());
            obj.setPrecioProd(prod.getPrecioProd());
            obj.setUrlImagenProd(prod.getUrlImagenProd());
            obj.setIdCategoria(prod.getIdCategoria());
            obj.setNomProd(prod.getNomProd());
            tabla.getItems().add(obj);

        }
        else {
            for(int i=0;i<tabla.getItems().size();i++){
                obj =(ProductoOrden) tabla.getItems().get(i);
                if (obj.getIdProducto()==prod.getIdProducto()){
                    obj.setCantidad(obj.getCantidad()+1);
                }
            }
        }
        orden.UPDATE();
        tabla.refresh();
    }
    private void eliminarProducto() {
        ProductoOrden obj=(ProductoOrden) tabla.getItems().get(getIndex());
        array.remove((Integer) getTableView().getItems().get(getIndex()).getIdProducto());
        obj.setCantidad(obj.getCantidad()-1);
        if(obj.getCantidad()==0){
            tabla.getItems().remove(getIndex());
        }
        orden.UPDATE();

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
