package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class TextException extends Exception{

    private final String message;

    public TextException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
