package it.uniroma2.dicii.ispw.progetto.lupini.model;

import java.util.List;

public class Question {

    private String questionText;
    private List<String> keywords;
    private UserProfile author;
    private List<Response> responses = null;


    //constructor with parameters because is used in ForumSection
    public Question(String text, List<String> keywords, UserProfile author){
        this.questionText = text;
        this.keywords = keywords;
        this.author = author;
    }

    /* the relationship between question and responses is a relation of composition. A response exists only if the question
        associated exists. If the question is deleted so are the responses.
         */
    public void newResponse(String text){
        CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
        UserProfile author =   currentUserProfile.getCurrentUser();
        Response res = new Response(text, author);
        responses.add(res);
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



}
