package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestUserAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.BecomeModeratorFormView;

public class BecomeModeratorControllerGraficoCLI extends EmptyScreenControllerGraficoCLI implements NewRequestControllerGraficoInterface {

    private BecomeModeratorFormView view;

    public void setView(BecomeModeratorFormView view) {
        this.view = view;
    }

    //per mandare la richiesta
    public void sendRequest(String text) throws PersistanceLayerNotAvailable, RequestAlreadyDone, TextException {

        try{
            UserProfileBean user = CurrentUserProfileBean.getProfileInstance().getUser();
            RequestBean requestBean = new RequestBean(user.getUsername(), user.getEmail(), user.getPoints(), user.getBadBehaviour());
            requestBean.setText(text);

            RequestUserAPI requestUserAPI = new RequestUserAPI();
            requestUserAPI.setUserControllerGrafico(this);
            requestUserAPI.sendRequest(requestBean);
        }catch(PersistanceLayerNotAvailable e){
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere registrata per motivi tecnici. Riprovare più tardi.");
        }catch(RequestAlreadyDone e){
            throw new RequestAlreadyDone("Impossibile fare una nuova richiesta perchè una richiesta fatta da te è ancora in sospeso.");
        }

    }

    //aggiorna l'utente se la richiesta è stata inviata
    @Override
    public void requestSaved() {
        this.view.requestSuccessful();
        goToHomepage();
    }
}
