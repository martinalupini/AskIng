package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostQuestionControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewQuestionControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class QuestionFormControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX implements NewQuestionControllerInterface {

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

        //prende il nome dell'utente che ha fatto la domanda (utente corrente)
        String author = CurrentUserProfileBean.getProfileInstance().getUsername();
        QuestionBean questionBean = new QuestionBean(author);

        try{
            //il bean effettua il controllo sintattico su keywords e bean
            questionBean.setKeywords(keywordField.getText());
            questionBean.setText(textField.getText());

            //ora bisogna inviare la domanda al controller applicativo il quale controllerà se le keyword o il testo
            //contengono parole bannate
            PostQuestionControllerAppl controllerAppl = new PostQuestionControllerAppl();
            controllerAppl.setControllerGrafico(this);
            controllerAppl.checkAndProcessQuestion(questionBean, section);

            //se non contengono parole scurrili viene mostrata la schermata della domanda
            SectionControllerGraficoJavaFX.goToViewQuestion((Node)event.getSource(), questionBean);

        }catch( KeywordsException | TextException  e){
            errorLabel.setText(e.getMessage());
        }catch(PersistanceLayerNotAvailable | IOException e){
            errorLabel.setText("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        } catch (BannedWordFoundException e) {
            bannedWordPresent();
        }

    }

    @Override
    public void bannedWordPresent(){
        errorLabel.setText("Sono state rilevate parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
    }

    @Override
    public void publicationSuccessful(){
        errorLabel.setText("La tua domanda è stata pubblicata");
    }




}
