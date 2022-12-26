package it.uniroma2.dicii.ispw.progetto.lupini.view;


import it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends EmptyScreen {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void clickLogin() {
        /*since the activation of the same use case needs a new instance of application controller I create a new instance everytime
        I want to log*/
        LoginControllerAppl logctl = new LoginControllerAppl(this);
        try{
            logctl.login(usernameField.getText(), passwordField.getText());
        } catch (DBNotAvailable e) {
            errorLabel.setText(e.getMessage());
        } catch (ItemNotFound e) {
            errorLabel.setText(e.getMessage());
        }
    }


    @Override
    public void goToProfile(ActionEvent event){}

}

