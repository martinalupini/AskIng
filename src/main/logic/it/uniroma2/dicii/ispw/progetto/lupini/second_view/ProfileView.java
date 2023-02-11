package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.ProfileControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.Scanner;

public class ProfileView {

    ProfileControllerGraficoCLI controller = new ProfileControllerGraficoCLI();

    public void displayProfile(){
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        UserProfileBean user = currentUserProfileBean.getUser();
        Scanner reader = new Scanner(System.in);
        String line;

        Print.print("\n\n------------------------------------------IL TUO PROFILO------------------------------------------\n\n" +
                "\nUsername: "+user.getUsername()+"\nEmail: "+user.getEmail());

        if(user.getRole().equals("regular user")){
            Print.print("\nPoints: "+user.getPoints()+"\nBad Behaviour: "+user.getBadBehaviour());
        }

        Print.print("Inserire 'h' se si vuole tornare all'homepage: ");

        while(true) {
            line = reader.nextLine();
            if(line.equalsIgnoreCase("h")){
                this.controller.goToHomepage();
                return;
            }else{
                Print.printError("Opzione inserita non valida riprovare: ");
            }
        }
    }
}
