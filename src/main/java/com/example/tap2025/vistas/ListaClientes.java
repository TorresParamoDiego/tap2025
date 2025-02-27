package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ClientesDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
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
    public void creaUI() {
        btnAgregar = new Button();
        btnAgregar.setGraphic(new ImageView(getClass().getResource("/Images/boton.jpg").toString()));
        tlbMenu = new ToolBar(btnAgregar);
        tblClientes = new TableView<>();
        vBox = new VBox(tlbMenu,tblClientes);
        escena = new Scene(vBox,500,500);
    }
}

