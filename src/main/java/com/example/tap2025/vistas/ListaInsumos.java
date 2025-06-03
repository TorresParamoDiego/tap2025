package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.InsumoDAO;
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

public class ListaInsumos extends Stage {
    private ToolBar tlbMenu;
    private TableView<InsumoDAO> tblInsumos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaInsumos() {
        creaUI();
        this.setTitle("Lista de Detalle de los insumos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblInsumos = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Insumo(tblInsumos,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblInsumos);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        InsumoDAO objC = new InsumoDAO();
        TableColumn<InsumoDAO,Integer> tbcIdInsumo= new TableColumn<>("Id");
        tbcIdInsumo.setCellValueFactory(new PropertyValueFactory<>("idInsumo"));
        TableColumn<InsumoDAO,String> tbcNomIns= new TableColumn<>("Nombre");
        tbcNomIns.setCellValueFactory(new PropertyValueFactory<>("nomIns"));
        TableColumn<InsumoDAO,Float> tbcPrecioInsumo= new TableColumn<>("Precio");
        tbcPrecioInsumo.setCellValueFactory(new PropertyValueFactory<>("precioIns"));
        TableColumn<InsumoDAO,Integer> tbcIdProveedor= new TableColumn<>("Proveedor");
        tbcIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
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
        tblInsumos.getColumns().addAll(tbcIdInsumo,tbcNomIns,tbcPrecioInsumo,tbcIdProveedor,tbcEditar,tbcEliminar);
        tblInsumos.setItems(objC.SELECT());
    }
}
