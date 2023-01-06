package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewRequestController extends  EmptyScreen{

    @FXML
    private Label behaviourLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label requestText;

    @FXML
    private Label usernameLabel;


    public void loadData(String username, String email, int point, int behaviour, String text){
        this.usernameLabel.setText(username);
        this.emailLabel.setText(email);
        this.pointsLabel.setText(String.valueOf(point));
        this.behaviourLabel.setText(String.valueOf(behaviour));
        this.requestText.setText(text);
    }

    @FXML
    void acceptRequest(ActionEvent event) {

    }

    @FXML
    void declineRequest(ActionEvent event) {

    }

}
