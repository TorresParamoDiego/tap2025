package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.DetalleOrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaDetalleOrdenes extends Stage {
    private ToolBar tlbMenu;
    private TableView<DetalleOrdenDAO> tblDetalleOrdenes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaDetalleOrdenes() {
        creaUI();
        this.setTitle("Lista de Detalle de los productos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblDetalleOrdenes = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new DetalleOrden(tblDetalleOrdenes,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblDetalleOrdenes);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        DetalleOrdenDAO objC = new DetalleOrdenDAO();
        TableColumn<DetalleOrdenDAO,Integer> tbcIdOrden= new TableColumn<>("Id Orden");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<DetalleOrdenDAO,Integer> tbcIdProducto= new TableColumn<>("Id Producto");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        TableColumn<DetalleOrdenDAO,Integer> tbcCantidad= new TableColumn<>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn tbcEditar= new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn tableColumn) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn tbcEliminar= new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn tableColumn) {
                return new ButtonCell("Eliminar");
            }
        });
        tblDetalleOrdenes.getColumns().addAll(tbcIdOrden,tbcIdProducto,tbcCantidad,tbcEditar,tbcEliminar);
        tblDetalleOrdenes.setItems(objC.SELECT());
    }
}
