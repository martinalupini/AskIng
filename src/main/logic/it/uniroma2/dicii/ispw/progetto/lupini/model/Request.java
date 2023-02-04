package it.uniroma2.dicii.ispw.progetto.lupini.model;

public class Request {

    UserProfile author;

    String text;

    public Request(String text, UserProfile user){
        this.author = user;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public UserProfile getAuthor() {
        return author;
    }
}
