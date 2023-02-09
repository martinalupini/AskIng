package it.uniroma2.dicii.ispw.progetto.lupini.second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.RequestViewControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.Scanner;

public class RequestView {

    private RequestBean request;
    private RequestViewControllerGraficoCLI controller;

    public RequestView(RequestBean request){
        this.request = request;
        this.controller = new RequestViewControllerGraficoCLI(this);
    }

    public void displayRequest() {
        Scanner reader = new Scanner(System.in);
        String line;

        Print.print("\n\n------------------------------------------RICHIESTA------------------------------------------\n\n" +
                "INFORMAZIONI SULL'UTENTE" +
                "Username: "+this.request.getUsername()+"\nEmail: "+this.request.getEmail()+"\nPoints: "+this.request.getPoints()+"" +
                "\nBad Behaviour: "+this.request.getBadBehaviour()+"\nRICHIESTA\n"+this.request.getText());


        while (true) {
            Print.print("Selezionare l'operazione che si vuole svolgere:\na) Accettare la richiesta\nb) Rifiutare la richiesta\nc) Tornare all'homepage\n" +
                    "Inserire qui la propria scelta: ");
            line = reader.nextLine();
            try{
            if (line.equalsIgnoreCase("a")) {

                this.controller.acceptRequest(this.request);
                return;

            }else if(line.equalsIgnoreCase("b")){

                this.controller.declineRequest(this.request);
                return;

            }else if (line.equalsIgnoreCase("c")) {
                this.controller.goToHomepage();
                return;
            } else {
                Print.printError("Opzione inserita non valida riprovare: ");
            }
            } catch (PersistanceLayerNotAvailable e) {
                Print.printError("Spiacenti si sono verificati degli errori interni. Riprovare più tardi.");
            }
        }
    }

    public void updateStatusRequest(String status) {
        Print.print("RICHIESTA "+status+" CON SUCCESSO");
    }
}
