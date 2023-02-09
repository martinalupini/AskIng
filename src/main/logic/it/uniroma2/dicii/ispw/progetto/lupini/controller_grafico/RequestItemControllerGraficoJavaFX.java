package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RequestItemControllerGraficoJavaFX {

    @FXML
    private Button requestItem;


    public void setRequestItem(String requestText) {
        this.requestItem.setText(requestText);
    }
}
