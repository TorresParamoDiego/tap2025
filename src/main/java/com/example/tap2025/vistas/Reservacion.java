package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ReservacionDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Reservacion extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtDuracion,txtHoraio,txtCliente;
    private Scene escena;
    private ReservacionDAO obj;
    private TableView tblReservacion;
    public Reservacion(TableView tblDetalleProducto, ReservacionDAO obj) {
        this.tblReservacion = tblReservacion;
        creaUI();
        if(obj == null) {
            this.obj = new ReservacionDAO();
        }
        else {
            this.obj = obj;
            txtCliente.setText(String.valueOf(obj.getIdCte()));
            txtDuracion.setText(String.valueOf(obj.getDuracionRese()));
            txtHoraio.setText(obj.getHorarioFechRese());
        }
        this.setTitle("Registrar reservacion");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtHoraio = new TextField();
        txtHoraio.setText("Fecha y horario");
        txtDuracion = new TextField();
        txtDuracion.setText("Duracion");
        txtCliente = new TextField();
        txtCliente.setText("Cliente");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setHorarioFechRese(txtHoraio.getText());
            obj.setIdCte(Integer.parseInt(txtCliente.getText()));
            obj.setDuracionRese(Integer.parseInt(txtDuracion.getText()));
            if(obj.getIdReservacion()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblReservacion.setItems(obj.SELECT());
            tblReservacion.refresh();
            this.close();
        });
        vBox = new VBox(txtHoraio, txtDuracion, txtCliente, btnGuardar);
        escena = new Scene(vBox);
    }
}
