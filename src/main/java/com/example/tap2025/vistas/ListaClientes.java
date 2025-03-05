package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ClientesDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaClientes extends Stage {

    private ToolBar tlbMenu;
    private TableView<ClientesDAO> tblClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaClientes() {
        creaUI();
        this.setTitle("Lista de Clientes -3¬");
        this.setScene(escena);
        this.show();
    }
    private void creaUI() {
        tblClientes = new TableView<>();
        btnAgregar = new Button();
        btnAgregar.setOnAction(event -> new Cliente(tblClientes));
        btnAgregar.setGraphic(new ImageView(getClass().getResource("/Images/boton.jpg").toString()));
        tlbMenu = new ToolBar(btnAgregar);
        crearTabla();
        vBox = new VBox(tlbMenu,tblClientes);
        escena = new Scene(vBox,500,500);
    }
    private void crearTabla(){
        ClientesDAO objC = new ClientesDAO();
        TableColumn<ClientesDAO,String> tbcNomCte= new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte")); //se toma el nombre del atributo de la clase

        TableColumn<ClientesDAO,String> tbcDireccion= new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<ClientesDAO,String> tbcTelCte= new TableColumn<>("Telefono");
        tbcTelCte.setCellValueFactory(new PropertyValueFactory<>("telCte"));

        TableColumn<ClientesDAO,String> tbcEmailCte= new TableColumn<>("Email");
        tbcEmailCte.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        tblClientes.getColumns().addAll(tbcNomCte,tbcDireccion,tbcTelCte,tbcEmailCte);
        tblClientes.setItems(objC.SELECT());
    }
}
/*
PropertyValueFactory
Callback cuando se ejecuta una accion desencadena una accion al terminar
 */

