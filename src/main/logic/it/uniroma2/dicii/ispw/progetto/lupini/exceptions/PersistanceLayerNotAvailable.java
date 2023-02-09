package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione lanciata quando ci sono peoblematiche legate al filesystem o al database
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
