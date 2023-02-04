package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResponseItemController {

    @FXML
    private Label textLabel;

    @FXML
    private Label userLabel;




    public void setResponse(ResponseBean res){
        userLabel.setText(res.getUsername());
        textLabel.setText(res.getText());
    }

}
