package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class DBNotAvailable extends Exception{

    private final String message;

    public DBNotAvailable(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
