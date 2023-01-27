package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.BecomeModeratorFormView;

public class BecomeModeratorControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements NewRequestControllerGraficoInterface {

    private BecomeModeratorFormView view;
    public BecomeModeratorControllerGraficoCLI(BecomeModeratorFormView view){
        this.view = view;
    }

    public void sendRequest(String text) throws PersistanceLayerNotAvailable, RequestAlreadyDone {

        UserProfileBean user = CurrentUserProfileBean.getProfileInstance().getUser();
        RequestBean requestBean = new RequestBean(text, user.getUsername(), user.getEmail(), user.getPoints(), user.getBadBehaviour());
        RequestControllerAppl requestControllerAppl = new RequestControllerAppl(this, null);
        requestControllerAppl.processRequest(requestBean);

    }



    @Override
    public void updateStatus() {
        this.view.requestSuccessful();
    }

    @Override
    public void notifyUser() {

    }
}
