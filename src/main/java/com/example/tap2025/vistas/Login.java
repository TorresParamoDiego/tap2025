package com.example.tap2025.vistas;

import com.example.tap2025.Modelos.EmpleadoDAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Stage {
    private TextArea txtIdEmpleado;
    private TextArea txtPassword;
    private Label lblStatusMessage;
    private boolean flagPass = false;
    private StringBuilder inputUsuario = new StringBuilder();
    private StringBuilder inputPass = new StringBuilder();

    public Login() {
        crearUI();
    }

    private void crearUI() {
        this.setTitle("Login");
        Label lblTitulo = new Label("Acceso");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        txtIdEmpleado = new TextArea();
        txtIdEmpleado.setEditable(false);
        txtIdEmpleado.setPrefHeight(40);
        txtIdEmpleado.setPromptText("ID de Empleado");

        txtPassword = new TextArea();
        txtPassword.setEditable(false);
        txtPassword.setPrefHeight(40);
        txtPassword.setPromptText("Contraseña");

        lblStatusMessage = new Label();
        lblStatusMessage.setStyle("-fx-text-fill: red;");

        GridPane numPad = crearNumpad();

        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setPrefWidth(120);
        btnLogin.setOnAction(e -> validarLogin());

        Button btnClear = new Button("Limpiar");
        btnClear.setPrefWidth(120);
        btnClear.setOnAction(e -> {
            inputUsuario.setLength(0);
            inputPass.setLength(0);
            flagPass = false;
            updateDisplay();
            lblStatusMessage.setText("");
        });

        HBox buttons = new HBox(20, btnLogin, btnClear);
        buttons.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(15, lblTitulo, txtIdEmpleado, txtPassword, numPad, lblStatusMessage, buttons);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 400, 500);
        this.setScene(scene);
        this.show();
    }

    private GridPane crearNumpad() {
        GridPane numPad = new GridPane();
        numPad.setHgap(10);
        numPad.setVgap(10);
        numPad.setAlignment(Pos.CENTER);

        //botones 1 a 9
        int number = 1;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                Button btn = createNumButton(String.valueOf(number));
                numPad.add(btn, col, row);
                number++;
            }

        //0
        Button btn0 = createNumButton("0");
        numPad.add(btn0, 1, 3);

        //borrar
        Button btnBack = new Button("<-");
        btnBack.setPrefSize(60, 60);
        btnBack.setOnAction(e -> {
            if (flagPass) {
                if (inputPass.length() > 0)
                    inputPass.deleteCharAt(inputPass.length() - 1);
            }
            else if (inputUsuario.length() > 0)
                    inputUsuario.deleteCharAt(inputUsuario.length() - 1);
            updateDisplay();
        });
        numPad.add(btnBack, 2, 3);

        //cambiar seccion
        Button btnSwitch = new Button("Cambiar");
        btnSwitch.setPrefSize(60, 60);
        btnSwitch.setOnAction(e -> {
            flagPass = !flagPass;
            lblStatusMessage.setText("");
            updateDisplay();
        });
        numPad.add(btnSwitch, 0, 3);

        return numPad;
    }

    private Button createNumButton(String digit) {
        Button btn = new Button(digit);
        btn.setPrefSize(60, 60);
        btn.setOnAction(e -> {
            if (flagPass)
                inputPass.append(digit);
            else
                inputUsuario.append(digit);
            updateDisplay();
        });
        return btn;
    }

    private void updateDisplay() {
        txtIdEmpleado.setText(inputUsuario.toString());
        //asteriscos para esconder contraseña
        StringBuilder oculto = new StringBuilder();
        for (int i = 0; i < inputPass.length(); i++)
            oculto.append("*");
        txtPassword.setText(oculto.toString());

        //resaltar
        if (flagPass) {
            txtPassword.setStyle("-fx-border-color: #2196F3; -fx-border-width: 2px;");
            txtIdEmpleado.setStyle("");
        } else {
            txtIdEmpleado.setStyle("-fx-border-color: #2196F3; -fx-border-width: 2px;");
            txtPassword.setStyle("");
        }
    }

    private void validarLogin() {
        boolean datosCorrectos = true;
        if (inputUsuario.length() == 0) {
            lblStatusMessage.setText("Ingrese el ID de empleado");
            datosCorrectos = false;
        }
        else if (inputPass.length() == 0) {
            lblStatusMessage.setText("Ingrese la contraseña");
            datosCorrectos = false;
        }

        if (datosCorrectos) {
            String usuarioLimpio = inputUsuario.toString().trim().replace("\n", "").replace("\r", "");
            String passwordLimpio = inputPass.toString().trim().replace("\n", "").replace("\r", "");

            int idEmpl;
            try {
                idEmpl = Integer.parseInt(usuarioLimpio);
            } catch (NumberFormatException e) {
                lblStatusMessage.setText("Tu ID es incorrecto");
                return;
            }

            EmpleadoDAO empleado = new EmpleadoDAO();
            ObservableList<EmpleadoDAO> lista = empleado.SELECT();
            for (EmpleadoDAO emp : lista)
                if (emp.getIdEmpl() == idEmpl)
                    if (verificarPassword(passwordLimpio, emp.getPassword())) {
                        this.close();

                        //det usuario
                        if (idEmpl == 1)
                            new RestauranteAdmin();
                        else
                            new Restaurante(idEmpl, emp.getNomEmpl());
                        return;
                    } else {
                        lblStatusMessage.setText("Contraseña incorrecta");
                        return;
                    }

            lblStatusMessage.setText("Empleado no encontrado");
        }
    }

    private boolean verificarPassword(String pass, String hash) {
        if (hash == null || hash.isEmpty())
            return false;
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.verify(hash, pass.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}