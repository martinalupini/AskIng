package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class BannedWordFoundException extends Exception{
    private final String message;

    public BannedWordFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
