package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestUserAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class DoNewRequestController extends EmptyScreen implements NewRequestControllerGraficoInterface {

    @FXML
    private Label behaviourLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label pointsLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField requestText;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label explainingLabel;

    @FXML
    private Button sendButton;


    @FXML
    private Label statusLabel;



    public void loadData(String username, String email, int point, int behaviour){
        this.usernameLabel.setText(username);
        this.emailLabel.setText(email);
        this.pointsLabel.setText(String.valueOf(point));
        this.behaviourLabel.setText(String.valueOf(behaviour));
    }


    @FXML
    void sendRequest(ActionEvent event) {

        //creo il bean da passare al controller applicativo
        RequestBean requestBean = new RequestBean(requestText.getText(), usernameLabel.getText(), emailLabel.getText(), Integer.valueOf(pointsLabel.getText()), Integer.valueOf(behaviourLabel.getText()));

        //creo la classe attraverso cui comunicare con il controller applicativo
        RequestUserAPI requestAPI = new RequestUserAPI(this, null);
        try {
            requestAPI.sendRequest(requestBean);
        } catch (PersistanceLayerNotAvailable e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText("Spiacenti la richiesta non può essere registrata per motivi tecnici. Riprovare più tardi.");
        } catch (RequestAlreadyDone e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText("Impossibile fare una nuova richiesta perchè una richiesta fatta da te è ancora in sospeso.");
        }
    }

    public void updateStatus(){

        explainingLabel.setVisible(false);
        sendButton.setVisible(false);
        requestText.setVisible(false);
        statusLabel.setText("RICHIESTA MANDATA CON SUCCESSO");

    }



}
