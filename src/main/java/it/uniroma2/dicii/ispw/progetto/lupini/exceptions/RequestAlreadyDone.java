package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class RequestAlreadyDone extends Exception{

    private final String message;


    public RequestAlreadyDone(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
