package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewQuestionController extends EmptyScreen{

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



    }


    public void initialize(String text, String username) {
        List<ResponseBean> responses = new ArrayList<>(responses());
        for(ResponseBean r : responses){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("responseItem.fxml"));

            try{
                VBox vbox = fxmlLoader.load();
                ResponseItemController resContr = fxmlLoader.getController();
                resContr.setResponse( r);
                responseLayout.getChildren().add(vbox);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private List<ResponseBean> responses() {
        List<ResponseBean> list = new ArrayList<>();

        list = currentQuestion.getResponses();

        return list;

    }

    public static ResponseBean convertResponse( Response response){
        String username = response.getAuthor().getUsername();
        String text = response.getResponseText();

        ResponseBean responseBean = new ResponseBean(username, text);

        return responseBean;

    }
}
