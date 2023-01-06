package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RequestItemController {

    @FXML
    private Button requestItem;


    public void setRequestItem(String requestText) {
        this.requestItem.setText(requestText);
    }
}
