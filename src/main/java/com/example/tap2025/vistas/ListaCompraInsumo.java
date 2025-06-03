package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.CompraInsumosDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ListaCompraInsumo extends Stage {
    private ToolBar tlbMenu;
    private TableView<CompraInsumosDAO> tblCompraInsumos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaCompraInsumo() {
        creaUI();
        this.setTitle("Lista de compras");
        this.setScene(escena);
        this.show();
    }
    private void creaUI() {
        tblCompraInsumos = new TableView<>();
        btnAgregar = new Button();
        btnAgregar.setOnAction(event -> new CompraInsumo(tblCompraInsumos,null));
        btnAgregar.setGraphic(new ImageView(getClass().getResource("/Images/boton.jpg").toString()));
        tlbMenu = new ToolBar(btnAgregar);
        crearTabla();
        vBox = new VBox(tlbMenu,tblCompraInsumos);
        escena = new Scene(vBox,600,500);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void crearTabla(){
        CompraInsumosDAO objC = new CompraInsumosDAO();
        TableColumn<CompraInsumosDAO, Integer> tbcIdCompra = new TableColumn<>("ID");
        tbcIdCompra.setCellValueFactory(new PropertyValueFactory<>("idCompra"));
        TableColumn<CompraInsumosDAO, Integer> tbcIdInsumo = new TableColumn<>("ID insumo");
        tbcIdInsumo.setCellValueFactory(new PropertyValueFactory<>("idInsumo"));
        TableColumn<CompraInsumosDAO,Integer> tbcCantidad = new TableColumn<>("Cantidad");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadInsumo"));
        TableColumn<CompraInsumosDAO,String> tbcfecha = new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("fechCompra"));
        TableColumn<CompraInsumosDAO,Float> tbcCosto = new TableColumn<>("Costo");
        tbcCosto.setCellValueFactory(new PropertyValueFactory<>("costoCompra"));
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

        tblCompraInsumos.getColumns().addAll(tbcIdCompra,tbcIdInsumo,tbcCantidad,tbcfecha,tbcCosto,tbcEditar,tbcEliminar);
        tblCompraInsumos.setItems(objC.SELECT());


    }
}
