package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.MesaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mesa extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtCapacidad;
    private Scene escena;
    private MesaDAO obj;
    private TableView tblMesa;
    public Mesa(TableView tblMesa, MesaDAO obj) {
        this.tblMesa = tblMesa;
        creaUI();
        if(obj == null) {
            this.obj = new MesaDAO();
        }
        else {
            this.obj = obj;
            txtCapacidad.setText(String.valueOf(obj.getCapacidadMesa()));
        }
        this.setTitle("Registrar las mesas");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtCapacidad = new TextField();
        txtCapacidad.setPromptText("Capacidad");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setCapacidadMesa(Integer.parseInt(txtCapacidad.getText()));
            if(obj.getIdMesa()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblMesa.setItems(obj.SELECT());
            tblMesa.refresh();
            this.close();
        });
        vBox = new VBox(txtCapacidad,btnGuardar);
        escena = new Scene(vBox);
    }
}
