package com.example.tap2025.vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestauranteAdmin extends Stage{
    MenuItem mitCat,mitCte,mitDetOrd,mitDetPro,mitEmp,mitIns,mitMsa;
    MenuItem mitOdn,mitPro,mitPrv,mitPst,mitRes,mitResMsa;
    Menu menTablas;
    MenuBar menBar;
    VBox vbox;
    Scene scene;
    public void creaUI(){
        mitCat = new MenuItem("Categorias");
        mitCte = new MenuItem("Clientes");
        mitCte.setOnAction(e -> {new ListaClientes();});
        mitDetOrd = new MenuItem("Detalle Ordenes");
        mitDetPro = new MenuItem("Detalle Productos");
        mitEmp = new MenuItem("Empleados");
        mitIns = new MenuItem("Insumos");
        mitMsa = new MenuItem("Mesa");
        mitRes = new MenuItem("Reservaciones");
        mitResMsa = new MenuItem("Reservaciones Mesa");
        mitOdn = new MenuItem("Orden");
        mitPro = new MenuItem("Productos");
        mitPrv = new MenuItem("Proveedores");
        mitPst = new MenuItem("Puestos");
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
        this.setTitle("Restaurante Admin");
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }
}
