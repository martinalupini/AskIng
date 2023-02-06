package it.uniroma2.dicii.ispw.progetto.lupini.second_view;


import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.NewQuestionViewControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;


import java.util.Scanner;

public class NewQuestionView {

    NewQuestionViewControllerGraficoCLI controller;

    public NewQuestionView(String section){
        this.controller = new NewQuestionViewControllerGraficoCLI(section, this);

    }

    public void displayForm(){
        String keywords;
        String text;
        String line;
        Scanner reader = new Scanner(System.in);

        System.out.println("""
                
                
                ------------------------------------------NUOVA DOMANDA------------------------------------------

                Per eseguire questa operazione devi prima effettuare il login.
                Inserire "c" se si vuole continuare oppure "h" se si vuole tornare all'homepage: 

                """);

        line = reader.nextLine();
        if(line.equalsIgnoreCase("h")){
            this.controller.goToHomepage();
            return;
        }

        while(true){

            System.out.println("Inserire qui delle keywords inerenti alla domanda che si vuole fare (separare ogni parola con uno spazio): ");
            keywords = reader.nextLine();
            System.out.println("Inserire qui il testo della propria domanda: ");
            text = reader.nextLine();

            try {
                this.controller.submitQuestion(keywords, text);
            } catch (KeywordsException | TextException | PersistanceLayerNotAvailable e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public void success(){
        System.out.println("La tua domanda è stata pubblicata");
    }

    public void bannedWordInText(){
        System.err.println("Sono state rilevate delle parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
    }
}
