package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestModeratorAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.RequestView;


public class RequestViewControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements ManageRequestsContrGrafInterface {

    private RequestView view;

    public RequestViewControllerGraficoCLI(RequestView view){
        this.view = view;
    }

    //metodo chiamato quando il moderatore vuole accettare la richiesta. Il controller grafico comunica con un API
    //che mappa le chiamate del controller grafico su quelle del controller applicativo
    public void acceptRequest(RequestBean request) throws PersistanceLayerNotAvailable {

        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI();
        moderatorAPI.setModeratorControllerGrafico(this);
        try {
            moderatorAPI.updateRequestState( request.getUsername(), "accepted");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere accetta per motivi tecnici. Riprovare più tardi.");
        }

    }


    //aggiorna il moderatore che la richiesta è andata a buon fine
    @Override
    public void requestStateUpdatedCorrectly(String status) {
        this.view.updateStatusRequest(status);
        goToHomepage();

    }


    //simile a acceptRequest
    public void declineRequest(RequestBean request) throws PersistanceLayerNotAvailable {
        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI();
        moderatorAPI.setModeratorControllerGrafico(this);
        try{
        moderatorAPI.updateRequestState( request.getUsername(), "declined");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere rifiutata per motivi tecnici. Riprovare più tardi.");
        }
    }
}
