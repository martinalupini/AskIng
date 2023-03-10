package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.CheckBannedWords;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.IncreaseUserPoints;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionsAndResponsesFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewResponseControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ResponseDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;


public class PostResponseControllerAppl {


    private NewResponseControllerInterface controllerGrafico;

    public void setControllerGrafico(NewResponseControllerInterface controllerGrafico) {
        this.controllerGrafico = controllerGrafico;
    }

    public void checkAndProcessResponse(ResponseBean responseBean, QuestionBean questionBean) throws PersistanceLayerNotAvailable {

        try {
            CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();

            //controllo se la risposta contiene parole bannate
            CheckBannedWords.checkText(responseBean.getText());

            //se no procedo con il salvataggio della risposta in memoria
            Response res = new Response(responseBean.getText(), currentUserProfile.getCurrentUser());
            ResponseDAOJDBC responseDAOJDBC = new ResponseDAOJDBC();
            responseDAOJDBC.saveNewResponse(res, questionBean.getId());

            //aggiungo la risposta alla relativa domanda
            QuestionsAndResponsesFactory.getCurrentInstance().addResponseToQuestion(questionBean.getId(), res);

            //aggiurno il punteggio dell'utente
            if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                IncreaseUserPoints.increaseUserPoints();
            }

            //aggiungo la risposta
            questionBean.addResponse(responseBean);

        } catch (BannedWordFoundException e) {
            this.controllerGrafico.bannedWordPresent();
        }

    }
}
