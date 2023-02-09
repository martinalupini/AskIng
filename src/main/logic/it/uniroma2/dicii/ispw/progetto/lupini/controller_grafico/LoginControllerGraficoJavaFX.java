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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX {
    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private String nextWindow;

    private SectionControllerGraficoJavaFX sectionController;

    private ViewSingleQuestionControllerGraficoJavaFX viewQuestionController;

    public void setNextWindow(String nextView) {

        this.nextWindow = nextView;
    }

    public void setSectionController(SectionControllerGraficoJavaFX sectionController) {
        this.sectionController = sectionController;
    }

    public void setViewQuestionController(ViewSingleQuestionControllerGraficoJavaFX viewQuestionController) {
        this.viewQuestionController = viewQuestionController;
    }

    @FXML
    public void clickLogin(ActionEvent event) {
        LoginControllerAppl logctl = new LoginControllerAppl();
        try{
            logctl.login(usernameField.getText(), passwordField.getText());

            //serve per tornare alla schermata di partenza quando clicco login
            switch (nextWindow) {
                case "profileView" -> goToProfile(event);
                case "DoNewModeratorRequest" -> clickBecomeModerator(event);
                case "DoNewQuestion" -> this.sectionController.doNewQuestion(event);
                case "DoNewResponse" -> {
                    FXMLLoader loader = new FXMLLoader(TitleCourseControllerGraficoJavaFX.class.getResource("viewQuestion.fxml"));
                    Parent root = loader.load();

                    ViewSingleQuestionControllerGraficoJavaFX viewQuestionController = loader.getController();
                    QuestionBean q = this.viewQuestionController.currentQuestion;
                    viewQuestionController.setCurrentQuestion(q);
                    q.attach(viewQuestionController);
                    viewQuestionController.setQuestionLabel(q.getText());
                    viewQuestionController.setUsernameLabel(q.getUsername());
                    viewQuestionController.initialize(q.getText(), q.getUsername());
                    viewQuestionController.setResponseText(this.viewQuestionController.getResponseText());

                    SectionControllerGraficoJavaFX.displayQuestion(root, viewQuestionController, q, (Node)event.getSource());

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

    @FXML
    void loginGoogle(ActionEvent event) {
        errorLabel.setText("NOT IMPLEMENTED");
    }

    @FXML
    void loginMicrosoft(ActionEvent event) {
        errorLabel.setText("NOT IMPLEMENTED");
    }

    @FXML
    void register(ActionEvent event) {
        errorLabel.setText("NOT IMPLEMENTED");
    }



}

