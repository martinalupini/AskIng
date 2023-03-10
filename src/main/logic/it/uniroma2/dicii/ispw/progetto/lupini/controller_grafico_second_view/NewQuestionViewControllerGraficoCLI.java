package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostQuestionControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewQuestionControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.NewQuestionView;

public class NewQuestionViewControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements NewQuestionControllerInterface {

    private String section;
    private NewQuestionView view;

    public NewQuestionViewControllerGraficoCLI(String section, NewQuestionView newQuestionView){
        this.view = newQuestionView;
        this.section = section;
    }

    //chiamato quando l'utente vuole inviare una nuova domanda
    public void submitQuestion(String keywords, String text) throws KeywordsException, TextException, PersistanceLayerNotAvailable {
        String author = CurrentUserProfileBean.getProfileInstance().getUsername();
        QuestionBean questionBean = new QuestionBean(author);

        //controllo sintattico effettuato dal bean quando voglio settare il testo
        questionBean.setKeywords(keywords);
        questionBean.setText(text);
        try {

            PostQuestionControllerAppl controllerAppl = new PostQuestionControllerAppl();
            controllerAppl.setControllerGrafico(this);
            controllerAppl.checkAndProcessQuestion(questionBean, section);

        }catch(PersistanceLayerNotAvailable e){
            throw new PersistanceLayerNotAvailable("Si sono verificati dei problemi tecnici. Riprovare pi?? tardi.");
        } catch (BannedWordFoundException e) {
            bannedWordPresent();
        }
    }

    @Override
    public void bannedWordPresent() {
        this.view.bannedWordInText();

    }

    @Override
    public void publicationSuccessful() {
        this.view.success();

    }
}
