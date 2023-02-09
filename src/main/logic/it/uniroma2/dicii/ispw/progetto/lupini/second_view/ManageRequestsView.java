package it.uniroma2.dicii.ispw.progetto.lupini.second_view;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view.ManageRequestsControllerGraficoCLI;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;

import java.util.List;
import java.util.Scanner;

public class ManageRequestsView {

    private ManageRequestsControllerGraficoCLI controller;
    private List<RequestBean> requests;

    public ManageRequestsView(){
        this.controller = new ManageRequestsControllerGraficoCLI(this);
    }

    public void displayRequests(){
        Scanner reader = new Scanner(System.in);
        String line;

        Print.print("\n\n------------------------------------------NUOVE RICHIESTE------------------------------------------\n\n");
        int i = 1;

        try {
            this.requests = this.controller.getRequests();

            for(RequestBean r : this.requests){
                Print.print(i+") "+r.getUsername()+"\n"+r.getText()+"\n");
                i++;
            }

        } catch (PersistanceLayerNotAvailable e) {
            Print.printError("Spiacenti, si sono verificati degli errori interni. Riprovare più tardi.");
            this.controller.goToHomepage();
        }

        Print.print("Selezionare l'operazione che si vuole svolgere:\na) Esaminare una richiesta\nb) Tornare all'homepage\n" +
                "Inserire qui la propria scelta: ");

        while (true) {
            line = reader.nextLine();
            if (line.equalsIgnoreCase("a")) {
                Print.print("Inserire il numero della richiesta che si vuole consultare: ");
                i = reader.nextInt();
                this.controller.goToRequest(i);
                return;


            }
            if (line.equalsIgnoreCase("b")) {
                this.controller.goToHomepage();
                return;
            } else {
                Print.printError("Opzione inserita non valida riprovare: ");
            }
        }
    }
}
