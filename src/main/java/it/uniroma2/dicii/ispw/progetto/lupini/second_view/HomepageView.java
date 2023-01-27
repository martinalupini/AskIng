package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.HomepageControllerGraficoCLI;

import java.util.Scanner;

public class HomepageView {

    private HomepageControllerGraficoCLI controller = new HomepageControllerGraficoCLI();

    public void displayHomepage(){
        Scanner reader = new Scanner(System.in);
        int selection;

        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        Boolean regular = true;

        if(currentUserProfileBean.isLogged() && currentUserProfileBean.getRole().equals("moderator"))  regular = false;

        if(regular) {
            System.out.println("\n\n------------------------------------------HOMEPAGE AskIng------------------------------------------\n\n" +
                    "Bentornati nell'homepage di AskIng.Selezionate cosa volete fare:\n" +
                    "1) Consultare il forum\n" +
                    "2) Visualizzare profilo\n" +
                    "3) Richiedere di diventare moderatore\n" +
                    "4) Uscire");
        }else{

            System.out.println("\n\n------------------------------------------HOMEPAGE AskIng------------------------------------------\n\n" +
                    "Bentornati nell'homepage di AskIng.Selezionate cosa volete fare:\n" +
                    "1) Consultare il forum\n" +
                    "2) Visualizzare profilo\n" +
                    "3) Visualizzare richieste per diventare moderatori\n" +
                    "4) Uscire");

        }


        while(true){
            selection = reader.nextInt();
            if(selection == 1){
                this.controller.goToForum();
                return;
            }
            if(selection == 2) {
                this.controller.goToProfile();
                return;
            }if(selection ==4) {
                this.controller.exit();
                return;
            }if(selection ==3 && regular) {
                this.controller.selectedBecomeModerator();
                return;
            }if(selection ==3 && !regular){
                this.controller.selectedViewModeratorRequests();
                return;
            }else{
                System.out.println("Opzione inserita non valida riprovare: ");
            }
        }


    }
}
