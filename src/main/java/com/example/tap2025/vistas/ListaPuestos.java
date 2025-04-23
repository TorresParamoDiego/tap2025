package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.PuestoDAO;
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

public class ListaPuestos extends Stage {
    private ToolBar tlbMenu;
    private TableView<PuestoDAO> tblPuestos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaPuestos() {
        creaUI();
        this.setTitle("Lista de Puestos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblPuestos = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Mesa(tblPuestos,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblPuestos);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        PuestoDAO objC = new PuestoDAO();
        TableColumn<PuestoDAO,Integer> tbcIdPuesto= new TableColumn<>("Id");
        tbcIdPuesto.setCellValueFactory(new PropertyValueFactory<>("idPuesto"));
        TableColumn<PuestoDAO,String> tbcNombre= new TableColumn<>("Nombre");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nomPuesto"));
        TableColumn<PuestoDAO,String> tbcDescripcion= new TableColumn<>("Descripcion");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TableColumn<PuestoDAO,Float> tbcSueldo= new TableColumn<>("Sueldo");
        tbcSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldoPuesto"));

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
        tblPuestos.getColumns().addAll(tbcIdPuesto,tbcNombre,tbcDescripcion,tbcSueldo,tbcEditar,tbcEliminar);
        tblPuestos.setItems(objC.SELECT());
    }
}
