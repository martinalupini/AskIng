package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione lanciata quando un utente tenta di fare una richiesta quando ne è già presente un'altra in sospeso
//(un utente può fare al massimo una richiesta alla volta)
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
