package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.HomepageControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.ClearScreen;

import java.util.Scanner;

public class HomepageView {

    private HomepageControllerGraficoCLI controller = new HomepageControllerGraficoCLI();

    public void displayHomepage(){
        Scanner reader = new Scanner(System.in);
        int selection;

        ClearScreen.clearScreen();
        System.out.println("------------------------------------------HOMEPAGE AskIng------------------------------------------\n\n" +
                "Bentornati nell'homepage di AskIng.Selezionate cosa volete fare:\n" +
                "1) Consultare il forum\n" +
                "2) Visualizzare profilo\n" +
                "3) Richiedere di diventare moderatore\n" +
                "4) Uscire");

        selection = reader.nextInt();
        while(true){
            if(selection == 1){
                this.controller.goToForum();
                break;
            }
            if(selection == 2){
                this.controller.goToProfile();
            }
        }


    }
}
