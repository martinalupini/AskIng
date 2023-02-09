package it.uniroma2.dicii.ispw.progetto.lupini.api_boundary;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;

import java.text.SimpleDateFormat;

public class RequestUserAPI {

    private NewRequestControllerGraficoInterface userControllerGrafico;
    private RequestControllerAppl requestControllerAppl;

    public void setRequestControllerAppl(RequestControllerAppl requestControllerAppl) {
        this.requestControllerAppl = requestControllerAppl;
    }

    public void setUserControllerGrafico(NewRequestControllerGraficoInterface userControllerGrafico) {
        this.userControllerGrafico = userControllerGrafico;
    }

    //chiamata dal controller grafico per inviare la richiesta.
    // Il metodo chiama la rispettiva operazione del controller applicativo
    public void sendRequest(RequestBean request) throws PersistanceLayerNotAvailable, RequestAlreadyDone {
        this.requestControllerAppl=  new RequestControllerAppl();
        this.requestControllerAppl.setRegularUserBoundary(this);
        this.requestControllerAppl.processRequest(request);
    }

    //chiamata dal controller applicativo per indicare che la richiesta è stata salvata correttamente
    public void updateStatus() {
        this.userControllerGrafico.updateStatus();
    }

    //chiamata dal controller applicativo per notificare all'utente l'esito della sua richiesta
    public void notifyUser(String username, String status) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Notification notification = new Notification();
        String destination=username+ "_"+timeStamp;
        if(status.equals("accepted")) {
            notification.notify(destination, "Complimenti! sei diventato moderatore. Accedi al sistema per vedere tutte le tue nuove funzionalità");
        }else{
            notification.notify(destination, "Mi dispiace la tua richiesta per diventare moderatore è stata rifiutata. Continua a partecipare al forum e acquista sempre più competenze per poter fare la richiesta in futuro.");
        }
    }
}
