package it.uniroma2.dicii.ispw.progetto.lupini.view;



import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
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

    private String nextWindow;

    public void setNextWindow(String nextView) {

        this.nextWindow = nextView;
    }

    @FXML
    public void clickLogin(ActionEvent event) {
        /*since the activation of the same use case needs a new instance of application controller I create a new instance everytime
        I want to log*/
        LoginControllerAppl logctl = new LoginControllerAppl(this);
        try{
            logctl.login(usernameField.getText(), passwordField.getText());

            switch (nextWindow) {
                case "profileView" -> goToProfile(event);
                case "DoNewModeratorRequest" -> clickBecomeModerator(event);
                case "DoNewQuestion" -> doNewQuestion(event);
                default -> throw new ImpossibleStartGUI( "Errore on starting the GUI");
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


}

