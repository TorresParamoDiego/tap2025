package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ReservacionMesaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservacionMesa extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtIdReservacion,txtIdMesa;
    private Scene escena;
    private ReservacionMesaDAO obj;
    private TableView tblReservacionMesa;
    public ReservacionMesa(TableView tblReservacionMesa, ReservacionMesaDAO obj) {
        this.tblReservacionMesa = tblReservacionMesa;
        creaUI();
        if(obj == null) {
            this.obj = new ReservacionMesaDAO();
        }
        else {
            this.obj = obj;
            txtIdReservacion.setText(String.valueOf(obj.getIdReservacion()));
            txtIdMesa.setText(String.valueOf(obj.getIdMesa()));
        }
        this.setTitle("Registrar las mesas de las reservaciones");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtIdMesa = new TextField();
        txtIdMesa.setPromptText("No. mesa");
        txtIdReservacion = new TextField();
        txtIdReservacion.setPromptText("Id reservación");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setIdReservacion(Integer.parseInt(txtIdReservacion.getText()));
            obj.setIdMesa(Integer.parseInt(txtIdMesa.getText()));
            if(obj.getIdReservacion()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblReservacionMesa.setItems(obj.SELECT());
            tblReservacionMesa.refresh();
            this.close();
        });
        vBox = new VBox(txtIdReservacion,txtIdMesa,btnGuardar);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }
}
