package it.uniroma2.dicii.ispw.progetto.lupini.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String questionText;
    private List<String> keywords;
    private UserProfile author;
    private List<Response> responses;


    //constructor with parameters because is used in ForumSection
    public Question(String text, List<String> keywords, UserProfile author, int id, List<Response> responses){
        this.questionText = text;
        this.keywords = keywords;
        this.author = author;
        this.id = id;
        this.responses = new ArrayList<>();
        for(Response r: responses){
            this.responses.add(r.clone());
        }
    }

    //usata quando ho una nuova domanda
    public Question(String text, List<String> keywords, UserProfile author){
        this.questionText = text;
        this.keywords = keywords;
        this.author = author;
    }

    /* the relationship between question and responses is a relation of composition. A response exists only if the question
        associated exists. If the question is deleted so are the responses.
         */

    public List<Response> getResponses(){
        List<Response>  copyResponses = new ArrayList<>();
        for(Response r: this.responses){
            copyResponses.add(r.clone());
        }
        return copyResponses;
    }

    public void addResponse(Response r){
        responses.add(r.clone());
    }

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

    public Question clone(){
        return new Question(this.questionText, this.keywords, this.author, this.id, this.responses);
    }
}
