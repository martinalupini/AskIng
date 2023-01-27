package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.ManageRequestsContrGrafInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewRequestControllerGraficoInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.DoNewRequestController;

import java.util.ArrayList;
import java.util.List;

public class RequestControllerAppl {

    private ManageRequestsContrGrafInterface moderatorControllerGrafico;
    private NewRequestControllerGraficoInterface userControllerGrafico;

    public RequestControllerAppl(NewRequestControllerGraficoInterface doNewRequestController, ManageRequestsContrGrafInterface viewRequestController){
        this.userControllerGrafico = doNewRequestController;
        this.moderatorControllerGrafico = viewRequestController;
    }

    public List<RequestBean> getRequests() throws PersistanceLayerNotAvailable {

        List<Request> requests;
        List<RequestBean> req = new ArrayList<>();
        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();

        try {
            requests =  requestDAOJDBC.retrieveRequests();
            for (Request r : requests) {

                req.add(convertRequest(r));

            }

            return req;
        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle risposte. Riprovare più tardi");
        }

    }

    public void processRequest(RequestBean requestBean) throws PersistanceLayerNotAvailable, RequestAlreadyDone {
        //The aim of this method is to register the new request and notify the view controller of moderators
        try {

            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requestDAOJDBC.registerNewRequest(requestBean.getText(), requestBean.getUsername());
            userControllerGrafico.updateStatus();
            


        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere registrata per motivi tecnici. Riprovare più tardi.");
        } catch (RequestAlreadyDone e) {
            throw new RequestAlreadyDone("Impossibile fare una nuova richiesta perchè una richiesta fatta da te è ancora in sospeso.");
        }
    }


    public void updateRequestState(String username,  String state) throws PersistanceLayerNotAvailable {
        try {

            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requestDAOJDBC.deleteRequestFromUsername(username);

            if (state.equals("accepted")){
            //now I need to change the user role in both the filesystem and the DBMS
            UserProfileDAOJDBC userProfileDAOJDBC = new UserProfileDAOJDBC();
            userProfileDAOJDBC.changeRoleOfUser(username);

            UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
            userProfileDAOCSV.changeRoleOfUser(username);

            //only now I can confirm the right execution of the operation

                this.moderatorControllerGrafico.updateStatus("ACCETTATA");
            }else{
                this.moderatorControllerGrafico.updateStatus("RIFIUTATA");
            }


        } catch (PersistanceLayerNotAvailable | ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("Spiacenti la richiesta non può essere accetta o rifiuata per motivi tecnici. Riprovare più tardi.");
        }
    }


    private RequestBean  convertRequest(Request req ){

        String username = req.getAuthor().getUsername();
        String email = req.getAuthor().getEmail();
        int points = ((RegularUser)req.getAuthor().getRole()).getPoints();
        int badBehaviour = ((RegularUser)req.getAuthor().getRole()).getBadBehaviour();

        return new RequestBean(req.getText(), username,email,points, badBehaviour);
    }
}
