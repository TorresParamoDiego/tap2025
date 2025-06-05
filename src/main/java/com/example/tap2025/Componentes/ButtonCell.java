package com.example.tap2025.Componentes;


import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.ClientesDAO;
import com.example.tap2025.Modelos.CompraInsumosDAO;
import com.example.tap2025.Modelos.DetalleOrdenDAO;
import com.example.tap2025.Modelos.DetalleProductoDAO;
import com.example.tap2025.Modelos.EmpleadoDAO;
import com.example.tap2025.Modelos.InsumoDAO;
import com.example.tap2025.Modelos.MesaDAO;
import com.example.tap2025.Modelos.MetodosPagoDAO;
import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.ProveedorDAO;
import com.example.tap2025.Modelos.PuestoDAO;
import com.example.tap2025.Modelos.ReservacionDAO;
import com.example.tap2025.Modelos.ReservacionMesaDAO;
import com.example.tap2025.vistas.Categoria;
import com.example.tap2025.vistas.Cliente;
import com.example.tap2025.vistas.CompraInsumo;
import com.example.tap2025.vistas.DetalleOrden;
import com.example.tap2025.vistas.DetalleProducto;
import com.example.tap2025.vistas.Empleado;
import com.example.tap2025.vistas.Insumo;
import com.example.tap2025.vistas.Mesa;
import com.example.tap2025.vistas.MetodoPago;
import com.example.tap2025.vistas.Orden;
import com.example.tap2025.vistas.Producto;
import com.example.tap2025.vistas.Proovedor;
import com.example.tap2025.vistas.Puesto;
import com.example.tap2025.vistas.Reservacion;
import com.example.tap2025.vistas.ReservacionMesa;
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
        int control=0;
        ObservableList<Object> lista=null;
        ClientesDAO objC=null;
        CategoriaDAO objC2=null;
        CompraInsumosDAO objC3=null;
        DetalleProductoDAO objDP=null;
        DetalleOrdenDAO objDOR=null;
        EmpleadoDAO objE=null;
        MesaDAO objM=null;
        InsumoDAO objI=null;
        OrdenDAO objO=null;
        ProductoDAO objP=null;
        ReservacionDAO objR=null;
        ReservacionMesaDAO objRM=null;
        PuestoDAO objPU=null;
        ProveedorDAO objPV=null;
        MetodosPagoDAO objM1=null;
        if(obj instanceof ClientesDAO) {
            objC=(ClientesDAO)obj;
            control=1;
            lista = FXCollections.observableArrayList(objC.SELECT());
        } else if (obj instanceof CategoriaDAO) {
            objC2=(CategoriaDAO)obj;
            control=2;
            lista = FXCollections.observableArrayList(objC2.SELECT());
        } else if (obj instanceof DetalleOrdenDAO ) {
            objDOR=(DetalleOrdenDAO)obj;
            control=3;
            lista = FXCollections.observableArrayList(objDOR.SELECT());
        } else if (obj instanceof OrdenDAO ) {
            objO=(OrdenDAO)obj;
            control=4;
            lista = FXCollections.observableArrayList(objO.SELECT());
        } else if (obj instanceof DetalleProductoDAO ) {
            objDP=(DetalleProductoDAO)obj;
            control=5;
            lista = FXCollections.observableArrayList(objDP.SELECT());
        } else if (obj instanceof EmpleadoDAO ) {
            objE=(EmpleadoDAO)obj;
            control=6;
            lista = FXCollections.observableArrayList(objE.SELECT());
        } else if (obj instanceof InsumoDAO ) {
            objI=(InsumoDAO)obj;
            control=7;
            lista = FXCollections.observableArrayList(objI.SELECT());
        } else if (obj instanceof MesaDAO ) {
            objM=(MesaDAO)obj;
            control=8;
            lista = FXCollections.observableArrayList(objM.SELECT());
        } else if (obj instanceof ProveedorDAO ) {
            objPV=(ProveedorDAO)obj;
            control=9;
            lista = FXCollections.observableArrayList(objPV.SELECT());
        } else if (obj instanceof ProductoDAO ){
            objP=(ProductoDAO)obj;
            control=10;
            lista = FXCollections.observableArrayList(objP.SELECT());
        } else if (obj instanceof PuestoDAO ) {
            objPU=(PuestoDAO)obj;
            control=11;
            lista = FXCollections.observableArrayList(objPU.SELECT());
        } else if (obj instanceof ReservacionDAO ) {
            objR=(ReservacionDAO)obj;
            control=12;
            lista = FXCollections.observableArrayList(objR.SELECT());
        } else if (obj instanceof ReservacionMesaDAO ) {
            objRM=(ReservacionMesaDAO)obj;
            control=13;
            lista = FXCollections.observableArrayList(objRM.SELECT());
        } else if (obj instanceof CompraInsumosDAO ) {
            objC3=(CompraInsumosDAO)obj;
            control=14;
            lista = FXCollections.observableArrayList(objC3.SELECT());
        } else if (obj instanceof MetodosPagoDAO) {
            objM1=(MetodosPagoDAO) obj;
            control=15;
            lista = FXCollections.observableArrayList(objM1.SELECT());

        }

        if(strLableBtn.equals("Editar")){
            switch (control){
                case 1->  new Cliente(this.getTableView(), objC);
                case 2->  new Categoria(this.getTableView(), objC2);
                case 3-> new DetalleOrden(this.getTableView(), objDOR);
                case 4->  new Orden(this.getTableView(),objO);
                case 5->  new DetalleProducto(this.getTableView(), objDP);
                case 6->  new Empleado(this.getTableView(), objE);
                case 7->  new Insumo(this.getTableView(), objI);
                case 8-> new Mesa(this.getTableView(), objM);
                case 9-> new Proovedor(this.getTableView(), objPV);
                case 10->  new Producto(this.getTableView(), objP);
                case 11 -> new Puesto(this.getTableView(), objPU);
                case 12->  new Reservacion(this.getTableView(), objR);
                case 13->  new ReservacionMesa(this.getTableView(), objRM);
                case 14->  new CompraInsumo(this.getTableView(), objC3);
                case 15-> new MetodoPago(this.getTableView(),objM1);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("¿Estás seguro de borrar este registro?");
            Optional<ButtonType> opcion = alert.showAndWait();
            if(opcion.get() == ButtonType.OK){
                switch (control){
                    case 1->{
                        objC.DELETE();
                        lista=FXCollections.observableArrayList(objC.SELECT());
                    }
                    case 2-> {
                        objC2.DELETE();
                        lista=FXCollections.observableArrayList(objC2.SELECT());
                    }
                    case 3->  {
                        objDOR.DELETE();
                        lista=FXCollections.observableArrayList(objDOR.SELECT());
                    }
                    case 4->  {
                        objO.DELETE();
                        lista=FXCollections.observableArrayList(objO.SELECT());
                    }
                    case 5->{
                        objDP.DELETE();
                        lista=FXCollections.observableArrayList(objDP.SELECT());
                    }
                    case 6->  {
                        objE.DELETE();
                        lista=FXCollections.observableArrayList(objE.SELECT());
                    }
                    case 7->  {
                        objI.DELETE();
                        lista=FXCollections.observableArrayList(objI.SELECT());
                    }
                    case 8-> {
                        objM.DELETE();
                        lista=FXCollections.observableArrayList(objM.SELECT());
                    }
                    case 9->  {
                        objPV.DELETE();
                        lista=FXCollections.observableArrayList(objPV.SELECT());
                    }
                    case 10-> {
                        objP.DELETE();
                        lista=FXCollections.observableArrayList(objP.SELECT());
                    }
                    case 11-> {
                        objPU.DELETE();
                        lista=FXCollections.observableArrayList(objPU.SELECT());
                    }
                    case 12->  {
                        objR.DELETE();
                        lista=FXCollections.observableArrayList(objR.SELECT());
                    }
                    case 13-> {
                        objRM.DELETE();
                        lista=FXCollections.observableArrayList(objRM.SELECT());
                    }
                    case 14->{
                        objC3.DELETE();
                        lista=FXCollections.observableArrayList(objC3.SELECT());
                    }
                    case 15->{
                        objM1.DELETE();
                        lista=FXCollections.observableArrayList(objM1.SELECT());
                    }
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