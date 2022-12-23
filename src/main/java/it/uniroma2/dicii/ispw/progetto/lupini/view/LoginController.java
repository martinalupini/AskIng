package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends EmptyScreen {

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickLogin(ActionEvent event) {

    }


    @Override
    public void goToProfile(ActionEvent event){}
}

