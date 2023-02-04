package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class ImpossibleStartGUI extends RuntimeException{

    private final String message;


    public ImpossibleStartGUI(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
