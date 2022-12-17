package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionItemController implements Initializable {
    @FXML
    private Label textLabel;

    @FXML
    private Label userLabel;

    public void setQuestion(QuestionBean res){
        userLabel.setText(res.getUsername());
        textLabel.setText(res.getText());
    }

    @FXML
    void viewQuestion(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
