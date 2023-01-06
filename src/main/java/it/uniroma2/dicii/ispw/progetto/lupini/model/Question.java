package it.uniroma2.dicii.ispw.progetto.lupini.model;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String questionText;
    private List<String> keywords;
    private UserProfile author;
    private List<Response> responses = new ArrayList<>();


    //constructor with parameters because is used in ForumSection
    public Question(String text, List<String> keywords, UserProfile author, int id){
        this.questionText = text;
        this.keywords = keywords;
        this.author = author;
        this.id = id;
    }

    public Question(String text, List<String> keywords, UserProfile author){
        this.questionText = text;
        this.keywords = keywords;
        this.author = author;
    }

    /* the relationship between question and responses is a relation of composition. A response exists only if the question
        associated exists. If the question is deleted so are the responses.
         */

    public Question cloneQuestion(){
        Question clone = new Question(this.getQuestionText(), this.getKeywords(), this.getAuthor(), this.getId());
        return clone;
    }

    public void newResponse(String text){
        CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
        UserProfile creator =   currentUserProfile.getCurrentUser();
        Response res = new Response(text, creator);
        responses.add(res);
    }

    public List<Response> getResponses() {
        List<Response> newList = new ArrayList<>();

        if(responses.isEmpty()){

            QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
            responses = questionDAOJDBC.retrieveResponseFromQuestionID(this.id);
        }

        for (Response r : responses) {
                Response newR = r.cloneResponse();
                newList.add(newR);
        }

        return newList;
    }

    //getters

    public UserProfile getAuthor() {
        return author;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getId() {
        return id;
    }
}
