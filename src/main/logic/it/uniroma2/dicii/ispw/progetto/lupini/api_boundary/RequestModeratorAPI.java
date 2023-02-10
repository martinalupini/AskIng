package it.uniroma2.dicii.ispw.progetto.lupini.api_boundary;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import java.text.SimpleDateFormat;

public class RequestModeratorAPI {

    private ManageRequestsContrGrafInterface moderatorControllerGrafico;
    private RequestControllerAppl requestControllerAppl;


    public void setModeratorControllerGrafico(ManageRequestsContrGrafInterface moderatorControllerGrafico) {
        this.moderatorControllerGrafico = moderatorControllerGrafico;
    }

    public void setRequestControllerAppl(RequestControllerAppl requestControllerAppl) {
        this.requestControllerAppl = requestControllerAppl;
    }

    //usata dal controller grafico per indicare che il moderatore vuole accettare/rifiutare la richiesta
    public void updateRequestState(String username, String state) throws PersistanceLayerNotAvailable, ImpossibleToUpdate {
        this.requestControllerAppl = new RequestControllerAppl();
        this.requestControllerAppl.setModeratorBoundary(this);
        this.requestControllerAppl.updateRequestState(username, state);
    }

    //invocata dal controller applicativo per aggiornare il moderatore se l'operazione
    //di accettazione o rifiuto è andata a buon fine
    public void requestStateUpdatedCorrectly(String status){
        this.moderatorControllerGrafico.requestStateUpdatedCorrectly(status);
    }


    //invocata dal controller applicativo per notificare il moderatore di una nuova richiesta
    public void notifyModeratorNewRequest() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Notification notification = new Notification();
        String destination= "moderator_"+timeStamp;
        notification.notify(destination, "E' arrivata una nuova richiesta di diventare moderatore. Controllare l'applicazione.");

    }
}
