package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.OrdenDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Orden extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtIdCte,txtIdEmpl,txtIdMesa,txtFechOrden;
    private Scene escena;
    private OrdenDAO obj;
    private TableView tblOrdenes;
    public Orden(TableView tblOrdenes, OrdenDAO obj) {
        this.tblOrdenes = tblOrdenes;
        creaUI();
        if(obj == null) {
            this.obj = new OrdenDAO();
        }
        else {
            this.obj = obj;
            txtIdCte.setText(String.valueOf(obj.getIdCte()));
            txtIdEmpl.setText(String.valueOf(obj.getIdEmpl()));
            txtIdMesa.setText(String.valueOf(obj.getIdMesa()));
            txtFechOrden.setText(String.valueOf(obj.getFechHora()));
        }
        this.setTitle("Registrar las ordenes");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtIdCte = new TextField();
        txtIdCte.setPromptText("Id cliente");
        txtIdEmpl = new TextField();
        txtIdEmpl.setPromptText("Id empleado");
        txtFechOrden = new TextField();
        txtFechOrden.setPromptText("Fecha orden");
        txtIdMesa = new TextField();
        txtIdMesa.setPromptText("No. mesa");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setIdCte(Integer.parseInt(txtIdCte.getText()));
            obj.setIdEmpl(Integer.parseInt(txtIdEmpl.getText()));
            obj.setIdMesa(Integer.parseInt(txtIdMesa.getText()));
            obj.setFechHora(txtFechOrden.getText());
            if(obj.getIdOrden()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblOrdenes.setItems(obj.SELECT());
            tblOrdenes.refresh();
            this.close();
        });
        vBox = new VBox(txtIdCte,txtIdEmpl,txtIdMesa,txtFechOrden,btnGuardar);
        escena = new Scene(vBox);
    }
}
