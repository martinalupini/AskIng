package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostResponseControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewResponseControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.LoginView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.QuestionView;

public class QuestionControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements NewResponseControllerInterface {

    private QuestionBean question;

    private QuestionView view;

    public QuestionControllerGraficoCLI(QuestionBean question, QuestionView view){
        this.question = question;
        this.view = view;
    }


    //metodo chiamato quando l'utente vuole rispondere a una domanda
    public void replyToQuestion(String text) throws TextException, PersistanceLayerNotAvailable {

        //per prima cosa controllo se l'utente è loggato
        if(!CurrentUserProfileBean.getProfileInstance().isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }

        //prendo l'username dell'utente attuale
        String author = CurrentUserProfileBean.getProfileInstance().getUsername();

        //controllo sintattico della lunghezza del testo
        ResponseBean responseBean = new ResponseBean(author);
        responseBean.setText(text);

        //chiamata del controller applicativo
        PostResponseControllerAppl postResponseControllerAppl = new PostResponseControllerAppl();
        postResponseControllerAppl.setControllerGrafico(this);
        try {
            postResponseControllerAppl.checkAndProcessResponse(responseBean, question);
        }catch(PersistanceLayerNotAvailable e){
            throw new PersistanceLayerNotAvailable("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        }
    }

    @Override
    public void bannedWordPresent() {
        this.view.bannedWordPresent();
    }



}
