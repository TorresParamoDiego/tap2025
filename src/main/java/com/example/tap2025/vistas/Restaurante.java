package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonAgregar;
import com.example.tap2025.Componentes.ProductoOrden;
import com.example.tap2025.Modelos.CategoriaDAO;
import com.example.tap2025.Modelos.ClientesDAO;
import com.example.tap2025.Modelos.DetalleOrdenDAO;
import com.example.tap2025.Modelos.EmpleadoDAO;
import com.example.tap2025.Modelos.MesaDAO;
import com.example.tap2025.Modelos.MetodosPagoDAO;
import com.example.tap2025.Modelos.OrdenDAO;
import com.example.tap2025.Modelos.ProductoDAO;
import com.example.tap2025.Modelos.Selectores;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Restaurante extends Stage {
    VBox pnlEscena,pnlProductos,pnlDetalleOrden;
    HBox pnlCatProd,pnlPrincipal,pnlMesas,pnlEmpleados,pnlClientes,pnlOrdenAcabar, pnlMetodosPago;
    MenuBar mnbRestaurante;
    //Menu menOpciones;
    MenuItem mitLogin,mitReservaciones;
    Button[] btnCategorias,btnMesas,btnEmpleados,btnClientes;
    Scene escena;
    TableView tbvProductos,tbvOrden;
    Button btnTerminar;
    Label lblIdEmpleado,lblIdMesa,lblIdCliente,lblPrecio;
    Button[] btnMetodosPago;
    Label lblMetodoPago;
    OrdenDAO orden;
    int idEmpleado,idOrden,idMesa,idCliente, idMetodoPago;
    String nombre;
    LinkedList<Integer> arrProductos;

    public void creaUI(){
        lblIdEmpleado = new Label("EMPLEADO: " + nombre);
        lblIdCliente = new Label("ID CLIENTE");
        lblIdMesa = new Label("ID MESA");
        lblPrecio = new Label("Precio");
        lblMetodoPago = new Label("MÉTODO DE PAGO");
        creaBotones();
        pnlCatProd = new HBox(btnCategorias);
        pnlEmpleados=new HBox(btnEmpleados);
        pnlClientes = new HBox(btnClientes);
        pnlMesas = new HBox(btnMesas);
        pnlMetodosPago = new HBox(btnMetodosPago);

        btnTerminar= new Button("Terminar");
        btnTerminar.setOnAction(e -> {
            orden.setFechHora(Timestamp.valueOf(LocalDateTime.now())+"");
            orden.setIdEmpl(idEmpleado);
            orden.setIdMesa(idMesa);
            orden.setIdCte(idCliente);

            agreProdDetaOrden();

            float total =(float) Selectores.calcPrecioOrden(idOrden);
            orden.setPrecioOrden(total);
            orden.UPDATE();


            //ticket
            try {
                //pdf
                String outputPath = "./ticket.pdf";
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                document.open();

                document.add(new Paragraph("Ticket\n\n"));

                PdfPTable ticket = new PdfPTable(2);
                ticket.setWidthPercentage(100);
                ticket.addCell("Fecha:");
                ticket.addCell(orden.getFechHora());
                ticket.addCell("Empleado:");
                ticket.addCell(nombre);
                ticket.addCell("Mesa:");
                ticket.addCell(idMesa+"");
                ticket.addCell("Cliente:");
                ticket.addCell(idCliente+"");
                ticket.addCell("Método de pago:");
                int metPag = orden.getIdMetodoPago();
                if(metPag == 1)
                    ticket.addCell("Efectivo");
                else if (metPag == 2)
                    ticket.addCell("Tarjeta");

                document.add(ticket);
                document.add(new Paragraph("\n"));

                //productos
                document.add(new Paragraph("Órden:\n\n"));

                PdfPTable productTable = new PdfPTable(3);
                productTable.setWidthPercentage(100);
                //float[] columnWidths = {3f, 1f, 1.5f, 1.5f}; //ancho
                //productTable.setWidths(columnWidths);

                productTable.addCell("Producto");
                productTable.addCell("Cant.");
                productTable.addCell("Precio Unit.");
                //productTable.addCell("Subtotal");

                //obtener los detalles de la orden
                ObservableList<DetalleOrdenDAO> detallesOrden = new DetalleOrdenDAO().SELECT();
                float totalOrden = 0;

                for (int i = 0; i < detallesOrden.size(); i++) {
                    DetalleOrdenDAO detalle = detallesOrden.get(i);
                    if (detalle.getIdOrden() == orden.getIdOrden()) {
                        ObservableList<ProductoDAO> productos = new ProductoDAO().SELECT();
                        for (int j = 0; j < productos.size(); j++) {
                            ProductoDAO producto = productos.get(j);
                            if (producto.getIdProducto() == detalle.getIdProducto()) {
                                float subtotal = producto.getPrecioProd() * detalle.getCantidad();
                                totalOrden += subtotal;

                                productTable.addCell(producto.getNomProd());
                                productTable.addCell(String.valueOf(detalle.getCantidad()));
                                productTable.addCell("$" + String.format("%.2f", producto.getPrecioProd()));
                                //productTable.addCell("$" + String.format("%.2f", subtotal));
                            }
                        }
                    }
                }

                document.add(productTable);
                document.add(new Paragraph("\n"));


                PdfPTable totalTable = new PdfPTable(2);
                totalTable.setWidthPercentage(100);
                totalTable.addCell("TOTAL:");
                totalTable.addCell("$" + String.format("%.2f", totalOrden));

                document.add(totalTable);
                document.close();

                //this.close();
                //abrir pdf
                File file = new File(outputPath);
                Desktop.getDesktop().open(file);

            } catch (DocumentException | IOException ex) {
                ex.printStackTrace();
            }
            orden.UPDATE();
            orden.INSERT();
            orden=orden.SELECT().get(orden.SELECT().size()-1);
            orden.setPrecioOrden(0.1f);
            idOrden=orden.getIdOrden();
            tbvOrden.getItems().clear();
        });

        tbvProductos = new TableView<ProductoDAO>();
        tbvOrden = new TableView<ProductoOrden>();
        creaTablaProductos(0);
        creaTablaOrden(0);
        mitLogin = new MenuItem("Login");
        mitLogin.setOnAction(e -> new RestauranteAdmin());
        mitReservaciones = new MenuItem("Reservaciones");
        mitReservaciones.setOnAction(e -> new RestauranteAdmin());
        //menOpciones = new Menu("Opciones");
        //menOpciones.getItems().addAll(mitLogin,mitReservaciones);
        mnbRestaurante = new MenuBar();
        //mnbRestaurante.getMenus().addAll(menOpciones);
        pnlProductos = new VBox(pnlCatProd,tbvProductos);
        pnlOrdenAcabar=new HBox(lblPrecio,btnTerminar);
        pnlEscena = new VBox(mnbRestaurante, pnlProductos,tbvOrden,pnlOrdenAcabar);

        pnlDetalleOrden= new VBox(lblIdEmpleado);
        pnlDetalleOrden.getChildren().addAll(pnlEmpleados);
        pnlDetalleOrden.getChildren().addAll(lblIdMesa);
        pnlDetalleOrden.getChildren().addAll(pnlMesas);
        pnlDetalleOrden.getChildren().addAll(lblIdCliente);
        pnlDetalleOrden.getChildren().addAll(pnlClientes);
        pnlDetalleOrden.getChildren().addAll(lblMetodoPago);
        pnlDetalleOrden.getChildren().addAll(pnlMetodosPago);

        pnlPrincipal = new HBox(pnlEscena,pnlDetalleOrden);
        escena = new Scene(pnlPrincipal);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }

    private void creaTablaOrden(int idOrden){
        tbvOrden.getItems().clear();
        Restaurante restaurante = this;
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
        ObservableList<CategoriaDAO> categoria= new CategoriaDAO().SELECT();
        btnCategorias = new Button[categoria.size()];
        for (int i = 0; i < btnCategorias.length; i++) {
            btnCategorias[i] = new Button();
            btnCategorias[i].setText(categoria.get(i).getNomCategoria());
            btnCategorias[i].setPrefSize(200,30);
            int finalI = i;
            btnCategorias[i].setOnAction(e -> {
                creaTablaProductos(categoria.get(finalI).getIdCategoria());
                tbvProductos.refresh();
            });
        }

        ObservableList<EmpleadoDAO> listEmpleados= Selectores.listaEmpleados();
        btnEmpleados = new Button[listEmpleados.size()];
        for (int i = 0; i < listEmpleados.size(); i++) {
            btnEmpleados[i] = new Button();
            btnEmpleados[i].setText(listEmpleados.get(i).getIdEmpl()+"");
            btnEmpleados[i].setPrefSize(30,30);
            int finalI = i;
            btnEmpleados[i].setOnAction(e -> {
                idEmpleado=listEmpleados.get(finalI).getIdEmpl();
                lblIdEmpleado.setText("Empleado "+listEmpleados.get(finalI).getIdEmpl());
            });
        }

        ObservableList<MesaDAO> listMesas=new MesaDAO().SELECT();
        btnMesas = new Button[listMesas.size()];
        for (int i = 0; i < listMesas.size(); i++) {
            btnMesas[i] = new Button();
            btnMesas[i].setText(listMesas.get(i).getIdMesa()+"");
            ImageView imageView = new ImageView(getClass().getResource(
                    "/Images/mesa.png").toExternalForm());
            imageView.setStyle("-fx-background-color: #a9360c;-fx-border-radius: 30;");
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            btnMesas[i].setGraphic(imageView);
            int finalI = i;
            btnMesas[i].setOnAction(e -> {
                idMesa=listMesas.get(finalI).getIdMesa();
                lblIdMesa.setText("Mesa "+listMesas.get(finalI).getIdMesa());
            });
        }

        ObservableList<ClientesDAO> listClientes=new ClientesDAO().SELECT();
        btnClientes = new Button[listClientes.size()];
        for (int i = 0; i < listClientes.size(); i++) {
            btnClientes[i] = new Button();
            btnClientes[i].setText(listClientes.get(i).getIdCte()+"");
            btnClientes[i].setPrefSize(30,30);
            int finalI = i;
            btnClientes[i].setOnAction(e -> {
                idCliente=listClientes.get(finalI).getIdCte();
                lblIdCliente.setText("Cliente "+listClientes.get(finalI).getIdCte());
            });
        }

        ObservableList<MetodosPagoDAO> listMetodosPago = new MetodosPagoDAO().SELECT();
        btnMetodosPago = new Button[listMetodosPago.size()];
        for (int i = 0; i < listMetodosPago.size(); i++) {
            btnMetodosPago[i] = new Button();
            btnMetodosPago[i].setText(listMetodosPago.get(i).getTipoMetodo());
            btnMetodosPago[i].setPrefSize(100, 30);
            int finalI = i;
            btnMetodosPago[i].setOnAction(e -> {
                idMetodoPago = listMetodosPago.get(finalI).getIdMetodoPago();
                lblMetodoPago.setText("Método: " + listMetodosPago.get(finalI).getTipoMetodo());
            });
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

    public Restaurante(int idEmpleado, String nombre){
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        arrProductos=new LinkedList<>();
        creaUI();
        this.setScene(escena);
        this.show();
        this.setTitle("Restaurante - Empleado: " + nombre);
        this.setMaximized(true);
        idCliente=1;
        idMesa=1;
        orden=new OrdenDAO();
        orden.setFechHora(Timestamp.valueOf(LocalDateTime.now())+"");
        orden.setIdCte(1);
        orden.setIdEmpl(idEmpleado);
        orden.setIdMesa(1);
        idMetodoPago = 1;
        orden.setPrecioOrden(0.00000000001F);
        //orden.INSERT();
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
                        //System.out.println(contador);
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