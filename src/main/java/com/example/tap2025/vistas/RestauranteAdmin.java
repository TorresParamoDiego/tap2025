package com.example.tap2025.vistas;

import com.example.tap2025.reportes.GenerarReporte;
import com.example.tap2025.reportes.Graficas;
import com.itextpdf.text.DocumentException;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class RestauranteAdmin extends Stage{
    private MenuItem mitCat,mitCte,mitDetOrd,mitDetPro,mitEmp,mitIns,mitMsa;
    private MenuItem mitOdn,mitPro,mitPrv,mitPst,mitRes,mitResMsa;
    private MenuItem mitReporte, mitProdMasVendidos, mitVentasPDia, mitEmplMasVentas, mitReporteGraficas;
    private Menu menTablas, reporte;
    private MenuBar menBar;
    private VBox vbox;
    private Scene scene;
    private MenuItem mitComIns;

    public void creaUI(){
        mitCat = new MenuItem("Categorias");
        mitCat.setOnAction(e -> new ListaCategorias());
        mitCte = new MenuItem("Clientes");
        mitCte.setOnAction(e -> new ListaClientes());
        mitComIns=new MenuItem("Compra insumos");
        mitComIns.setOnAction(e->new ListaCompraInsumo());
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
        MenuItem mitMet=new MenuItem("Metodos Pago");
        mitMet.setOnAction(e->new ListaMetodoPago());
        menTablas.getItems().addAll(mitCat,mitCte,mitComIns,mitDetOrd,mitDetPro,mitEmp,mitIns,mitMsa
                ,mitMet,mitRes,mitResMsa,mitPro,mitPrv,mitPst,mitOdn);

        mitReporte = new MenuItem("Generar reporte de órdenes");
        mitReporte.setOnAction(event -> new GenerarReporte());
        mitProdMasVendidos = new MenuItem("Productos más vendidos");
        mitProdMasVendidos.setOnAction(event -> {
            try {
                Graficas.masVendidos();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        mitVentasPDia = new MenuItem("Ventas por día");
        mitVentasPDia.setOnAction(event -> {
            try {
                Graficas.ventasPDia();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        mitEmplMasVentas = new MenuItem("Ventas de empleados");
        mitEmplMasVentas.setOnAction(event -> {
            try {
                Graficas.empleadoMasVentas();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        mitReporteGraficas = new MenuItem("Reporte de Graficas");
        mitReporteGraficas.setOnAction(event -> {
            try {
                String path="./reporte_graficas.pdf";
                Graficas.exportarGraficasAPDF(path);

                File file = new File(path);
                Desktop.getDesktop().open(file);
            } catch (SQLException | DocumentException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        reporte = new Menu("Reportes");
        reporte.getItems().addAll(mitProdMasVendidos, mitVentasPDia, mitEmplMasVentas, mitReporteGraficas, mitReporte);


        menBar = new MenuBar();
        menBar.getMenus().addAll(menTablas, reporte);
        vbox = new VBox();
        vbox.getChildren().addAll(menBar);
        scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());

    }
    public RestauranteAdmin(){
        creaUI();
        this.setTitle("Restaurante Administrador");
        this.setScene(scene);
        this.setMaximized(true);
        this.show();
    }
}
