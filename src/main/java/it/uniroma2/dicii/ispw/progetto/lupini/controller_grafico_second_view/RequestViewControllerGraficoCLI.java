package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.RequestView;


import java.text.SimpleDateFormat;

public class RequestViewControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements ManageRequestsContrGrafInterface {

    private RequestView view;

    public RequestViewControllerGraficoCLI(RequestView view){
        this.view = view;
    }

    public void acceptRequest(RequestBean request) throws PersistanceLayerNotAvailable {

        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(null, this);
        requestControllerAppl.updateRequestState( request.getUsername(), "accepted");

    }


    @Override
    public void updateStatus(String s) {
        this.view.updateStatusRequest(s);

    }

    @Override
    public void notifyModeratorNewRequest() {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Notification notification = new Notification();
        String destination= "moderators_"+timeStamp;
        notification.notify(destination, "E' arrivata una nuova richiesta per diventare moderatore. Controllare la propria sezione");


    }

    public void declineRequest(RequestBean request) throws PersistanceLayerNotAvailable {
        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(null, this);
        requestControllerAppl.updateRequestState( request.getUsername(), "declined");
    }
}
