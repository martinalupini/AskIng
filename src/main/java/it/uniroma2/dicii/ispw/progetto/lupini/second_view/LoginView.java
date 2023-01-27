package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.LoginControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

import java.util.Scanner;

public class LoginView {

    private LoginControllerGraficoCLI controllerG = new LoginControllerGraficoCLI();

    public void displayForm(){
        String line;
        String password;
        String username;
        Scanner reader = new Scanner(System.in);

        System.out.println("""
                \n\n------------------------------------------LOGIN------------------------------------------

                Per eseguire questa operazione devi prima effettuare il login.\nInserire "c" se si vuole continuare oppure "h" se si vuole tornare all'homepage: 

                """);

        line = reader.nextLine();
        if(line.toLowerCase().equals("h")){
            controllerG.goToHomepage();
            return;
        }

        while(true) {
            System.out.println("Inserire il proprio username: ");
            username = reader.nextLine();
            System.out.println("Inserire la propria password: ");
            password = reader.nextLine();

            LoginControllerGraficoCLI controller = new LoginControllerGraficoCLI();
            try {
                controller.selectedLogin(username,password);
                return;
            } catch (ItemNotFound e) {
                System.out.println("Username o password errati. Riprovare");
            } catch (PersistanceLayerNotAvailable e) {
                System.out.println("Spiacenti, si sono verificati problemi interni. Riprovare più tardi.");
            }

        }

    }
}
