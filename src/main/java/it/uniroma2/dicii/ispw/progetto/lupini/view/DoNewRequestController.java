package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class DoNewRequestController extends EmptyScreen{

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

        //first I create the bean
        RequestBean requestBean = new RequestBean(requestText.getText(), usernameLabel.getText(), emailLabel.getText(), Integer.valueOf(pointsLabel.getText()), Integer.valueOf(behaviourLabel.getText()));
        //then I create the application controller
        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(this, null);
        //then I call the processRequest method
        try {
            requestControllerAppl.processRequest(requestBean);
        } catch (PersistanceLayerNotAvailable e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText(e.getMessage());
        } catch (RequestAlreadyDone e) {
            descriptionLabel.setTextFill(Paint.valueOf("red"));
            descriptionLabel.setText(e.getMessage());
        }
    }

    public void updateStatus(){

        explainingLabel.setVisible(false);
        sendButton.setVisible(false);
        requestText.setVisible(false);
        statusLabel.setText("RICHIESTA MANDATA CON SUCCESSO");

    }


}
