package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.CategoriaDAO;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Restaurante extends Stage {
    VBox pnlEscena;
    HBox pnlCatProd;
    MenuBar mnbRestaurante;
    Menu menOpciones;
    MenuItem mitLogin;
    Button[] categorias;
    Scene escena;
    public void creaUI(){
        ObservableList<CategoriaDAO> categoria= new CategoriaDAO().SELECT();
        categorias = new Button[categoria.size()];
        for (int i = 0; i < categorias.length; i++) {
            categorias[i] = new Button();
            categorias[i].setGraphic(new ImageView());
            categorias[i].setText(categoria.get(i).getNomCategoria());
        }
        mitLogin = new MenuItem("Login");
        mitLogin.setOnAction(e -> new RestauranteAdmin());
        menOpciones = new Menu("Opciones");
        menOpciones.getItems().addAll(mitLogin);
        mnbRestaurante = new MenuBar();
        mnbRestaurante.getMenus().addAll(menOpciones);
        pnlCatProd = new HBox(categorias);
        pnlEscena = new VBox(mnbRestaurante, pnlCatProd);
        escena = new Scene(pnlEscena);
    }
    public Restaurante(){
        creaUI();
        this.setScene(escena);
        this.show();
        this.setTitle("Restaurante");
        this.setMaximized(true);
    }
}
