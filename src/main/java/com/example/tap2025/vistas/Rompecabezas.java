package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Rompecabezas extends Stage {
    Scene escena;
    MenuBar menuBar;
    Menu menu;
    MenuItem menRompecabezasS,menRompecabezasM,menRompecabezasG;
    public Rompecabezas() {
        creaIU();
        this.setTitle("Rompecabezas");
        this.setScene(escena);
        this.show();
    }
    private void creaIU(){
        menRompecabezasS = new MenuItem("Crear Rompecabeza chico");
        menRompecabezasS.setOnAction(e -> {creaRompecabezas(1);});
        menRompecabezasM = new MenuItem("Crear Rompecabeza mediano");
        menRompecabezasM.setOnAction(e -> {creaRompecabezas(2);});
        menRompecabezasG = new MenuItem("Crear Rompecabeza Grande");
        menRompecabezasG.setOnAction(e -> {creaRompecabezas(3);});
        menu=new Menu("Decidir Tamaño");
        menu.getItems().addAll(menRompecabezasS,menRompecabezasM,menRompecabezasG);
        menuBar=new MenuBar();
        menuBar.getMenus().addAll(menu);
        escena=new Scene(menuBar,800,600);
    }
    private void creaRompecabezas(int tamano){
        switch (tamano){
            case 1:{

            }
            break;
            case 2: {


            }
            break;
            case 3:{
            }
            break;
        }
    }
}
