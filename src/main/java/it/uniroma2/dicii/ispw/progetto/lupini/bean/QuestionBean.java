package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;

import java.util.ArrayList;
import java.util.List;

public class QuestionBean {
    String username;
    String text;

    int id;

    List<String> keywords;

    List<ResponseBean> responses = new ArrayList<>();

    public QuestionBean(String user, String text, List<String> keywords, int id){
        this.id = id;
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

    public int getId() {
        return id;
    }




}
