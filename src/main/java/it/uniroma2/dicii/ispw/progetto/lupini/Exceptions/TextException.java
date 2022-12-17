package it.uniroma2.dicii.ispw.progetto.lupini.Exceptions;

public class TextException extends Exception{

    private String message;

    public TextException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
