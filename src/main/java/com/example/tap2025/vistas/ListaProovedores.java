package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.ProveedorDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaProovedores extends Stage {
    private ToolBar tlbMenu;
    private TableView<ProveedorDAO> tblProveedores;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaProovedores() {
        creaUI();
        this.setTitle("Lista de Proveedores");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblProveedores = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Proovedor(tblProveedores,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblProveedores);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        ProveedorDAO objC = new ProveedorDAO();
        TableColumn<ProveedorDAO,Integer> tbcIdProveedor= new TableColumn<>("Id");
        tbcIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        TableColumn<ProveedorDAO,String> tbcNombreProveedor= new TableColumn<>("Nombre");
        tbcNombreProveedor.setCellValueFactory(new PropertyValueFactory<>("nomProv"));
        TableColumn<ProveedorDAO,String> tbcDescripcionProv= new TableColumn<>("Descripcion");
        tbcDescripcionProv.setCellValueFactory(new PropertyValueFactory<>("descripcionProv"));
        TableColumn<ProveedorDAO,String> tbcEmailProv= new TableColumn<>("Email");
        tbcEmailProv.setCellValueFactory(new PropertyValueFactory<>("emailProv"));
        TableColumn<ProveedorDAO,String> tbcDireccionProv= new TableColumn<>("Direccion");
        tbcDireccionProv.setCellValueFactory(new PropertyValueFactory<>("direccionProv"));

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
        tblProveedores.getColumns().addAll(tbcIdProveedor,tbcNombreProveedor,tbcDescripcionProv,tbcEmailProv,tbcDireccionProv,tbcEditar,tbcEliminar);
        tblProveedores.setItems(objC.SELECT());
    }
}
