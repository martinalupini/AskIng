package it.uniroma2.dicii.ispw.progetto.lupini.bean.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

public class CheckTextLenght {

    private CheckTextLenght(){}

    public static void checkTextLength(String text) throws TextException {
        if(text.isEmpty()){
            throw new TextException("Inserire il testo della domanda");
        }
        String[] words = text.split(" ");
        if(words.length >= 1000){
            throw new TextException("Il testo della domanda può essere al massimo di 1000 parole");
        }
    }
}
