package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.engineering.CheckTextLenght;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

public class RequestBean {
    private String username = "";

    private int badBehaviour;

    private int points;

    private String email;
    private String text = "";

    public RequestBean(String user, String email, int points, int badBehaviour){
        this.username = user;
        this.points = points;
        this.badBehaviour = badBehaviour;
        this.email = email;
    }

    public RequestBean(String text, String user, String email, int points, int badBehaviour){
        this.username = user;
        this.points = points;
        this.badBehaviour = badBehaviour;
        this.email = email;
        this.text = text;
    }
    public void setText(String text) throws TextException {
        CheckTextLenght.checkTextLength(text);
        this.text = text;
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


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username + "----" + text;
    }
}
