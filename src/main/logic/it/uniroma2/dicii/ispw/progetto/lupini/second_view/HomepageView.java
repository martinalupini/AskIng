package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.HomepageControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.Scanner;

public class HomepageView {

    private HomepageControllerGraficoCLI controller = new HomepageControllerGraficoCLI();

    public void displayHomepage(){
        Scanner reader = new Scanner(System.in);
        String selection;

        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        Boolean regular = true;

        if(currentUserProfileBean.isLogged() && currentUserProfileBean.getRole().equals("moderator")) {
            regular = false;
        }

        if(Boolean.TRUE.equals(regular)) {
            Print.print("\n\n------------------------------------------HOMEPAGE AskIng------------------------------------------\n\n" +
                    "Bentornati nell'homepage di AskIng.Selezionate cosa volete fare:\n" +
                    "a) Consultare il forum\n" +
                    "b) Visualizzare profilo\n" +
                    "c) Richiedere di diventare moderatore\n" +
                    "d) Uscire");
        }else{

            Print.print("\n\n------------------------------------------HOMEPAGE AskIng------------------------------------------\n\n" +
                    "Bentornati nell'homepage di AskIng.Selezionate cosa volete fare:\n" +
                    "a) Consultare il forum\n" +
                    "b) Visualizzare profilo\n" +
                    "c) Visualizzare richieste per diventare moderatori\n" +
                    "d) Uscire");

        }


        while(true){
            selection = reader.nextLine();

            switch(selection.toLowerCase()){
                case "a":
                    this.controller.goToForum();
                    return;
                case "b":
                    this.controller.goToProfile();
                    return;
                case "d":
                    this.controller.exit();
                    displayHomepage();
                    break;
                case "c":
                    if(Boolean.TRUE.equals(regular)) {
                        this.controller.selectedBecomeModerator();
                        return;
                    }else {
                        this.controller.selectedViewModeratorRequests();
                        return;
                    }
                default:
                    Print.printError("Opzione inserita non valida riprovare: ");
            }
        }


    }
}
