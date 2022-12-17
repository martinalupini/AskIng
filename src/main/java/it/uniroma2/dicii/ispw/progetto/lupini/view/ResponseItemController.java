package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResponseItemController implements Initializable {

    @FXML
    private Label textLabel;

    @FXML
    private Label userLabel;


    public void setResponse(ResponseBean res){
        userLabel.setText(res.getUsername());
        textLabel.setText(res.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
