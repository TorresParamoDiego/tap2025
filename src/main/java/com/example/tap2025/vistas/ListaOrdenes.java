package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.OrdenDAO;
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

public class ListaOrdenes extends Stage {
    private ToolBar tlbMenu;
    private TableView<OrdenDAO> tblOrdenes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaOrdenes() {
        creaUI();
        this.setTitle("Lista de Ordenes");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblOrdenes = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Orden(tblOrdenes,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblOrdenes);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        OrdenDAO objC = new OrdenDAO();
        TableColumn<OrdenDAO,Integer> tbcIdOrden= new TableColumn<>("Id");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        TableColumn<OrdenDAO,Float> tbcPrecio= new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precioOrden"));
        TableColumn<OrdenDAO,Integer> tbcIdCte= new TableColumn<>("Id cliente");
        tbcIdCte.setCellValueFactory(new PropertyValueFactory<>("idCte"));
        TableColumn<OrdenDAO,Integer> tbcIdEmpl= new TableColumn<>("Id de empleado");
        tbcIdEmpl.setCellValueFactory(new PropertyValueFactory<>("idEmpl"));
        TableColumn<OrdenDAO,Integer> tbcIdMesa= new TableColumn<>("No. Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<OrdenDAO,String> tbcFechOrden= new TableColumn<>("Fecha");
        tbcFechOrden.setCellValueFactory(new PropertyValueFactory<>("fechHora"));
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
        tblOrdenes.getColumns().addAll(tbcIdOrden,tbcIdCte,tbcIdEmpl,tbcIdMesa,tbcFechOrden,tbcPrecio,tbcEditar,tbcEliminar);
        tblOrdenes.setItems(objC.SELECT());
    }
}
