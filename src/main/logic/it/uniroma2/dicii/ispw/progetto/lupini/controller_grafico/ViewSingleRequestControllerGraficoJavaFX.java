package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestModeratorAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewSingleRequestControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX implements ManageRequestsContrGrafInterface {

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

    @FXML
    private Label statusLabel;

    @FXML
    private Button acceptButton;


    @FXML
    private Button declineButton;


    //mostra i dati dell'autore della domanda
    public void displayUserRequestData(String username, String email, int point, int behaviour, String text){
        this.usernameLabel.setText(username);
        this.emailLabel.setText(email);
        this.pointsLabel.setText(String.valueOf(point));
        this.behaviourLabel.setText(String.valueOf(behaviour));
        this.requestText.setText(text);
    }

    @FXML
    void acceptRequest(ActionEvent event) {

        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI();
        moderatorAPI.setModeratorControllerGrafico(this);
        try {
            moderatorAPI.updateRequestState(this.usernameLabel.getText(), "accepted");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            statusLabel.setText("Spiacenti la richiesta non può essere accetta per motivi tecnici. Riprovare più tardi.");
        }

    }


    @FXML
    void declineRequest(ActionEvent event) {
        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI();
        moderatorAPI.setModeratorControllerGrafico(this);
        try {
            moderatorAPI.updateRequestState(this.usernameLabel.getText(), "declined");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            statusLabel.setText("Spiacenti la richiesta non può essere rifiutata per motivi tecnici. Riprovare più tardi.");
        }
    }

    //mostra al moderatore se la sua operazione è andata a buon fine
    @Override
    public void updateStatus(String s){

        acceptButton.setVisible(false);
        declineButton.setVisible(false);
        statusLabel.setText("RICHIESTA "+s+" CON SUCCESSO");
    }

}
