package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostQuestionControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewQuestionControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class QuestionFormController extends EmptyScreen implements NewQuestionControllerInterface {

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

        String author = CurrentUserProfileBean.getProfileInstance().getUsername();
        QuestionBean questionBean = new QuestionBean(author);

        try{
            questionBean.setKeywords(keywordField.getText());
            questionBean.setText(textField.getText());

            //ora bisogna inviare la domanda al controller applicativo il quale controllerà se le keyword o il testo
            //contengono parole bannate

            PostQuestionControllerAppl controllerAppl = new PostQuestionControllerAppl(this);
            controllerAppl.checkAndProcessQuestion(questionBean, section);


        }catch( KeywordsException | TextException | PersistanceLayerNotAvailable e){
            errorLabel.setText(e.getMessage());
        }


    }

    @Override
    public void bannedWordPresent(){
        errorLabel.setText("Sono state rilevate delle parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
    }

    @Override
    public void publicationSuccessful(){
        errorLabel.setText("La tua domanda è stata pubblicata");
    }




}
