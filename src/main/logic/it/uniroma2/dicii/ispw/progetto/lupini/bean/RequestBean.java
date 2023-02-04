package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

import java.util.StringTokenizer;

public class RequestBean {
    private String username = "";

    private int badBehaviour;

    private int points;

    private String email;
    private String text = "";

    public RequestBean(String text, String user, String email, int points, int badBehaviour){
        this.username = user;
        this.text = text;
        this.points = points;
        this.badBehaviour = badBehaviour;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getText() { return text; }

    public String getEmail() {
        return email;
    }

    public int getPoints() {
        return points;
    }

    public int getBadBehaviour() {
        return badBehaviour;
    }

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
