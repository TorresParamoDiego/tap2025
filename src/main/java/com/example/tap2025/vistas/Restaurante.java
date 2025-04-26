package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.Selectores;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Restaurante extends Stage {
    VBox pnlEscena,pnlProductos;
    HBox pnlCatProd;
    MenuBar mnbRestaurante;
    Menu menOpciones;
    MenuItem mitLogin;
    Button[] categorias;
    Scene escena;
    TableView tbvProductos;
    public void creaUI(){
        ObservableList<CategoriaDAO> categoria= new CategoriaDAO().SELECT();
        categorias = new Button[categoria.size()];
        for (int i = 0; i < categorias.length; i++) {
            categorias[i] = new Button();
            categorias[i].setGraphic(new ImageView());
            categorias[i].setText(categoria.get(i).getNomCategoria());
            int finalI = i;
            categorias[i].setOnAction(e -> {
                creaTablaProductos(categoria.get(finalI).getIdCategoria());
                tbvProductos.refresh();
            });
        }
        tbvProductos = new TableView<ProductoDAO>();
        creaTablaProductos(0);
        mitLogin = new MenuItem("Login");
        mitLogin.setOnAction(e -> new RestauranteAdmin());
        menOpciones = new Menu("Opciones");
        menOpciones.getItems().addAll(mitLogin);
        mnbRestaurante = new MenuBar();
        mnbRestaurante.getMenus().addAll(menOpciones);
        pnlCatProd = new HBox(categorias);
        pnlProductos = new VBox(pnlCatProd,tbvProductos);
        pnlEscena = new VBox(mnbRestaurante, pnlProductos);
        escena = new Scene(pnlEscena);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTablaProductos(int id){
        tbvProductos.getItems().clear();
        TableColumn<ProductoDAO,String> tbcNomProd= new TableColumn<>("Nombre");
        tbcNomProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        TableColumn<ProductoDAO,Float> tbcPrecioProd= new TableColumn<>("Precio");
        tbcPrecioProd.setCellValueFactory(new PropertyValueFactory<>("precioProd"));
        TableColumn<ProductoDAO,String> tbcUrlImagenProd= new TableColumn<>("Imagen");
        tbcUrlImagenProd.setCellValueFactory(new PropertyValueFactory<>("urlImagenProd"));
        tbcUrlImagenProd.setCellFactory(col -> new TableCell<ProductoDAO,String>() {
            private ImageView imageView = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty || item.equals("")) {
                    setGraphic(null);
                }
                else {
                    try{
                        imageView.setImage(new Image(getClass().getResource("/Images/Productos/"+item).toExternalForm()));
                        imageView.setFitHeight(60);
                        imageView.setFitWidth(60);
                        setGraphic(imageView);
                    }catch (Exception e){
                        setGraphic(null);
                    }

                }
            }
        });
        tbvProductos.getColumns().clear();
        tbvProductos.getColumns().addAll(tbcNomProd, tbcPrecioProd, tbcUrlImagenProd);
        tbvProductos.setItems(Selectores.listaProductos(id));
    }
    public Restaurante(){
        creaUI();
        this.setScene(escena);
        this.show();
        this.setTitle("Restaurante");
        this.setMaximized(true);
    }
}
