package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ProfileControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX {

    @FXML
    private Label badBehaviourLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label usernameLabel;

    @Override
    @FXML
    public void goToProfile(ActionEvent e){
        //This method is empty because is not needed to change screen
    }


    //caricamento dati (Utente Regolare)
    public void loadData(String username, String email, int points, int badBehaviour){
        usernameLabel.setText(username);
        emailLabel.setText(email);
        pointsLabel.setText(String.valueOf(points));
        badBehaviourLabel.setText(String.valueOf(badBehaviour));

    }

    //caricamento dati (Moderatore)
    public void loadData(String username, String email){
        usernameLabel.setText(username);
        emailLabel.setText(email);

    }


    }

