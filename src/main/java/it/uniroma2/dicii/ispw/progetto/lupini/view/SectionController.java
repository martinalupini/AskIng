package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ForumSectionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SectionController extends EmptyScreen {

    @FXML
    private VBox questionLayout;

    @FXML
    private Label sectionName;

    @FXML
    private Label errorLabel;


    public void setSectionName(String sectionName) {
        this.sectionName.setText(sectionName);
    }



    @FXML
    void clickSearch(ActionEvent event) {

    }



    public void initialize(String sectionName) {

        List<QuestionBean> sectionQuestions = this.questions(sectionName);

        for(QuestionBean q : sectionQuestions){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("questionItem.fxml"));

            try{
                VBox vbox = fxmlLoader.load();
                QuestionItemController questContr = fxmlLoader.getController();
                questContr.setQuestion( q);

                vbox.setOnMouseClicked(mouseEvent -> {

                    try {
                        FXMLLoader loader = new FXMLLoader(TitleCourseController.class.getResource("viewQuestion.fxml"));
                        Parent root = loader.load();

                        ViewQuestionController viewQuestionController = loader.getController();
                        viewQuestionController.setCurrentQuestion(q);
                        viewQuestionController.setQuestionLabel(q.getText());
                        viewQuestionController.setUsernameLabel(q.getUsername());
                        viewQuestionController.initialize(q.getText(), q.getUsername());
                        viewQuestionController.setKeyword1(q.getKeywords().get(0));

                        try{
                            viewQuestionController.setKeyword2(q.getKeywords().get(1));
                        }catch(IndexOutOfBoundsException e){
                            viewQuestionController.setInvisible2();
                        }

                        try{
                            viewQuestionController.setKeyword2(q.getKeywords().get(2));
                        }catch(IndexOutOfBoundsException e){
                            viewQuestionController.setInvisible3();
                        }

                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                questionLayout.getChildren().add(vbox);

            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }


    private List<QuestionBean> questions(String sectionName) {

        /*qua devo comunicare con DAO per recuperare domande
        oppure posso comunicare con section
        */

        List<QuestionBean> list = new ArrayList<>();
        ForumSectionBean forumSectionBean = new ForumSectionBean(sectionName);
        try {
            list = forumSectionBean.getQuestions();
        } catch (DBNotAvailable e) {
            errorLabel.setText(e.getMessage());
        }

        return list;

    }



    public static QuestionBean convertQuestion(Question question){
        QuestionBean quest = new QuestionBean(question.getAuthor().getUsername(), question.getQuestionText(), question.getKeywords(), question.getId());
        return quest;
    }

}

