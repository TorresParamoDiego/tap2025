package com.example.tap2025.vistas;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Rompecabezas extends Stage {
    Scene escena;
    MenuBar menuBar;
    Menu menu;
    MenuItem menRompecabezasS,menRompecabezasM,menRompecabezasG;
    GridPane grid;
    VBox vbox;
    ImageView origen;
    Cronometro cronometro;
    PrintWriter archivoResult;
    String nivel;
    public Rompecabezas() {
        creaIU();
        cronometro = new Cronometro();
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
                nivel="Pequeño";
            }
            break;
            case 2: {
                maxContador = 16;
                maxI = 5;
                maxj = 5;
                maxK = 4;
                rompecabeza = "/Images/Rompecabezas2/row-";
                imagenes = new ImageView[4][4];
                nivel="Mediano";
            }
            break;
            case 3: {
                maxContador = 25;
                maxI = 6;
                maxj = 6;
                maxK = 5;
                rompecabeza = "/Images/Rompecabezas3/row-";
                imagenes = new ImageView[5][5];
                nivel="Grande";
            }
            break;
        }
        while(contador<maxContador){
            i=r.nextInt(1,maxI);
            j=r.nextInt(1,maxj);
            if(imagenes[i-1][j-1]==null) {
                imagenes[i - 1][j - 1] = new ImageView(getClass().getResource(rompecabeza + i +
                        "-column-" + j + ".jpg").toString());
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
        cronometro.show();
        cronometro.setX(0);
        cronometro.setY(0);
        cronometro.iniciar();
    }
    private void anadirEventoImagen(ImageView imagen){
        imagen.setOnMouseEntered(event -> {
            imagen.setCursor(Cursor.HAND);
        });
        imagen.setOnMouseDragged(event -> {
            imagen.setCursor(Cursor.CLOSED_HAND);
        });
        imagen.setOnDragDetected(e -> {
            origen = imagen;
            imagen.startFullDrag();
        });
        imagen.setOnMouseDragReleased(event -> {
            if (origen != null && origen != imagen) {
                intePosicion(origen, imagen);
                validacion();
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

        //origen = null;

    }
    private void validacion() {
        ImageView imagen;
        int nColumns = grid.getColumnCount();
        int nRows = grid.getRowCount();
        int columna = 1;
        String url;
        int fila = 1;
        int cont = 0;
        boolean bandera = true;
        GridPaneOrdenador.ordenarChildren(grid);
        while(cont<grid.getChildren().size()&&bandera){
            url ="row-"+fila+"-column-"+columna+".jpg";
            if(grid.getChildren().get(cont) instanceof ImageView){
                imagen = (ImageView) grid.getChildren().get(cont);
                if(!imagen.getImage().getUrl().contains(url)){
                    bandera=false;
                }
        }
            if(columna<nColumns){
                columna++;
            }
            else {
                fila++;
                columna=1;
                if(fila>nRows)
                    fila=1;
            }
            cont++;
        }
        if(bandera){
            cronometro.detener();
            try {
                anadirArchivo();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("no se puede anadir archivo");
            }
            m_creaAlerta();
            cronometro.reiniciar();
            cronometro.close();
        }
    }
    public void abreArchivo() throws IOException {
        archivoResult = new PrintWriter(new FileWriter("C:\\Users\\52461\\IdeaProjects\\tap2025\\src\\main\\resources\\Archivos\\ResultadosRompecabezas.txt",true));
    }
    public void anadirArchivo() throws IOException {
        abreArchivo();
        archivoResult.println(nivel+": "+cronometro.getTiempo());
        archivoResult.close();
    }
    void m_creaAlerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Felicidades lo resolviste");
        alert.setHeaderText("Lo lograste, da click en aceptar para salir y empezar un nuevo reto");

        alert.setOnCloseRequest(event -> {
            grid.getChildren().clear();
            escena.setCursor(Cursor.DEFAULT);
        });
        alert.showAndWait().ifPresent(response -> {
            grid.getChildren().clear();
            escena.setCursor(Cursor.DEFAULT);
        });
    }
}



class GridPaneOrdenador {
    public static void ordenarChildren(GridPane gridPane) {
        // Copia de la lista para evitar modificar directamente el ObservableList
        ArrayList<Node> childrenList = new ArrayList<>(gridPane.getChildren());

        int n = childrenList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Node nodeA = childrenList.get(j);
                Node nodeB = childrenList.get(j + 1);

                int colA = (GridPane.getColumnIndex(nodeA) != null) ? GridPane.getColumnIndex(nodeA) : 0;
                int rowA = (GridPane.getRowIndex(nodeA) != null) ? GridPane.getRowIndex(nodeA) : 0;
                int colB = (GridPane.getColumnIndex(nodeB) != null) ? GridPane.getColumnIndex(nodeB) : 0;
                int rowB = (GridPane.getRowIndex(nodeB) != null) ? GridPane.getRowIndex(nodeB) : 0;

                // Comparación por fila, luego por columna
                if ((rowA > rowB) || (rowA == rowB && colA > colB)) {
                    Node temp = childrenList.get(j);
                    childrenList.set(j, childrenList.get(j + 1));
                    childrenList.set(j + 1, temp);
                }
            }
        }

        // Reemplazar la lista ordenada en el GridPane
        ObservableList<Node> newChildren = FXCollections.observableArrayList(childrenList);
        gridPane.getChildren().setAll(newChildren);
    }
}
class Cronometro extends Stage{
    Timeline timeline;
    Scene scene;
    Label tiempo;
    Label txtTiempo;
    HBox hbox;
    private int segundos;
    public void creaUi(){
        txtTiempo = new Label("Tiempo: ");
        tiempo = new Label();
        hbox = new HBox();
        hbox.getChildren().addAll(txtTiempo,tiempo);
        scene = new Scene(hbox, 200, 100);
        this.setScene(scene);
    }
    public Cronometro() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            segundos++;
            tiempo.setText(segundos+" segundos");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        creaUi();
    }
    public String getTiempo(){
        return tiempo.getText();
    }
    public void iniciar() {
        tiempo.setText("");
        segundos=0;
        timeline.play();
    }
    public void detener(){
        timeline.pause();
    }
    public void reiniciar() {
        segundos = 0;
        timeline.stop();
    }
}