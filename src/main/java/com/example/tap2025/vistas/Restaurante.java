package com.example.tap2025.vistas;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Restaurante extends Stage {
    HBox PanelOdn,pnlCategorias;
    VBox pnlCatProd;
    MenuBar mnbRestaurante;
    Button[] categorias;
    public void creaUI(){
        categorias = new Button[3];
    }
}
