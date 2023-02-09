package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.PostResponseControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionOfSectionFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewResponseControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.LoginView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.QuestionView;
import java.util.List;

public class QuestionControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements NewResponseControllerInterface {

    private QuestionBean question;

    private QuestionView view;

    public QuestionControllerGraficoCLI(QuestionBean question, QuestionView view){
        this.question = question;
        this.view = view;
    }


    public void replyToQuestion(String text) throws TextException, PersistanceLayerNotAvailable {
        if(!CurrentUserProfileBean.getProfileInstance().isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }

        String author = CurrentUserProfileBean.getProfileInstance().getUsername();

        ResponseBean responseBean = new ResponseBean(author);

        responseBean.setText(text);
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
