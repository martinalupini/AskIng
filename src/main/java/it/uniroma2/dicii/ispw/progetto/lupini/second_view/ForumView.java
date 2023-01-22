package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.ClearScreen;

import java.util.Scanner;

public class ForumView {

    private final String[]  courses = {"Algebra e Logica", "Analisi 1", "Analisi 2", "Automi e Linguaggi", "Basi di Dati", "Calcolatori Elettronici", "Campi Elettromagnetici", "Fisica 1", "Fisica 2", "Fondamenti di Controlli", "Fondamenti di Informatica", "Fondamenti di Elelttronica","Fondamenti di Telecomunicazione", "Geometria", "Ingegneria degli Algoritmi", "Ingegneria di Internet e Web","Ingegneria del Software e Progettazione Web", "Probabilità e Statistica", "Ricerca Operativa", "Sistemi Operativi"};
    public void displayForum(){
        Scanner scanner = new Scanner(System.in);
        String line;

        ClearScreen.clearScreen();

        System.out.println("------------------------------------------FORUM------------------------------------------\n\n");
        for( String s : courses){
            System.out.println(s+"\n");
        }
        System.out.println("\nPer accedere alle domande scrivere il nome della sezione: ");
        line = scanner.nextLine();
    }
}
