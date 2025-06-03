package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.EmpleadoDAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Empleado extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextField txtNombre,txtTelefono;
    private TextField txtRFC, txtCurp,txtNss,txtfechIngreso;
    private TextField txthorarioEntrada, txthorarioSalida,txtIdPuesto, txtPass;
    private Scene escena;
    private EmpleadoDAO obj;
    private TableView tblEmpleados;
    public Empleado(TableView tblEmpleados, EmpleadoDAO obj) {
        this.tblEmpleados = tblEmpleados;
        creaUI();
        if(obj == null) {
            this.obj = new EmpleadoDAO();
        }
        else {
            this.obj = obj;
            txtNombre.setText(obj.getNomEmpl());
            txtTelefono.setText(obj.getTelEmpl());
            txtRFC.setText(obj.getRFCEmpl());
            txtCurp.setText(obj.getCurpEmpl());
            txtNss.setText(obj.getNssEmpl());
            txtfechIngreso.setText(obj.getFechIngresoEmpl());
            txthorarioEntrada.setText(obj.getHorarioEntradaEmpl());
            txthorarioSalida.setText(obj.getHorarioSalidaEmpl());
            txtIdPuesto.setText(String.valueOf(obj.getIdPuesto()));
            txtPass.setText("");
        }
        this.setTitle("Registrar los empleados");
        this.setScene(escena);
        this.show();
    }
    private void creaUI(){
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono");
        txtRFC = new TextField();
        txtRFC.setPromptText("RFC");
        txtCurp = new TextField();
        txtCurp.setPromptText("Curp");
        txtNss = new TextField();
        txtNss.setPromptText("Nss");
        txtfechIngreso = new TextField();
        txtfechIngreso.setPromptText("Fecha ingreso");
        txthorarioEntrada = new TextField();
        txthorarioEntrada.setPromptText("Horario entrada");
        txthorarioSalida = new TextField();
        txthorarioSalida.setPromptText("Horario salida");
        txtIdPuesto = new TextField();
        txtIdPuesto.setPromptText("Id puesto");
        txtPass = new TextField();
        txtPass.setPromptText("Password");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            obj.setNomEmpl(txtNombre.getText());
            obj.setTelEmpl(txtTelefono.getText());
            obj.setRFCEmpl(txtRFC.getText());
            obj.setCurpEmpl(txtCurp.getText());
            obj.setNssEmpl(txtNss.getText());
            obj.setFechIngresoEmpl(txtfechIngreso.getText());
            obj.setHorarioEntradaEmpl(txthorarioEntrada.getText());
            obj.setHorarioSalidaEmpl(txthorarioSalida.getText());
            obj.setIdPuesto(Integer.parseInt(txtIdPuesto.getText()));
            obj.setPassword(hashPassword(txtPass.getText()));
            if(obj.getIdEmpl()>0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblEmpleados.setItems(obj.SELECT());
            tblEmpleados.refresh();
            this.close();
        });
        vBox = new VBox(txtNombre,txtTelefono,txtRFC,
                txtCurp,txtNss,txtfechIngreso,txthorarioEntrada,txthorarioSalida,txtIdPuesto, txtPass,btnGuardar);
        escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/Styles/Restaurante.css").toExternalForm());
    }

    String hashPassword(String pass){
        Argon2 argon2 = Argon2Factory.create();
        char[] passwd = pass.toCharArray();
        try {
            int N = 65536;
            int r = 2;
            int p = 1;
            // Hash password
            return argon2.hash(r, N, p, passwd);
        } finally {
            argon2.wipeArray(passwd);
        }
    }
}
