package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.BecomeModeratorControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;

import java.util.Scanner;

public class BecomeModeratorFormView {

    private BecomeModeratorControllerGraficoCLI controller;

    public BecomeModeratorFormView(){
        this.controller = new BecomeModeratorControllerGraficoCLI(this);
    }

    public void displayForm(){
        String text;
        String line;
        Scanner reader = new Scanner(System.in);

        System.out.println("Manda qui la tua richiesta per diventare moderatore.\n" +
                "Chi valuterà la tua richiesta vedrà il tuo username, la tua email, i tuoi punti accumulati e il tuo BadBehaviour. " +
                "Scrivi qui un breve testo sul perchè vuoi diventare moderatore:");

        text = reader.nextLine();
        try {
            this.controller.sendRequest(text);
        } catch (PersistanceLayerNotAvailable | RequestAlreadyDone e) {
            System.err.println(e.getMessage());
        }
    }

    public void requestSuccessful(){
        System.out.println("La tua richiesta è stata mandata con successo.");
    }
}
