package it.uniroma2.dicii.ispw.progetto.lupini.model;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;

public class Response {

    private String responseText;
    private UserProfile author;

    //constructor with parametres because is used in question (composition)
    public Response(String text, UserProfile author){
        this.responseText = text;
        this.author =  author;
    }

    /*   VEDI SE ELIMINARE
    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }*/


    //getters
    public String getResponseText() {
        return responseText;
    }

    public UserProfile getAuthor() {
        return author;
    }
}
