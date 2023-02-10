package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestUserAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class DoNewRequestControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX implements NewRequestControllerGraficoInterface {

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


    //mostra i dati dell'utente che verranno poi inviati al moderatore
    public void displayUserData(String username, String email, int point, int behaviour){
        this.usernameLabel.setText(username);
        this.emailLabel.setText(email);
        this.pointsLabel.setText(String.valueOf(point));
        this.behaviourLabel.setText(String.valueOf(behaviour));
    }


    @FXML
    void sendRequest(ActionEvent event) {

        try {
            //creo il bean da passare al controller applicativo. Il bean esegue anche i controlli sintattici sulla
            //lunghezza del testo ed eventualmente viene lanciata l'eccezione TextException
            RequestBean requestBean = new RequestBean(usernameLabel.getText(), emailLabel.getText(), Integer.valueOf(pointsLabel.getText()), Integer.valueOf(behaviourLabel.getText()));
            requestBean.setText(requestText.getText());

            //creo la classe attraverso cui comunicare con il controller applicativo
            RequestUserAPI requestAPI = new RequestUserAPI();
            requestAPI.setUserControllerGrafico(this);

            //invio la richiesta al controller
            requestAPI.sendRequest(requestBean);


        } catch (PersistanceLayerNotAvailable e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText("Spiacenti la richiesta non può essere registrata per motivi tecnici. Riprovare più tardi.");
        } catch (RequestAlreadyDone e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText("Impossibile fare una nuova richiesta perchè una richiesta fatta da te è ancora in sospeso.");
        }catch (TextException e) {
            descriptionLabel.setText(e.getMessage());

        }
    }

    //serve a comunicare all'utente che la richiesta è stata salvata e registrata
    public void requestSaved(){

        explainingLabel.setVisible(false);
        sendButton.setVisible(false);
        requestText.setVisible(false);
        statusLabel.setText("RICHIESTA MANDATA CON SUCCESSO");

    }



}
