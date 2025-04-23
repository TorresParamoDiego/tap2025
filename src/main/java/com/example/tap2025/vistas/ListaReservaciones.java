package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.ReservacionDAO;
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

public class ListaReservaciones extends Stage {
    private ToolBar tlbMenu;
    private TableView<ReservacionDAO> tblReservaciones;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaReservaciones() {
        creaUI();
        this.setTitle("Lista de Reservaciones");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblReservaciones = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Reservacion(tblReservaciones,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblReservaciones);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        ReservacionDAO objC = new ReservacionDAO();
        TableColumn<ReservacionDAO, Integer> tbcIdReservacion = new TableColumn<>("ID");
        tbcIdReservacion.setCellValueFactory(new PropertyValueFactory<>("idReservacion"));
        TableColumn<ReservacionDAO, String> tbcHoraRese = new TableColumn<>("Horario y fecha");
        tbcHoraRese.setCellValueFactory(new PropertyValueFactory<>("horarioRese"));
        TableColumn<ReservacionDAO, Integer> tbcDuracionRese = new TableColumn<>("Duracion");
        tbcDuracionRese.setCellValueFactory(new PropertyValueFactory<>("duracionRese"));
        TableColumn<ReservacionDAO,Integer> tbcidCte = new TableColumn<>("Id Cliente");
        tbcidCte.setCellValueFactory(new PropertyValueFactory<>("idCte"));
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
        tblReservaciones.getColumns().addAll(tbcIdReservacion,tbcHoraRese,tbcDuracionRese,tbcidCte,tbcEditar,tbcEliminar);
        tblReservaciones.setItems(objC.SELECT());
    }
}
