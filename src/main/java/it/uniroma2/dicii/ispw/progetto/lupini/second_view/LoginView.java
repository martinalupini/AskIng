package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.LoginControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.ClearScreen;

import java.util.Scanner;

public class LoginView {

    public void displayForm(){
        String password;
        String username;
        Scanner reader = new Scanner(System.in);
        ClearScreen.clearScreen();

        System.out.println("""
                ------------------------------------------LOGIN------------------------------------------

                Per eseguire questa operazione devi prima effettuare il login

                """);

        while(true) {
            System.out.println("Inserire il proprio username: ");
            username = reader.nextLine();
            System.out.println("Inserire la propria password: ");
            password = reader.nextLine();

            LoginControllerGraficoCLI controller = new LoginControllerGraficoCLI();
            try {
                controller.selectedLogin(username,password);
            } catch (ItemNotFound e) {
            } catch (PersistanceLayerNotAvailable e) {
                System.out.println("Spiacenti, si sono verificati problemi interni. Riprovare più tardi.");
            }

        }

    }
}
