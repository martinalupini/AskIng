package it.uniroma2.dicii.ispw.progetto.lupini.view;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostQuestionControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
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
            //need to finish
    }



    public void initialize(String sectionName) {

        List<QuestionBean> sectionQuestions = this.getQuestionOfSection(sectionName);

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

                        List<String> keywords = q.getKeywords();
                        viewQuestionController.setKeyword1(keywords.get(0));
                        if(keywords.size() == 1){
                            viewQuestionController.setInvisible2();
                            viewQuestionController.setInvisible3();
                        }
                        if(keywords.size() == 2) {
                            viewQuestionController.setKeyword2(keywords.get(1));
                            viewQuestionController.setInvisible3();
                        }
                        if(keywords.size() == 3){
                            viewQuestionController.setKeyword2(keywords.get(2));
                        }

                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        throw new ImpossibleStartGUI( "Errore on starting the GUI");
                    }
                });


                questionLayout.getChildren().add(vbox);

            } catch (IOException e){
                throw new ImpossibleStartGUI( "Errore on starting the GUI");
            }
        }
    }


    private List<QuestionBean> getQuestionOfSection(String sectionName) {

        List<QuestionBean> list = new ArrayList<>();
        PostQuestionControllerAppl postQuestCtlAppl = new PostQuestionControllerAppl();

        try{

            list=  postQuestCtlAppl.returnQuestionOfSection(sectionName.toLowerCase());

        } catch (DBNotAvailable e) {
            errorLabel.setText(e.getMessage());
        }

        return list;


    }



}

