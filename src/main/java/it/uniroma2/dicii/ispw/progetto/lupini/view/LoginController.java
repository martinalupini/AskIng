package it.uniroma2.dicii.ispw.progetto.lupini.view;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends HomepageController {

    
    //String originalPage;
    
    
    @FXML
    private Label errorLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    private String nextView;

    public void setNextView(String nextView) {
        this.nextView = nextView;
    }

    @FXML
    public void clickLogin(ActionEvent event) {
        /*since the activation of the same use case needs a new instance of application controller I create a new instance everytime
        I want to log*/
        LoginControllerAppl logctl = new LoginControllerAppl(this);
        try{
            logctl.login(usernameField.getText(), passwordField.getText());

            switch(nextView){
                case "profileView":
                    goToProfile(event);
                    break;
                case "DoNewModeratorRequest":
                    clickBecomeModerator(event);
                    break;

                case "DoNewQuestion":
                    doNewQuestion(event);
                    break;
            }

            
        } catch (ItemNotFound e) {
            //to clear the text fields
            usernameField.setText("");
            passwordField.setText("");
            errorLabel.setText(e.getMessage());

        } catch (DBNotAvailable e) {
            errorLabel.setText(e.getMessage());
        }


    }


    //vedi se mantenere questo metodo o eliminarlo mantenendo il costrutto switch
    private void goToPage(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(nextView));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

