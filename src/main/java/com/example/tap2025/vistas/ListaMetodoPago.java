package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.MetodosPagoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaMetodoPago extends Stage {
    private ToolBar tlbMenu;
    private TableView<MetodosPagoDAO> tblMetodos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaMetodoPago() {
        creaUI();
        this.setTitle("Lista de metodos de pago");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblMetodos = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new MetodoPago(tblMetodos,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblMetodos);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        MetodosPagoDAO objC = new MetodosPagoDAO();
        TableColumn<MetodosPagoDAO, Integer> tbcIdMetodo = new TableColumn<>("Id Metodo");
        tbcIdMetodo.setCellValueFactory(new PropertyValueFactory<>("idMetodoPago"));
        TableColumn<MetodosPagoDAO,String> tbcTipoMetood= new TableColumn<>("Tipo metodo");
        tbcTipoMetood.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        TableColumn<MetodosPagoDAO,String> tbcDescripcion= new TableColumn<>("Descripcion");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcDescripcion.setCellFactory(tc -> new TableCell<>() {
            TextArea texto = new TextArea();
            @Override
            protected void updateItem(String s, boolean b) {
                if(s!=null) {
                    texto.setStyle("-fx-background-color: transparent; -fx-padding: 5;");
                    super.updateItem(s, b);
                    texto.setText(s);
                    texto.setWrapText(true);
                    texto.setEditable(false);
                    texto.setMaxWidth(tbcDescripcion.getWidth());
                    texto.setPrefHeight(texto.getText().split("\n").length * 20);
                    setGraphic(texto);
                }
            }
        });
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
        tblMetodos.getColumns().addAll(tbcIdMetodo,tbcTipoMetood,tbcDescripcion,tbcEditar,tbcEliminar);
        tblMetodos.setItems(objC.SELECT());
    }
}
