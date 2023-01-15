package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostQuestionControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class QuestionFormController extends EmptyScreen{

    @FXML
    private TextField keywordField;
    @FXML
    private TextField textField;

    @FXML
    private Label errorLabel;

    private String section;


    public void setSection(String section) {
        this.section = section;
    }

    @FXML
    public void sendQuestion(ActionEvent event) {

        List<String> keywords = parseListKeywords(keywordField.getText());
        String author = CurrentUserProfileBean.getProfileInstance().getUsername();


        QuestionBean questionBean = new QuestionBean(author);

        try{
            questionBean.setKeywords(keywords);
            questionBean.setText(textField.getText());

            //ora bisogna inviare la domanda al controller applicativo il quale controllerà se le keyword o il testo
            //contengono parole bannate

            PostQuestionControllerAppl controllerAppl = new PostQuestionControllerAppl(this);
            controllerAppl.checkAndProcessQuestion(questionBean, section);


        }catch( KeywordsException | TextException | PersistanceLayerNotAvailable e){
            errorLabel.setText(e.getMessage());
        }


    }

    public void bannedWordPresent(){
        errorLabel.setText("Sono state rilevate delle parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
    }

    public void publicationSuccessful(){
        errorLabel.setText("La tua domanda è stata pubblicata");
    }

    private List<String> parseListKeywords(String text) {
        int i;

        List<String> list = new ArrayList<>();
        String[] words  = text.split(" ");

        //Simply check to verify that double blank space or punctuation characters are not
        //considered as a word
        for(i=0; i< words.length; i++){
            if(words[i].matches("^[\\s\\p{P}]*$")){
                continue;
            }
            list.add(words[i]);
        }

        return list;

    }


}
