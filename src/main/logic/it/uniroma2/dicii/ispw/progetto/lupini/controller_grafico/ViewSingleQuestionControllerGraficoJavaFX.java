package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ObserverOfQuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostResponseControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionsAndResponsesFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewResponseControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.UserNotLogged;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewSingleQuestionControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX implements NewResponseControllerInterface, ObserverOfQuestionBean {

    QuestionBean currentQuestion;


    @FXML
    private Label questionLabel;

    @FXML
    private VBox responseLayout;

    @FXML
    private Label usernameLabel;


    @FXML
    private TextField keyword1;

    @FXML
    private TextField keyword2;

    @FXML
    private TextField keyword3;

    @FXML
    private TextField responseText;

    @FXML
    private Label risposteText;

    @FXML
    private Button sendButton;

    @FXML
    private Label errorLabel;

    public void setResponseText(String responseText) {
        this.responseText.setText(responseText);
    }

    public String getResponseText() {
        return responseText.getText();
    }

    public void setKeyword1(String keyword1) {
        this.keyword1.setText(keyword1);
    }

    public void setKeyword2(String keyword2) {
        this.keyword2.setText(keyword2);
    }

    public void setKeyword3(String keyword3) {
        this.keyword3.setText(keyword3);
    }

    public void setInvisible2() {
        this.keyword2.setVisible(false);
    }

    public void setInvisible3() {
        this.keyword3.setVisible(false);
    }

    public void setCurrentQuestion(QuestionBean currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setQuestionLabel(String questiontext) {
        this.questionLabel.setText(questiontext);
    }

    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }



    @FXML
    public void replyToQuestion(ActionEvent event) {

        nextView = "DoNewResponse";
        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();

        //prima controlla se l'utente è loggato
        if (!currUser.isLogged()) {
            UserNotLogged userNotLogged = new UserNotLogged();
            userNotLogged.setViewQuestionController(this);
            userNotLogged.userNotLogged(nextView, event);
            return;
        }

        //prende l'username dell'utente corrente (autore della domanda)
        String author = CurrentUserProfileBean.getProfileInstance().getUsername();
        ResponseBean responseBean = new ResponseBean(author);

        try {
            //la bean effettua il controllo sintattico sulla lunghezza del testo
            responseBean.setText(responseText.getText());

            //si chiama il metodo del caso d'uso
            PostResponseControllerAppl postResponseControllerAppl = new PostResponseControllerAppl();
            postResponseControllerAppl.setControllerGrafico(this);
            postResponseControllerAppl.checkAndProcessResponse(responseBean, currentQuestion);

        } catch (TextException e) {
            errorLabel.setText(e.getMessage());
        }catch(PersistanceLayerNotAvailable e){
            errorLabel.setText("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        }


    }


    //metodo per il caricamento della schermata
    public void initialize(String text, String username) {
        this.currentQuestion.setResponses(getResponsesOfQuestion());
        for(ResponseBean r : this.currentQuestion.getResponses()){

            showResponse(r);
        }
    }


    private List<ResponseBean> getResponsesOfQuestion() {

        List<ResponseBean> list = new ArrayList<>();
        QuestionsAndResponsesFactory factory = QuestionsAndResponsesFactory.getCurrentInstance();

        try{
        list = factory.retrieveResponsesBeanFromQuestion(currentQuestion.getId());

        } catch (PersistanceLayerNotAvailable e) {
            questionLabel.setVisible(false);
            responseLayout.setVisible(false);
            responseText.setVisible(false);
            risposteText.setVisible(false);
            usernameLabel.setVisible(false);
            keyword1.setVisible(false);
            keyword2.setVisible(false);
            keyword3.setVisible(false);
            sendButton.setVisible(false);
            errorLabel.setText(e.getMessage());
        }

        return list;

    }


    @Override
    public void bannedWordPresent() {
        errorLabel.setText("Sono state rilevate delle parole non adeguate nella tua risposta. Il tuo punteggio BadBehaviour è stato aumentato.");
    }

    //utilizzato per implementare il pattern observer
    @Override
    public void update(){

        showResponse(currentQuestion.getResponses().get(currentQuestion.getResponses().size()-1));
    }

    //per mostrare la nuova domanda aggiunta
    private void showResponse(ResponseBean r){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("responseItem.fxml"));

        try{
            VBox vbox = fxmlLoader.load();
            ResponseItemControllerGraficoJavaFX resContr = fxmlLoader.getController();
            resContr.setResponse( r);
            responseLayout.getChildren().add(vbox);

        } catch (IOException e){
            throw new ImpossibleStartGUI( "Errore on starting the GUI");
        }
    }


}
