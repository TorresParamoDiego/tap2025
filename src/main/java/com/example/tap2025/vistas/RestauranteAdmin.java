package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestauranteAdmin extends Stage{
    private MenuItem mitCat,mitCte,mitDetOrd,mitDetPro,mitEmp,mitIns,mitMsa;
    private MenuItem mitOdn,mitPro,mitPrv,mitPst,mitRes,mitResMsa;
    private Menu menTablas;
    private MenuBar menBar;
    private VBox vbox;
    private Scene scene;

    public void creaUI(){
        mitCat = new MenuItem("Categorias");
        mitCat.setOnAction(e -> new ListaCategorias());
        mitCte = new MenuItem("Clientes");
        mitCte.setOnAction(e -> new ListaClientes());
        mitDetOrd = new MenuItem("Detalle Ordenes");
        mitDetOrd.setOnAction(e -> new ListaDetalleOrdenes());
        mitDetPro = new MenuItem("Detalle Productos");
        mitDetPro.setOnAction(e -> new ListaDetalleProductos());
        mitEmp = new MenuItem("Empleados");
        mitEmp.setOnAction(e -> new ListaEmpleados());
        mitIns = new MenuItem("Insumos");
        mitIns.setOnAction(e -> new ListaInsumos());
        mitMsa = new MenuItem("Mesa");
        mitMsa.setOnAction(e -> new ListaMesas());
        mitRes = new MenuItem("Reservaciones");
        mitRes.setOnAction(e -> new ListaReservaciones());
        mitResMsa = new MenuItem("Reservaciones Mesa");
        mitResMsa.setOnAction(e-> new ListaReservacionMesas());
        mitOdn = new MenuItem("Orden");
        mitOdn.setOnAction(e->new ListaOrdenes());
        mitPro = new MenuItem("Productos");
        mitPro.setOnAction(e -> new ListaProductos());
        mitPrv = new MenuItem("Proveedores");
        mitPrv.setOnAction(e-> new ListaProovedores());
        mitPst = new MenuItem("Puestos");
        mitPst.setOnAction(e -> new ListaPuestos());
        menTablas = new Menu("Tablas");
        menTablas.getItems().addAll(mitCat,mitCte,mitDetOrd,mitDetPro,mitEmp,mitIns,mitMsa
                ,mitRes,mitResMsa,mitPro,mitPrv,mitPst,mitOdn);
        menBar = new MenuBar();
        menBar.getMenus().addAll(menTablas);
        vbox = new VBox();
        vbox.getChildren().addAll(menBar);
        scene = new Scene(vbox);
    }
    public RestauranteAdmin(){
        creaUI();
        this.setTitle("Restaurante Administrador");
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }
}
