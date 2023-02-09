package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.BecomeModeratorControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.Scanner;

public class BecomeModeratorFormView {

    private BecomeModeratorControllerGraficoCLI controller;


    public void displayForm(){
        String text;
        Scanner reader = new Scanner(System.in);

        Print.print("Manda qui la tua richiesta per diventare moderatore.\n" +
                "Chi valuterà la tua richiesta vedrà il tuo username, la tua email, i tuoi punti accumulati e il tuo BadBehaviour. " +
                "Scrivi qui un breve testo sul perchè vuoi diventare moderatore:");

        text = reader.nextLine();
        try {
            this.controller = new BecomeModeratorControllerGraficoCLI();
            this.controller.setView(this);
            this.controller.sendRequest(text);
        } catch (PersistanceLayerNotAvailable | RequestAlreadyDone e) {
            Print.printError(e.getMessage());
            this.controller.goToHomepage();
        } catch (TextException e) {
            Print.printError(e.getMessage());
        }
    }

    public void requestSuccessful(){
        Print.print("La tua richiesta è stata mandata con successo.");
    }
}
