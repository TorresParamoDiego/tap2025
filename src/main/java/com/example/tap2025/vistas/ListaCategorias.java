package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.ClientesDAO;
import javafx.application.Application;
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

public class ListaCategorias extends Stage {
    private ToolBar tlbMenu;
    private TableView<CategoriaDAO> tblCategorias;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaCategorias() {
        creaUI();
        this.setTitle("Lista de Categorias");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblCategorias = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Categoria(tblCategorias,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblCategorias);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        CategoriaDAO objC = new CategoriaDAO();
        TableColumn<CategoriaDAO,String> tbcNomCategoria= new TableColumn<>("Nombre");
        tbcNomCategoria.setCellValueFactory(new PropertyValueFactory<>("nomCategoria"));
        TableColumn<CategoriaDAO,String> tbcDescripcion= new TableColumn<>("Descripcion");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionCategoria"));

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
        tblCategorias.getColumns().addAll(tbcNomCategoria,tbcDescripcion,tbcEditar,tbcEliminar);
        tblCategorias.setItems(objC.SELECT());
    }
}
