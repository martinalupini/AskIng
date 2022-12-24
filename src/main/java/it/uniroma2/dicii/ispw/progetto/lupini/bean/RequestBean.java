package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

import java.util.StringTokenizer;

public class RequestBean {
    private String username = "";

    private int badBehaviour;

    private int points;
    private String text = "";

    public RequestBean(String user){
        this.username = user;
    }

    public String getUsername() {
        return username;
    }

    public String getText() { return text; }

    public void  checkText() throws TextException{
        StringTokenizer strTok = new StringTokenizer(text, " ");
        if(text.isEmpty()){
            throw new TextException("Inserire il testo");
        }

        int wordCount = strTok.countTokens();

        if(wordCount > 1000){
            throw new TextException("Il numero massimo di parole inseribili è 1000");
        }

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + "----" + text;
    }
}
