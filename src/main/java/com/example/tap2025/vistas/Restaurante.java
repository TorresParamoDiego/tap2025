package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonAgregar;
import com.example.tap2025.Componentes.ProductoOrden;
import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.ClientesDAO;
import com.example.tap2025.Modelos.DetalleOrdenDAO;
import com.example.tap2025.Modelos.EmpleadoDAO;
import com.example.tap2025.Modelos.MesaDAO;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Restaurante extends Stage {
    VBox pnlEscena,pnlProductos,pnlDetalleOrden,pnlMesas,pnlEmpleados,pnlClientes;
    HBox pnlCatProd,pnlPrincipal,pnlOrdenAcabar;
    HBox[] pnlBotoMesa,pnlBotoCte,pnlBotoEmpl;
    MenuBar mnbRestaurante;
    Menu menOpciones;
    MenuItem mitLogin,mitReservaciones;
    Button[] btnCategorias,btnMesas,btnEmpleados,btnClientes;
    Scene escena;
    TableView tbvProductos,tbvOrden;
    Button btnTerminar;
    Label lblIdEmpleado,lblIdMesa,lblIdCliente,lblPrecio;
    OrdenDAO orden;
    int idEmpleado,idOrden,idMesa,idCliente;
    LinkedList<Integer> arrProductos;

    public void creaUI(){
        lblIdEmpleado = new Label("ID EMPLEADO");
        lblIdCliente = new Label("ID CLIENTE");
        lblIdMesa = new Label("ID MESA");
        lblPrecio = new Label("Precio");
        creaBotones();
        pnlCatProd = new HBox(btnCategorias);
        pnlEmpleados=new VBox(pnlBotoEmpl);
        pnlClientes = new VBox(pnlBotoCte);
        pnlMesas = new VBox(pnlBotoMesa);

        btnTerminar= new Button("Terminar");
        btnTerminar.setOnAction(e -> {
            orden.setFechHora(Timestamp.valueOf(LocalDateTime.now())+"");
            orden.setIdEmpl(idEmpleado);
            orden.setIdMesa(idMesa);
            orden.setIdCte(idCliente);
            agreProdDetaOrden();
            orden.setPrecioOrden((float)Selectores.calcPrecioOrden(idOrden));
            orden.UPDATE();
            orden.INSERT();
            orden=orden.SELECT().get(orden.SELECT().size()-1);
            idOrden=orden.getIdOrden();
            tbvOrden.getItems().clear();
        }
        );
        tbvProductos = new TableView<ProductoDAO>();
        tbvOrden = new TableView<ProductoOrden>();
        creaTablaProductos(0);
        creaTablaOrden(0);
        mitLogin = new MenuItem("Login");
        mitLogin.setOnAction(e -> new RestauranteAdmin());
        mitReservaciones = new MenuItem("Reservaciones");
        mitReservaciones.setOnAction(e -> new RestauranteAdmin());
        menOpciones = new Menu("Opciones");
        menOpciones.getItems().addAll(mitLogin,mitReservaciones);
        mnbRestaurante = new MenuBar();
        Label Orden=new Label("Orden");
        mnbRestaurante.getMenus().addAll(menOpciones);
        pnlProductos = new VBox(pnlCatProd,tbvProductos);
        pnlOrdenAcabar=new HBox(lblPrecio,btnTerminar);
        pnlEscena = new VBox(mnbRestaurante, pnlProductos,Orden,tbvOrden,pnlOrdenAcabar);

        pnlDetalleOrden= new VBox(lblIdEmpleado);
        pnlDetalleOrden.getChildren().addAll(pnlEmpleados);
        pnlDetalleOrden.getChildren().addAll(lblIdMesa);
        pnlDetalleOrden.getChildren().addAll(pnlMesas);
        pnlDetalleOrden.getChildren().addAll(lblIdCliente);
        pnlDetalleOrden.getChildren().addAll(pnlClientes);

        pnlPrincipal = new HBox(pnlEscena,pnlDetalleOrden);
        escena = new Scene(pnlPrincipal);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
    private void creaTablaOrden(int idOrden){
        tbvOrden.getItems().clear();
        TableColumn<ProductoOrden,String> tbcNomProd= new TableColumn<>("Nombre");
        tbcNomProd.setCellValueFactory(new PropertyValueFactory<>("nomProd"));
        TableColumn<ProductoOrden,Float> tbcPrecioProd= new TableColumn<>("Precio");
        tbcPrecioProd.setCellValueFactory(new PropertyValueFactory<>("precioProd"));
        TableColumn<ProductoOrden,String> tbcUrlImagenProd= new TableColumn<>("Imagen");
        tbcUrlImagenProd.setCellValueFactory(new PropertyValueFactory<>("urlImagenProd"));
        TableColumn<ProductoOrden,Integer> tbcCantidadProd= new TableColumn<>("Cantidad");
        tbcCantidadProd.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tbcUrlImagenProd.setCellFactory(col -> new TableCell<ProductoOrden,String>() {
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
        TableColumn tbcEliminar= new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn tableColumn) {
                return new ButtonAgregar(arrProductos,orden,"Eliminar",tbvOrden,lblPrecio);
            }
        });
        tbvOrden.getColumns().clear();
        tbvOrden.getColumns().addAll(tbcNomProd, tbcPrecioProd, tbcUrlImagenProd,tbcCantidadProd,tbcEliminar);
    }
    private void creaBotones(){
        int cont=0;
        ObservableList<CategoriaDAO> categoria= new CategoriaDAO().SELECT();
        btnCategorias = new Button[categoria.size()];
        for (int i = 0; i < btnCategorias.length; i++) {
            btnCategorias[i] = new Button();
            btnCategorias[i].setText(categoria.get(i).getNomCategoria());
            btnCategorias[i].setPrefSize(100,30);
            int finalI = i;
            btnCategorias[i].setOnAction(e -> {
                creaTablaProductos(categoria.get(finalI).getIdCategoria());
                tbvProductos.refresh();
            });

        }
        ObservableList<EmpleadoDAO> listEmpleados= Selectores.listaEmpleados();
        btnEmpleados = new Button[listEmpleados.size()];
        pnlBotoEmpl=new HBox[listEmpleados.size()/5+1];
        for (int i = 0; i < listEmpleados.size(); i++) {
            btnEmpleados[i] = new Button();
            btnEmpleados[i].setText(listEmpleados.get(i).getIdEmpl()+"");
            btnEmpleados[i].setPrefSize(30,30);
            int finalI = i;
            btnEmpleados[i].setOnAction(e -> {
                idEmpleado=listEmpleados.get(finalI).getIdEmpl();
                lblIdEmpleado.setText("Empleado "+listEmpleados.get(finalI).getIdEmpl());
            });
            if(pnlBotoEmpl[cont]==null)
                pnlBotoEmpl[cont]=new HBox();
            pnlBotoEmpl[cont].getChildren().addAll(btnEmpleados[i]);
            if(pnlBotoEmpl[cont].getChildren().size()==5){
                cont++;
                if(pnlBotoEmpl.length>cont)
                    pnlBotoEmpl[cont]=new HBox();
            }
        }
        ObservableList<MesaDAO> listMesas=new MesaDAO().SELECT();
        btnMesas = new Button[listMesas.size()];
        cont=0;
        pnlBotoMesa=new HBox[listMesas.size()/5+1];
        for (int i = 0; i < listMesas.size(); i++) {
            btnMesas[i] = new Button();
            ImageView imageView = new ImageView(getClass().getResource(
                    "/Images/mesa.png").toExternalForm());
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            btnMesas[i].setGraphic(imageView);
            btnMesas[i].setText(listMesas.get(i).getIdMesa()+"");
            int finalI = i;
            btnMesas[i].setOnAction(e -> {
                idMesa=listMesas.get(finalI).getIdMesa();
                lblIdMesa.setText("Mesa "+listMesas.get(finalI).getIdMesa());
            });
            if(pnlBotoMesa[cont]==null) {
                pnlBotoMesa[cont] = new HBox();
            }
            pnlBotoMesa[cont].getChildren().add(btnMesas[i]);
            if(pnlBotoMesa[cont].getChildren().size()==5){
                cont++;
                pnlBotoMesa[cont]=new HBox();
            }
        }
        cont=0;
        ObservableList<ClientesDAO> listClientes=new ClientesDAO().SELECT();
        btnClientes = new Button[listClientes.size()];
        pnlBotoCte=new HBox[listClientes.size()/5+1];
        for (int i = 0; i < listClientes.size(); i++) {
            btnClientes[i] = new Button();
            btnClientes[i].setText(listClientes.get(i).getIdCte()+"");
            btnClientes[i].setPrefSize(30,30);
            int finalI = i;
            btnClientes[i].setOnAction(e -> {
                idCliente=listClientes.get(finalI).getIdCte();
                lblIdCliente.setText("Cliente "+listMesas.get(finalI).getIdMesa());
            });
            if(pnlBotoCte[cont]==null)
                pnlBotoCte[cont]=new HBox();
            pnlBotoCte[cont].getChildren().addAll(btnClientes[i]);
            if(pnlBotoCte[cont].getChildren().size()==5){
                cont++;
                pnlBotoCte[cont]=new HBox();
            }
        }
    }
    private void creaTablaProductos(int id){
        Restaurante restaurante = this;
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
                return new ButtonAgregar(arrProductos,orden,"Agregar",tbvOrden,lblPrecio);
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
        idCliente=1;
        idMesa=1;
        idEmpleado=1;
        orden=new OrdenDAO();
        orden.setFechHora(Timestamp.valueOf(LocalDateTime.now())+"");
        orden.setIdCte(1);
        orden.setIdEmpl(1);
        orden.setIdMesa(1);
        orden.INSERT();
        orden=orden.SELECT().get(orden.SELECT().size()-1);
        idOrden=orden.getIdOrden();
        this.setOnCloseRequest( e ->
                orden.DELETE()
        );
    }
    private void agreProdDetaOrden(){
        int contador;
        LinkedList<Integer> contado=new LinkedList<>();
        for (int i = 0; i < arrProductos.size(); i++) {
            Integer prod=arrProductos.get(i);
            if(!contado.contains(prod)) {
                contador = 0;

                for (int j = 0; j < arrProductos.size(); j++) {
                    if (prod.equals(arrProductos.get(j))) {
                        contador++;
                    }
                }
                DetalleOrdenDAO detorden = new DetalleOrdenDAO();
                detorden.setIdOrden(orden.getIdOrden());
                detorden.setIdProducto(prod);
                detorden.setCantidad(contador);
                detorden.INSERT();
                contado.add(prod);
            }
        }
        arrProductos.clear();
    }
}
