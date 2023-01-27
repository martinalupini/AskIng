package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.SectionControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

import java.util.List;
import java.util.Scanner;

public class SectionView {

    private String name;

    private List<QuestionBean> questions;

    private SectionControllerGraficoCLI controller;

    public SectionView(String name){

        this.name = name;
        this.controller = new SectionControllerGraficoCLI(name);
    }

    public void displaySection(){

        Scanner reader = new Scanner(System.in);
        String line;

        System.out.println("\n\n------------------------------------------"+this.name.toUpperCase()+"------------------------------------------\n\n");
        int i = 1;

        try {
            this.questions = this.controller.getQuestionOfSection(this.name);

            for(QuestionBean q : this.questions){
                System.out.println(i+") "+q.getUsername()+"\n"+q.getText()+"\n");
                i++;
            }

        } catch (PersistanceLayerNotAvailable e) {
            System.out.println("Spiacenti, si sono verificati degli errori interni. Riprovare più tardi.");
        }

        System.out.println("Selezionare l'operazione che si vuole svolgere:\na) Consultare una domanda\nb) Fare una domanda\nc) Tornare all'homepage\n" +
                "Inserire qui la propria scelta: ");

        while(true){
            line = reader.nextLine();
            if(line.toLowerCase().equals("a")){
                System.out.println("Inserire il numero della domanda che si vuole consultare: ");
                i = reader.nextInt();
                this.controller.goToQuestion(i);
                return;
            }
            if(line.toLowerCase().equals("b")){
                this.controller.doNewQuestion();
                return;
            }if(line.toLowerCase().equals("c")){
                this.controller.goToHomepage();
                return;
            }
            else{
                System.out.println("Opzione inserita non valida riprovare: ");
            }
        }

    }

}
