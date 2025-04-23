package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.ReservacionMesaDAO;
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

public class ListaReservacionMesas extends Stage {
    private ToolBar tlbMenu;
    private TableView<ReservacionMesaDAO> tblReservacionMesas;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaReservacionMesas() {
        creaUI();
        this.setTitle("Lista de mesas en reservacion");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblReservacionMesas = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new ReservacionMesa(tblReservacionMesas,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblReservacionMesas);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        ReservacionMesaDAO objC = new ReservacionMesaDAO();
        TableColumn<ReservacionMesaDAO, Integer> tbcIdReservacion = new TableColumn<>("Id reservacion");
        tbcIdReservacion.setCellValueFactory(new PropertyValueFactory<>("idReservacion"));
        TableColumn<ReservacionMesaDAO,Integer> tbcIdMesa= new TableColumn<>("No mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
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
        tblReservacionMesas.getColumns().addAll(tbcIdReservacion,tbcIdMesa,tbcEditar,tbcEliminar);
        tblReservacionMesas.setItems(objC.SELECT());
    }
}
