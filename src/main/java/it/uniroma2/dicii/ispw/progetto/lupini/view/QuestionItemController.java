package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuestionItemController {
    @FXML
    private Label textLabel;

    @FXML
    private Label userLabel;

    public void setQuestion(QuestionBean res){
        userLabel.setText(res.getUsername());
        textLabel.setText(res.getText());
    }


}
