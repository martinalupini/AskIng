package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.QuestionControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

import java.util.List;
import java.util.Scanner;

public class QuestionView {

    private QuestionBean question;
    private QuestionControllerGraficoCLI controller;
    private List<ResponseBean> responses;

    public QuestionView(QuestionBean question){

        this.question = question;
        this.controller = new QuestionControllerGraficoCLI(question, this);
        this.question.attach(this.controller);
    }


    public void displayQuestion() {

        Scanner reader = new Scanner(System.in);
        String line;

        System.out.println("\n\n------------------------------------------DOMANDA------------------------------------------\n\n" +
                "Autore: "+this.question.getUsername()+"\nKeywords: "+this.question.getKeywords()+"\nTesto:\n"+this.question.getText());

        int i = 1;

        System.out.println("RISPOSTE");

        try {
            this.responses = this.controller.getResponsesOfQuestion(this.question.getId());

            for (ResponseBean r : this.responses) {
                System.out.println(i + ") " + r.getUsername() + "\n" + r.getText() + "\n");
                i++;
            }

        } catch (PersistanceLayerNotAvailable e) {
            System.err.println("Spiacenti, si sono verificati degli errori interni. Riprovare più tardi.");
        }

        System.out.println("Selezionare l'operazione che si vuole svolgere:\na) Aggiungere una risposta\nb) Tornare all'homepage\n" +
                "Inserire qui la propria scelta: ");

        while (true) {
            line = reader.nextLine();
            if (line.equalsIgnoreCase("a")) {
                try {
                    System.out.println("Inserire qui la propria risposta: ");
                    line = reader.nextLine();
                    this.controller.replyToQuestion(line);
                    return;
                } catch (TextException | PersistanceLayerNotAvailable e) {
                    System.out.println(e.getMessage());
                    return;
                }

            }
            if (line.equalsIgnoreCase("b")) {
                this.controller.goToHomepage();
                return;
            } else {
                System.err.println("Opzione inserita non valida. Riprovare. ");
            }
        }
    }


    public void bannedWordPresent(){
        System.out.println("Sono state rilevate delle parole non adeguate nel testo della domanda. Il tuo punteggio BadBehaviour è stato aumentato.");
    }

}
