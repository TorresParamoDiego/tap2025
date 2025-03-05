package com.example.tap2025.Componentes;

import com.example.tap2025.Modelos.ClientesDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

public class ButtonCell extends TableCell<ClientesDAO,String>{

    private Button btnCelda;
    private String strLableBtn;
    public ButtonCell(String label) {
        strLableBtn = label;
        btnCelda = new Button(strLableBtn);
        btnCelda.setOnAction(event -> {eventoBoton();});
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty){
            this.setGraphic(btnCelda);
        }
    }
    private void eventoBoton(){
        ClientesDAO objC=this.getTableView().getItems().get(this.getIndex());
        if(strLableBtn.equals("Editar")){

        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Mensaje del sistema");
            alert.setContentText("¿Estas seguro de borrar este registro?");
            Optional<ButtonType> opcion = alert.showAndWait();
            if(opcion.get() == ButtonType.OK)
                objC.DELETE();
        }
        this.getTableView().setItems(objC.SELECT());
        this.getTableView().refresh();
    }

}
/*
ClientesDAO objC=this.getTableView().getItems().get(this.getIndex()); para
* recuperar el objeto de la posicion donde esta el boton
 */