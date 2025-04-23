package com.example.tap2025.vistas;

import com.example.tap2025.Componentes.ButtonCell;
import com.example.tap2025.Modelos.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaEmpleados extends Stage {
    private ToolBar tlbMenu;
    private TableView<EmpleadoDAO> tblDetalleEmpleados;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaEmpleados() {
        creaUI();
        this.setTitle("Lista de Detalle de los empleados");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        tblDetalleEmpleados = new TableView<>();
        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> new Empleado(tblDetalleEmpleados,null));
        tlbMenu = new ToolBar(btnAgregar);
        creaTabla();
        vBox = new VBox(tlbMenu, tblDetalleEmpleados);
        escena = new Scene(vBox);
    }
    private void creaTabla(){
        EmpleadoDAO objC = new EmpleadoDAO();
        TableColumn<EmpleadoDAO,Integer> tbcIdEmpleado= new TableColumn<>("Id empleado");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("idEmpl"));
        TableColumn<EmpleadoDAO,String> tbcNomEmpl= new TableColumn<>("Nombre empleado");
        tbcNomEmpl.setCellValueFactory(new PropertyValueFactory<>("nomEmpl"));
        TableColumn<EmpleadoDAO,String> tbcRFCEmpl= new TableColumn<>("RFC");
        tbcRFCEmpl.setCellValueFactory(new PropertyValueFactory<>("RFCEmpl"));
        TableColumn<EmpleadoDAO,String> tbcCurpEmpl= new TableColumn<>("CURP");
        tbcCurpEmpl.setCellValueFactory(new PropertyValueFactory<>("CurpEmpl"));
        TableColumn<EmpleadoDAO,String> tbcNssEmpl= new TableColumn<>("NSS");
        tbcNssEmpl.setCellValueFactory(new PropertyValueFactory<>("nssEmpl"));
        TableColumn<EmpleadoDAO,String> tbcTelEmpl= new TableColumn<>("Teléfono");
        tbcTelEmpl.setCellValueFactory(new PropertyValueFactory<>("telEmpl"));
        TableColumn<EmpleadoDAO,String> tbcFechIngresoEmpl= new TableColumn<>("Fecha de Ingreso");
        tbcFechIngresoEmpl.setCellValueFactory(new PropertyValueFactory<>("fechIngresoEmpl"));
        TableColumn<EmpleadoDAO,String> tbcHorarioEntradaEmpl= new TableColumn<>("Hora de entrada");
        tbcHorarioEntradaEmpl.setCellValueFactory(new PropertyValueFactory<>("horarioEntradaEmpl"));
        TableColumn<EmpleadoDAO,String> tbcHoraioSalidaEmpl= new TableColumn<>("Horaio de salida");
        tbcHoraioSalidaEmpl.setCellValueFactory(new PropertyValueFactory<>("horarioSalidaEmpl"));
        TableColumn<EmpleadoDAO,Integer> tbcIdPuesto= new TableColumn<>("Id puesto");
        tbcIdPuesto.setCellValueFactory(new PropertyValueFactory<>("idPuesto"));
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
        tblDetalleEmpleados.getColumns().addAll(tbcIdEmpleado,tbcNomEmpl,tbcRFCEmpl,tbcCurpEmpl,tbcNssEmpl,tbcTelEmpl
                ,tbcFechIngresoEmpl,tbcHorarioEntradaEmpl,tbcHoraioSalidaEmpl,tbcIdPuesto,tbcEditar,tbcEliminar);
        tblDetalleEmpleados.setItems(objC.SELECT());
    }
}
