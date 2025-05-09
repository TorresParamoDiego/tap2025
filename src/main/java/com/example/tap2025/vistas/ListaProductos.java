package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.ProductoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaProductos extends Stage {
    private ToolBar tlbMenu;
    private TableView<ProductoDAO> tblProductos;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaProductos() {
        creaUI();
        this.setTitle("Lista de Detalle de los productos");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblProductos = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Producto(tblProductos,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblProductos);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTabla(){
        ProductoDAO objC = new ProductoDAO();
        TableColumn<ProductoDAO,Integer> tbcIdProducto= new TableColumn<>("Id");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        TableColumn<ProductoDAO,String> tbcNomProd= new TableColumn<>("Nombre");
        tbcNomProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        TableColumn<ProductoDAO,Float> tbcPrecioProd= new TableColumn<>("Precio");
        tbcPrecioProd.setCellValueFactory(new PropertyValueFactory<>("precioProd"));
        TableColumn<ProductoDAO,Float> tbcCostoProd= new TableColumn<>("Costo");
        tbcCostoProd.setCellValueFactory(new PropertyValueFactory<>("costoProd"));
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
                        imageView.setFitHeight(50);
                        imageView.setFitWidth(50);
                        setGraphic(imageView);
                    }catch (Exception e){
                        setGraphic(null);
                    }

                }
            }
        });
        TableColumn<ProductoDAO,Integer> tbcIdCategoria= new TableColumn<>("Categoria");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
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
        tblProductos.getColumns().addAll(tbcIdProducto,tbcNomProd,tbcPrecioProd,tbcCostoProd,
        tbcUrlImagenProd,tbcIdCategoria,tbcEditar,tbcEliminar);
        tblProductos.setItems(objC.SELECT());
    }
}
