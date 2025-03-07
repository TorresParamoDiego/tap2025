package com.example.tap2025.vistas;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Rompecabezas extends Stage {
    Scene escena;
    MenuBar menuBar;
    Menu menu;
    MenuItem menRompecabezasS,menRompecabezasM,menRompecabezasG;
    GridPane grid;
    VBox vbox;
    ImageView origen;
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
        grid=new GridPane();
        vbox=new VBox(menuBar,grid);
        escena=new Scene(vbox,500,301);
    }
    private void creaRompecabezas(int tamano) {
        grid.getChildren().clear();
        byte contador = 0;
        byte maxContador = 0;
        int i, j, k = 0, l = 0, maxI = 0, maxj = 0, maxK = 0;
        Random r = new Random();
        String rompecabeza="";
        ImageView[][] imagenes=null;
        switch (tamano) {
            case 1: {
                maxContador = 6;
                maxI = 4;
                maxj = 3;
                maxK = 2;
                rompecabeza = "/Images/Rompecabezas1/row-";
                imagenes = new ImageView[3][2];
            }
            break;
            case 2: {
                maxContador = 16;
                maxI = 5;
                maxj = 5;
                maxK = 4;
                rompecabeza = "/Images/Rompecabezas2/row-";
                imagenes = new ImageView[4][4];
            }
            break;
            case 3: {
                maxContador = 16;
                maxI = 5;
                maxj = 5;
                maxK = 4;
                rompecabeza = "/Images/Rompecabezas2/row-";
                imagenes = new ImageView[5][5];
            }
            break;
        }
        while(contador<maxContador){
            i=r.nextInt(1,maxI);
            j=r.nextInt(1,maxj);
            if(imagenes[i-1][j-1]==null) {
                imagenes[i - 1][j - 1] = new ImageView(getClass().getResource(rompecabeza + i + "-column-" + j + ".jpg").toString());
                anadirEventoImagen(imagenes[i - 1][j - 1]);
                if (k < maxK) {
                    grid.add(imagenes[i - 1][j - 1], k, l);
                    k++;
                }
                if (k == maxK) {
                    k = 0;
                    l++;
                }
                contador++;
            }
        }
    }
    private void anadirEventoImagen(ImageView imagen){
        imagen.setOnDragDetected(e -> {
            origen = imagen;
            imagen.startFullDrag();
        });
        imagen.setOnMouseDragReleased(event -> {
            if (origen != null && origen != imagen) {
                intePosicion(origen, imagen);
            }
        });
    }
    private void intePosicion(ImageView img1, ImageView img2) {
        int col1 = GridPane.getColumnIndex(img1);
        int ren1 = GridPane.getRowIndex(img1);
        int col2 = GridPane.getColumnIndex(img2);
        int ren2 = GridPane.getRowIndex(img2);

        GridPane.setColumnIndex(img1, col2);
        GridPane.setRowIndex(img1, ren2);
        GridPane.setColumnIndex(img2, col1);
        GridPane.setRowIndex(img2, ren1);

        origen = null;
    }
    private void validacion() {
        ImageView imagen;
        int nColumns = grid.getColumnCount();
        int nRows = grid.getRowCount();
        int columna = 0;
        int fila = 0;
        for (int i = 0; i < grid.getChildren().size(); i++) {
            if(grid.getChildren().get(i) instanceof ImageView){
                imagen = (ImageView) grid.getChildren().get(i);
            }
            //if(imagen.getImage().getUrl())
        }
    }
}
