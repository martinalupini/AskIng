package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione lanciata quando non viene trovato l'utente dato il suo username
public class ItemNotFound extends  Exception{
    private final String message;

    public ItemNotFound(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
