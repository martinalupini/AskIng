package it.uniroma2.dicii.ispw.progetto.lupini.api_boundary;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;

import java.text.SimpleDateFormat;

public class RequestUserAPI {

    private NewRequestControllerGraficoInterface userControllerGrafico;
    private RequestControllerAppl requestControllerAppl;

    public RequestUserAPI(){}

    public void setRequestControllerAppl(RequestControllerAppl requestControllerAppl) {
        this.requestControllerAppl = requestControllerAppl;
    }

    public void setUserControllerGrafico(NewRequestControllerGraficoInterface userControllerGrafico) {
        this.userControllerGrafico = userControllerGrafico;
    }

    public void sendRequest(RequestBean request) throws PersistanceLayerNotAvailable, RequestAlreadyDone, ItemNotFound {
        //prima istanzio il controller applicativo (essendo che il caso d'uso è stato appena inizializzato l'attributo rispettivo avrà valore null)
        this.requestControllerAppl=  new RequestControllerAppl();
        this.requestControllerAppl.setRegularUserBoundary(this);
        //poi chiamo il metodo del controller applicativo per processare la richiesta
        this.requestControllerAppl.processRequest(request);
    }

    public void updateStatus() {
        this.userControllerGrafico.updateStatus();
    }

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
