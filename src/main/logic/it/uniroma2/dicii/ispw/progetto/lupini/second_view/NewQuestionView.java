package it.uniroma2.dicii.ispw.progetto.lupini.second_view;


import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.NewQuestionViewControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.KeywordsException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

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

        Print.print("""
                
                
                ------------------------------------------NUOVA DOMANDA------------------------------------------

                Inserire "h" se si vuole tornare all'homepage oppure premere invio per continuare: 

                """);

        line = reader.nextLine();
        if(line.equalsIgnoreCase("h")){
            this.controller.goToHomepage();
            return;
        }

        while(true){

            Print.print("Inserire qui delle keywords inerenti alla domanda che si vuole fare (separare ogni parola con uno spazio): ");
            keywords = reader.nextLine();
            Print.print("Inserire qui il testo della propria domanda: ");
            text = reader.nextLine();

            try {
                this.controller.submitQuestion(keywords, text);
                return;
            } catch (KeywordsException | TextException | PersistanceLayerNotAvailable e) {
                Print.print(e.getMessage());
                this.controller.goToHomepage();
            }

        }

    }

    public void success(){
        Print.print("La tua domanda è stata pubblicata");
        this.controller.goToHomepage();
    }

    public void bannedWordInText(){
        Print.printError("Sono state rilevate delle parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
        this.controller.goToHomepage();
    }
}
