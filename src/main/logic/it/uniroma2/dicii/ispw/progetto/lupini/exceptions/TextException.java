package it.uniroma2.dicii.ispw.progetto.lupini.exceptions;

//eccezione che si ha quando il testo che ha inserito l'utente è vuoto o supera il
// massimo numero di parole consentite
public class TextException extends Exception{

    private final String message;

    public TextException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
