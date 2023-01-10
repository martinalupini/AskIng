package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;

import java.util.ArrayList;
import java.util.List;

public class PostQuestionControllerAppl {

    public List<QuestionBean> returnQuestionOfSection(String username) throws DBNotAvailable {

        List<QuestionBean> list = new ArrayList<>();

        try{

            ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();
             List<Question> questions = forumSectionDAOJDBC.retrieveQuestionOfSection(username);

            for(Question q : questions){
                list.add(convertQuestion(q));
            }

            return list;

        } catch (DBNotAvailable e) {
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle domande. Riprovare più tardi");
        }

    }

    private QuestionBean convertQuestion(Question question){
        return new QuestionBean(question.getAuthor().getUsername(), question.getQuestionText(), question.getKeywords(), question.getId());

    }

    public List<ResponseBean> returnResponsesOfQuestion(int questionId) throws DBNotAvailable {

        List<ResponseBean> list = new ArrayList<>();

        QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
        try {

            List<Response> responses = questionDAOJDBC.retrieveResponseFromQuestionID(questionId);
            for(Response r : responses){
                list.add(convertResponse(r));
            }

        } catch (DBNotAvailable e) {
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle risposte. Riprovare più tardi");
        }

        return list;


    }

    private ResponseBean convertResponse( Response response){
        String username = response.getAuthor().getUsername();
        String text = response.getResponseText();

        return new ResponseBean(username, text);

    }

}
