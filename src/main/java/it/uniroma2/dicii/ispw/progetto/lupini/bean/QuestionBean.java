package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.view.ResponseItemController;
import it.uniroma2.dicii.ispw.progetto.lupini.view.ViewQuestionController;

import java.util.ArrayList;
import java.util.List;

public class QuestionBean {
    String username = "";
    String text = "";

    int ID ;

    List<String> keywords;

    List<ResponseBean> responses = new ArrayList<>();

    public QuestionBean(String user, String text, List<String> keywords, int id){
        this.ID = id;
        this.username = user;
        this.text = text;
        this.keywords = keywords;

    }

    public void checkKeywords(List<String> keywords)  throws KeywordsException{
        if(keywords.isEmpty()){
            throw new KeywordsException("Inserire almeno una keyword");
        }
        if(keywords.size()>3){
            throw new KeywordsException("Il numero massimo di keywords è 3");
        }
    }

    public void setKeywords(List<String> keywords) throws  KeywordsException{
            checkKeywords(keywords);
            this.keywords = keywords;
    }


    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public List<String> getKeywords() {
        return keywords;
    }




    public List<ResponseBean> getResponses() throws DBNotAvailable {
        getResponsesFromModel();
        return responses;
    }

    private void getResponsesFromModel() throws DBNotAvailable {
        if (responses.isEmpty()) {

                UserProfileDAO userProfileDAO = new UserProfileDAOJDBC();
            UserProfile user = null;
            try {
                user = userProfileDAO.retrieveUserFromUsername(this.username);
            } catch(ItemNotFound e){
                user = new UserProfile(username, "unknown", null);
            } catch(Exception e){
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle risposte. Riprovare più tardi");
            }

            Question question = new Question(text, keywords, user, ID);

                List<Response> responsesFromModel = question.getResponses();

                //Is needed to convert the questions retrieved from Question to QuestionBean

                for (Response r : responsesFromModel) {
                    responses.add(ViewQuestionController.convertResponse(r));
                }

        }
    }
}
