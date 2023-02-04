package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

public class PersistanceLayerNotAvailable extends Exception{

    private final String message;

    public PersistanceLayerNotAvailable(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
