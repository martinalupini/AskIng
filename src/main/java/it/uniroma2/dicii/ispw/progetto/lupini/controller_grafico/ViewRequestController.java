package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class ViewRequestController extends  EmptyScreen implements ManageRequestsContrGrafInterface {

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


    public void loadData(String username, String email, int point, int behaviour, String text){
        this.usernameLabel.setText(username);
        this.emailLabel.setText(email);
        this.pointsLabel.setText(String.valueOf(point));
        this.behaviourLabel.setText(String.valueOf(behaviour));
        this.requestText.setText(text);
    }

    @FXML
    void acceptRequest(ActionEvent event) {

        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(null, this);
        try {
            requestControllerAppl.updateRequestState( this.usernameLabel.getText(), "accepted");
        } catch (PersistanceLayerNotAvailable e) {
            statusLabel.setText(e.getMessage());
        }

    }


    @FXML
    void declineRequest(ActionEvent event) {
        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(null, this);
        try {
            requestControllerAppl.updateRequestState( this.usernameLabel.getText(), "declined");
        } catch (PersistanceLayerNotAvailable e) {
            statusLabel.setText(e.getMessage());
        }
    }

    @Override
    public void updateStatus(String s){

        acceptButton.setVisible(false);
        declineButton.setVisible(false);
        statusLabel.setText("RICHIESTA "+s+" CON SUCCESSO");
    }

    @Override
    public void notifyModeratorNewRequest() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Notification notification = new Notification();
        String destination= "moderators_"+timeStamp;
        notification.notify(destination, "E' arrivata una nuova richiesta per diventare moderatore. Controllare la propria sezione");
    }

}
