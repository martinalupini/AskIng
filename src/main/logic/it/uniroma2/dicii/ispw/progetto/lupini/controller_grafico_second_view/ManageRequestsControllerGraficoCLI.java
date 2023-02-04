package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.RequestsFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.ManageRequestsView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.RequestView;

import java.util.List;

public class ManageRequestsControllerGraficoCLI extends EmptyScreenControllerGraficoCLI {

    private ManageRequestsView view;
    private List<RequestBean> requests;

    public ManageRequestsControllerGraficoCLI(ManageRequestsView view){
        this.view = view;
    }


    public List<RequestBean> getRequests() throws PersistanceLayerNotAvailable {


        this.requests = RequestsFactory.getCurrentInstance().getRequestsBean();
        return this.requests;

    }

    public void goToRequest(int i) {

        RequestView view = new RequestView(this.requests.get(i-1));
        view.displayRequest();
    }


}
