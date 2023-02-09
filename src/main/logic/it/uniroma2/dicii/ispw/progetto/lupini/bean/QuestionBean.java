package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.engineering.CheckTextLenght;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;


import java.util.ArrayList;
import java.util.List;

public class QuestionBean extends SubjectQuestionBean {
    String username;
    String text;

    int id;

    List<String> keywords;

    List<ResponseBean> responses= new ArrayList<>();


    public QuestionBean(String user){
        this.username = user;
    }

    public QuestionBean(String user, String text, List<String> keywords, int id){
        this.id = id;
        this.username = user;
        this.text = text;
        this.keywords = keywords;

    }

    public void addResponse(ResponseBean r){
        responses.add(r);
        notifyObservers();
    }

    public List<ResponseBean> getResponses() {
        return responses;
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

    public void setResponses(List<ResponseBean> responses) {
        this.responses = responses;
    }

    //la funzione serve a scomporre la stringa passata in keywords usando come token lo spazio vuoto
    private List<String> parseListKeywords(String text) {
        int i;

        List<String> list = new ArrayList<>();
        String[] words  = text.split(" ");

        //Legge più spazi vuoti come un unico spazio
        for(i=0; i< words.length; i++){
            if(words[i].matches("^[\\s\\p{P}]*$")){
                continue;
            }
            list.add(words[i]);
        }

        return list;

    }

    //controlla che il numero di keywords sia corretto altrimenti lancia un'eccezione
    private void checkKeywords( List<String> keywords)  throws KeywordsException{
        if(keywords.isEmpty()){
            throw new KeywordsException("Inserire almeno una keyword");
        }
        if(keywords.size()>3){
            throw new KeywordsException("Il numero massimo di keywords è 3");
        }
    }


    public void setKeywords(String listKeywords) throws  KeywordsException{
        List<String> keywordsList = parseListKeywords(listKeywords);
        checkKeywords(keywordsList);
        this.keywords = keywordsList;
    }

    //controlla che il numero di parole sia corretto altrimenti lancia un'eccezione
    public void setText(String text) throws TextException {
        CheckTextLenght.checkTextLength(text);
        this.text = text;
    }
}
