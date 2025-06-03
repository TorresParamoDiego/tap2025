package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.MesaDAO;
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

public class ListaMesas extends Stage {
    private ToolBar tlbMenu;
    private TableView<MesaDAO> tblMesas;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaMesas() {
        creaUI();
        this.setTitle("Lista de Mesas");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblMesas = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Mesa(tblMesas,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblMesas);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        MesaDAO objC = new MesaDAO();
        TableColumn<MesaDAO,Integer> tbcIdMesa= new TableColumn<>("Id");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
        TableColumn<MesaDAO,Integer> tbcCapacidad= new TableColumn<>("Capacidad");
        tbcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadMesa"));
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
        tblMesas.getColumns().addAll(tbcIdMesa,tbcCapacidad,tbcEditar,tbcEliminar);
        tblMesas.setItems(objC.SELECT());
    }
}
