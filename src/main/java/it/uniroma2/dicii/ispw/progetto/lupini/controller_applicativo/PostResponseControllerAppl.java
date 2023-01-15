package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.CheckBannedWords;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.IncreaseUserPoints;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ResponseDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.view.ViewQuestionController;

import java.util.ArrayList;
import java.util.List;

public class PostResponseControllerAppl {

    private ViewQuestionController viewQuestionController;

    public PostResponseControllerAppl(ViewQuestionController viewQuestionController){
        this.viewQuestionController = viewQuestionController;
    }
    public List<ResponseBean> returnResponsesOfQuestion(int questionId) throws PersistanceLayerNotAvailable {

        List<ResponseBean> list = new ArrayList<>();

        QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
        try {

            List<Response> responses = questionDAOJDBC.retrieveResponseFromQuestionID(questionId);
            for(Response r : responses){
                list.add(convertResponse(r));
            }

        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle risposte. Riprovare più tardi");
        }

        return list;


    }

    private ResponseBean convertResponse( Response response){
        String username = response.getAuthor().getUsername();
        String text = response.getResponseText();

        return new ResponseBean(username, text);

    }


    public void checkAndProcessResponse(ResponseBean responseBean, int id) throws PersistanceLayerNotAvailable {

        try {
            CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();

            CheckBannedWords.checkText(responseBean.getText());

            Response res = new Response(responseBean.getText(), currentUserProfile.getCurrentUser());

            ResponseDAOJDBC responseDAOJDBC = new ResponseDAOJDBC();
            responseDAOJDBC.saveNewResponse(res, id);

            if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                IncreaseUserPoints.increaseUserPoints();
            }

            this.viewQuestionController.responseSuccessful(responseBean);

        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        } catch (BannedWordFoundException e) {
            this.viewQuestionController.bannedWordPresent();
        }

    }
}
