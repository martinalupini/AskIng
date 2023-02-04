package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.ForumControllerGraficoCLI;

import java.util.Scanner;

public class ForumView {

    private ForumControllerGraficoCLI controller = new ForumControllerGraficoCLI();

    private final String[]  courses = {"Algebra e Logica", "Analisi 1", "Analisi 2", "Automi e Linguaggi", "Basi di Dati", "Calcolatori Elettronici", "Campi Elettromagnetici", "Fisica 1", "Fisica 2", "Fondamenti di Controlli", "Fondamenti di Informatica", "Fondamenti di Elelttronica","Fondamenti di Telecomunicazione", "Geometria", "Ingegneria degli Algoritmi", "Ingegneria di Internet e Web","Ingegneria del Software e Progettazione Web", "Probabilità e Statistica", "Ricerca Operativa", "Sistemi Operativi"};
    public void displayForum(){
        Scanner scanner = new Scanner(System.in);
        String line;

        System.out.println("\n\n------------------------------------------FORUM------------------------------------------\n\n");
        for( String s : courses){
            System.out.println(s+"\n");
        }

        System.out.println("\nSelezionare l'operazione che si desidera fare:\na) Consultare una sezione\nb) Tornare nell'homepage\nInserire qui la propria scelta: ");

        while(true) {
            line = scanner.nextLine();

            if ((line.toLowerCase()).equals("b")) {
                controller.goToHomepage();
                return;
            }
            if ((line.toLowerCase().equals("a"))) {
                goToSectionSelected(scanner);
            }
        }
    }

    private void goToSectionSelected(Scanner scanner){
        String line;
        while(true){
            System.out.println("Inserire il nome della sezione che si vuole consultare: ");
            line = scanner.nextLine();

            for(String s: courses){
                s = s.toLowerCase();
                if(s.equals(line)){
                    this.controller.goToSection(line.toLowerCase());
                    return;
                }
            }
            System.err.println("Sezione non esistente");
        }
    }
}
