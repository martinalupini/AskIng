package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;



import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginControllerGrafico extends EmptyScreen {
    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private String nextWindow;

    private SectionController sectionController;

    private ViewQuestionController viewQuestionController;

    public void setNextWindow(String nextView) {

        this.nextWindow = nextView;
    }

    public void setSectionController(SectionController sectionController) {
        this.sectionController = sectionController;
    }

    public void setViewQuestionController(ViewQuestionController viewQuestionController) {
        this.viewQuestionController = viewQuestionController;
    }

    @FXML
    public void clickLogin(ActionEvent event) {
        /*since the activation of the same use case needs a new instance of application controller I create a new instance everytime
        I want to log*/
        LoginControllerAppl logctl = new LoginControllerAppl();
        try{
            logctl.login(usernameField.getText(), passwordField.getText());

            switch (nextWindow) {
                case "profileView" -> goToProfile(event);
                case "DoNewModeratorRequest" -> clickBecomeModerator(event);
                case "DoNewQuestion" -> this.sectionController.doNewQuestion(event);
                case "DoNewResponse" -> {//this.viewQuestionController.replyToQuestion(event);
                    FXMLLoader loader = new FXMLLoader(TitleCourseController.class.getResource("viewQuestion.fxml"));
                    Parent root = loader.load();

                    ViewQuestionController viewQuestionController = loader.getController();
                    QuestionBean q = this.viewQuestionController.currentQuestion;
                    viewQuestionController.setCurrentQuestion(q);
                    viewQuestionController.setQuestionLabel(q.getText());
                    viewQuestionController.setUsernameLabel(q.getUsername());
                    viewQuestionController.initialize(q.getText(), q.getUsername());
                    viewQuestionController.setResponseText(this.viewQuestionController.getResponseText());

                    SectionController.displayQuestion(root, viewQuestionController, q, (Node)event.getSource());

                    viewQuestionController.replyToQuestion(event);
                }
                default -> throw new ImpossibleStartGUI("Errore on starting the GUI");
            }

            
        } catch (ItemNotFound e) {
            //to clear the text fields
            usernameField.setText("");
            passwordField.setText("");
            errorLabel.setText("Username o password errati. Riprovare. ");

        } catch (PersistanceLayerNotAvailable | IOException e) {
            errorLabel.setText("Spacenti, si sono verificati dei problemi tecnici. Riprovare più tardi");
        }


    }



}

