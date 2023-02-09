package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.LoginControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.Scanner;

public class LoginView {

    private LoginControllerGraficoCLI controllerG = new LoginControllerGraficoCLI();

    public void displayForm(){
        String line;
        String password;
        String username;
        Scanner reader = new Scanner(System.in);

        Print.print("""
                ------------------------------------------LOGIN------------------------------------------

                Per eseguire questa operazione devi prima effettuare il login.
                Inserire "c" se si vuole continuare oppure "h" se si vuole tornare all'homepage: 

                """);

        line = reader.nextLine();
        if(line.equalsIgnoreCase("h")){
            controllerG.goToHomepage();
            return;
        }

        while(true) {
            Print.print("Inserire il proprio username: ");
            username = reader.nextLine();
            Print.print("Inserire la propria password: ");
            password = reader.nextLine();

            LoginControllerGraficoCLI controller = new LoginControllerGraficoCLI();
            try {
                controller.selectedLogin(username,password);
                return;
            } catch (ItemNotFound e) {
                Print.printError("Username o password errati. Riprovare");
            } catch (PersistanceLayerNotAvailable e) {
                Print.printError("Spiacenti, si sono verificati problemi interni. Riprovare più tardi.");
                this.controllerG.goToHomepage();
            }

        }

    }
}
