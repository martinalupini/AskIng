package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.engineering.CheckTextLenght;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;


import java.util.ArrayList;
import java.util.List;

public class QuestionBean extends  SubjectQuestionBean{
    String username;
    String text;

    int id;

    List<String> keywords;

    List<ResponseBean> responses= new ArrayList<>();

    public void addResponse(ResponseBean r){
        responses.add(r);
        notifyObservers();
    }

    public List<ResponseBean> getResponses() {
        return responses;
    }

    public QuestionBean(String user){
        this.username = user;
    }

    public QuestionBean(String user, String text, List<String> keywords, int id){
        this.id = id;
        this.username = user;
        this.text = text;
        this.keywords = keywords;

    }

    //The syntax check of user inserted data is in charge of the bean class
    private void checkKeywords( List<String> keywords)  throws KeywordsException{
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

    public void setText(String text) throws TextException {
        CheckTextLenght.checkTextLength(text);
        this.text = text;
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

    public int getId() {
        return id;
    }




}
