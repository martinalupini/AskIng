package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

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
