package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione lanciata quando il numero di keywords è 0 o superiore a tre
public class KeywordsException extends Exception {

    private final String message;

    public KeywordsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
