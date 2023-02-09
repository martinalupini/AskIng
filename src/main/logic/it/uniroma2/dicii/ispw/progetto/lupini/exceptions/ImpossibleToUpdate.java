package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione lanciata quando è impossibile aggiornare i points o il badbehaviour di un utente
public class ImpossibleToUpdate extends  Exception{

    private final String message;


    public ImpossibleToUpdate(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
