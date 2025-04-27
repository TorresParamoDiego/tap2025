package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonAgregar;
import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.DetalleOrdenDAO;
import com.example.tap2025.Modelos.EmpleadoDAO;
import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.Selectores;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class Restaurante extends Stage {
    VBox pnlEscena,pnlProductos,pnlEmpleados;
    HBox pnlCatProd,pnlPrincipal;
    MenuBar mnbRestaurante;
    Menu menOpciones;
    MenuItem mitLogin,mitReservaciones;
    Button[] categorias;
    Button[] empleados;
    Scene escena;
    TableView tbvProductos,tbvOrden;
    Button btnTerminar;
    Label lblIdEmpleado;
    OrdenDAO orden;
    int idEmpleado;
    int idOrden;
    LinkedList<Integer> arrProductos;
    public void creaUI(){
        lblIdEmpleado = new Label("ID EMPLEADO");
        ObservableList<CategoriaDAO> categoria= new CategoriaDAO().SELECT();
        categorias = new Button[categoria.size()];
        for (int i = 0; i < categorias.length; i++) {
            categorias[i] = new Button();
            categorias[i].setText(categoria.get(i).getNomCategoria());
            categorias[i].setPrefSize(200,30);
            int finalI = i;
            categorias[i].setOnAction(e -> {
                creaTablaProductos(categoria.get(finalI).getIdCategoria());
                tbvProductos.refresh();
            });

        }
        ObservableList<EmpleadoDAO> empleado= Selectores.listaEmpleados();
        empleados = new Button[empleado.size()];
        for (int i = 0; i < empleado.size(); i++) {
            empleados[i] = new Button();
            empleados[i].setText(empleado.get(i).getIdEmpl()+"");
            empleados[i].setPrefSize(30,30);
            int finalI = i;
            empleados[i].setOnAction(e -> {
                idEmpleado=empleado.get(finalI).getIdEmpl();
                lblIdEmpleado.setText("Empleado "+empleado.get(finalI).getIdEmpl());
            });
        }
        btnTerminar= new Button("Terminar");
        btnTerminar.setOnAction(e -> {
            orden.setFechHora(LocalDateTime.now()+"");
            orden.setIdEmpl(idEmpleado);
            orden.INSERT();
            idOrden=orden.SELECT().get(orden.SELECT().size()-1).getIdOrden();
            agreProdDetaOrden();
        }
        );
        tbvProductos = new TableView<ProductoDAO>();
        tbvOrden = new TableView<ProductoDAO>();
        creaTablaProductos(0);
        creaTablaOrden(0);
        mitLogin = new MenuItem("Login");
        mitLogin.setOnAction(e -> new RestauranteAdmin());
        mitReservaciones = new MenuItem("Reservaciones");
        mitReservaciones.setOnAction(e -> new RestauranteAdmin());
        menOpciones = new Menu("Opciones");
        menOpciones.getItems().addAll(mitLogin,mitReservaciones);
        mnbRestaurante = new MenuBar();
        mnbRestaurante.getMenus().addAll(menOpciones);
        pnlCatProd = new HBox(categorias);
        pnlProductos = new VBox(pnlCatProd,tbvProductos);
        pnlEscena = new VBox(mnbRestaurante, pnlProductos,tbvOrden,btnTerminar);
        pnlEmpleados= new VBox(lblIdEmpleado);
        pnlEmpleados.getChildren().addAll(empleados);
        pnlPrincipal = new HBox(pnlEscena,pnlEmpleados);
        escena = new Scene(pnlPrincipal);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTablaOrden(int idOrden){
        tbvOrden.getItems().clear();
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
        TableColumn tbcAgregar= new TableColumn<>("Agregar");
        tbcAgregar.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn tableColumn) {
                return new ButtonAgregar(arrProductos,orden);
            }
        });
        tbvProductos.getColumns().clear();
        tbvProductos.getColumns().addAll(tbcNomProd, tbcPrecioProd, tbcUrlImagenProd,tbcAgregar);
        tbvProductos.setItems(Selectores.listaProductos(id));
    }
    public Restaurante(){
        arrProductos=new LinkedList<>();
        creaUI();
        this.setScene(escena);
        this.show();
        this.setTitle("Restaurante");
        this.setMaximized(true);
        orden=new OrdenDAO();
        orden.setFechHora(LocalDateTime.now()+"");
        orden.INSERT();
        orden=orden.SELECT().get(orden.SELECT().size()-1);
        idOrden=orden.getIdOrden();
    }
    private void agreProdDetaOrden(){
        int contador=0;
        LinkedList<Integer> contado=new LinkedList<>();
        for (int i = 0; i < arrProductos.size(); i++) {
            Integer prod=arrProductos.get(i);
            if(!contado.contains(prod)){
                contador=0;
            }
            for (int j = 0; j < arrProductos.size(); j++) {
                if (prod.equals(arrProductos.get(j))) {
                    contador++;
                }
            }
            DetalleOrdenDAO orden=new DetalleOrdenDAO();
            orden.setIdOrden(idOrden);
            orden.setIdProducto(prod);
            orden.setCantidad(contador);
            orden.INSERT();
        }
    }
}
