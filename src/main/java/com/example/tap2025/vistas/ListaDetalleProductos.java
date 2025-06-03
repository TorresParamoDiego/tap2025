package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.DetalleProductoDAO;
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

public class ListaDetalleProductos extends Stage {
    private ToolBar tlbMenu;
    private TableView<DetalleProductoDAO> tblDetalleProductos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaDetalleProductos() {
        creaUI();
        this.setTitle("Lista de Detalle de los productos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblDetalleProductos = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new DetalleProducto(tblDetalleProductos,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblDetalleProductos);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        DetalleProductoDAO objC = new DetalleProductoDAO();
        TableColumn<DetalleProductoDAO,Integer> tbcIdInsumo= new TableColumn<>("Id Insumo");
        tbcIdInsumo.setCellValueFactory(new PropertyValueFactory<>("idInsumo"));
        TableColumn<DetalleProductoDAO,Integer> tbcIdProducto= new TableColumn<>("Id Producto");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

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
        tblDetalleProductos.getColumns().addAll(tbcIdInsumo,tbcIdProducto,tbcEditar,tbcEliminar);
        tblDetalleProductos.setItems(objC.SELECT());
    }

}
