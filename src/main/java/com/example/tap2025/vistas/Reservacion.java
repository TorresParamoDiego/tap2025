package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.ReservacionDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Reservacion extends Stage {
    private Button btnGuardar;
    private VBox vBox;
    private TextArea txtDuracion, txtHorario, txtCliente;
    private Label lblStatusMessage;
    private Scene escena;
    private ReservacionDAO obj;
    private TableView tblReservacion;

    private StringBuilder inputHorario = new StringBuilder();
    private StringBuilder inputDuracion = new StringBuilder();
    private StringBuilder inputCliente = new StringBuilder();
    private int campoActivo = 0;

    public Reservacion(TableView tblDetalleProducto, ReservacionDAO obj) {
        this.tblReservacion = tblDetalleProducto;
        creaUI();
        inicializarDatos(obj);
        this.setTitle("Registrar reservacion");
        this.setScene(escena);
        this.show();
    }

    private void inicializarDatos(ReservacionDAO obj) {
        if(obj == null)
            this.obj = new ReservacionDAO();
        else {
            this.obj = obj;
            inputCliente.append(String.valueOf(obj.getIdCte()));
            inputDuracion.append(String.valueOf(obj.getDuracionRese()));
            inputHorario.append(obj.getHorarioFechRese());
            updateDisplay();
        }
    }

    private void creaUI() {
        txtCliente = new TextArea();
        txtCliente.setEditable(false);
        txtCliente.setPrefHeight(40);
        txtCliente.setPromptText("Cliente");

        txtHorario = new TextArea();
        txtHorario.setEditable(false);
        txtHorario.setPrefHeight(40);
        txtHorario.setPromptText("Fecha y horario (YYYY-MM-DD HH:MM)");

        txtDuracion = new TextArea();
        txtDuracion.setEditable(false);
        txtDuracion.setPrefHeight(40);
        txtDuracion.setPromptText("Duracion");

        lblStatusMessage = new Label();
        lblStatusMessage.setStyle("-fx-text-fill: red;");

        GridPane numPad = crearNumpad();

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarReservacion());

        vBox = new VBox(15, txtCliente, txtHorario, txtDuracion, numPad, lblStatusMessage, btnGuardar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        updateDisplay();
        escena = new Scene(vBox, 400, 500);
    }

    private void guardarReservacion() {
        String mensaje = validarDatos();
        if (mensaje.isEmpty())
            procesarGuardado();
        else
            lblStatusMessage.setText(mensaje);
    }

    private String validarDatos() {
        if (inputCliente.length() == 0)
            return "Ingrese el ID del cliente";
        if (inputHorario.length() == 0)
            return "Ingrese la fecha y horario";
        if (inputDuracion.length() == 0)
            return "Ingrese la duracion";
        return "";
    }

    private void procesarGuardado() {
        try {
            obj.setHorarioFechRese(inputHorario.toString());
            obj.setIdCte(Integer.parseInt(inputCliente.toString()));
            obj.setDuracionRese(Integer.parseInt(inputDuracion.toString()));

            if(obj.getIdReservacion() > 0)
                obj.UPDATE();
            else
                obj.INSERT();

            tblReservacion.setItems(obj.SELECT());
            tblReservacion.refresh();
            this.close();
        } catch (NumberFormatException e) {
            lblStatusMessage.setText("Error en los datos numericos");
        }
    }

    private GridPane crearNumpad() {
        GridPane numPad = new GridPane();
        numPad.setHgap(10);
        numPad.setVgap(10);
        numPad.setAlignment(Pos.CENTER);

        agregarBotonesNumericos(numPad);
        agregarBotonesEspeciales(numPad);

        return numPad;
    }

    private void agregarBotonesNumericos(GridPane numPad) {
        int number = 1;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                Button btn = createNumButton(String.valueOf(number));
                numPad.add(btn, col, row);
                number++;
            }

        Button btn0 = createNumButton("0");
        numPad.add(btn0, 1, 3);
    }

    private void agregarBotonesEspeciales(GridPane numPad) {
        Button btnBack = new Button("<-");
        btnBack.setPrefSize(60, 60);
        btnBack.setOnAction(e -> {
            borrarChar();
            updateDisplay();
        });
        numPad.add(btnBack, 2, 3);

        Button btnSwitch = new Button("Cambiar");
        btnSwitch.setPrefSize(60, 60);
        btnSwitch.setOnAction(e -> {
            campoActivo = (campoActivo + 1) % 3;
            lblStatusMessage.setText("");
            updateDisplay();
        });
        numPad.add(btnSwitch, 0, 3);

        Button btnSlash = new Button("-");
        btnSlash.setPrefSize(60, 60);
        btnSlash.setOnAction(e -> agregarNoNum("-"));
        numPad.add(btnSlash, 0, 4);

        Button btnSpace = new Button("espacio");
        btnSpace.setPrefSize(60, 60);
        btnSpace.setOnAction(e -> agregarNoNum(" "));
        numPad.add(btnSpace, 1, 4);

        Button btnColon = new Button(":");
        btnColon.setPrefSize(60, 60);
        btnColon.setOnAction(e -> agregarNoNum(":"));
        numPad.add(btnColon, 2, 4);
    }

    private void borrarChar() {
        StringBuilder campo = obtenerCampoActivo();
        if (campo.length() > 0)
            campo.deleteCharAt(campo.length() - 1);
    }

    private void agregarNoNum(String caracter) {
        if (campoActivo == 1) {
            inputHorario.append(caracter);
            updateDisplay();
        }
    }

    private StringBuilder obtenerCampoActivo() {
        if (campoActivo == 0)
            return inputCliente;
        else if (campoActivo == 1)
            return inputHorario;
        else
            return inputDuracion;
    }

    private Button createNumButton(String cchar) {
        Button btn = new Button(cchar);
        btn.setPrefSize(60, 60);
        btn.setOnAction(e -> {
            StringBuilder campo = obtenerCampoActivo();
            campo.append(cchar);
            updateDisplay();
        });
        return btn;
    }

    private void updateDisplay() {
        txtCliente.setText(inputCliente.toString());
        txtHorario.setText(inputHorario.toString());
        txtDuracion.setText(inputDuracion.toString());

        estilizar();
    }

    private void estilizar() {
        String estiloActivo = "-fx-border-color: #2196F3; -fx-border-width: 2px;";
        String estiloInactivo = "";

        if (campoActivo == 0)
            txtCliente.setStyle(estiloActivo);
        else
            txtCliente.setStyle(estiloInactivo);

        if (campoActivo == 1)
            txtHorario.setStyle(estiloActivo);
        else
            txtHorario.setStyle(estiloInactivo);

        if (campoActivo == 2)
            txtDuracion.setStyle(estiloActivo);
        else
            txtDuracion.setStyle(estiloInactivo);
    }
}