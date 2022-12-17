package it.uniroma2.dicii.ispw.progetto.lupini.Exceptions;

public class KeywordsException extends Exception {

    String message;

    public KeywordsException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
