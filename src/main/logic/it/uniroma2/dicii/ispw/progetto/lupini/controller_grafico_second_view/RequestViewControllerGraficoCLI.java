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

    public void acceptRequest(RequestBean request) throws PersistanceLayerNotAvailable {

        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI(this, null);
        try {
            moderatorAPI.updateRequestState( request.getUsername(), "accepted");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere accetta per motivi tecnici. Riprovare più tardi.");
        }

    }


    @Override
    public void updateStatus(String status) {
        this.view.updateStatusRequest(status);
        goToHomepage();

    }

    public void declineRequest(RequestBean request) throws PersistanceLayerNotAvailable {
        RequestModeratorAPI moderatorAPI = new RequestModeratorAPI(this, null);
        try{
        moderatorAPI.updateRequestState( request.getUsername(), "declined");
        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere rifiutata per motivi tecnici. Riprovare più tardi.");
        }
    }
}
