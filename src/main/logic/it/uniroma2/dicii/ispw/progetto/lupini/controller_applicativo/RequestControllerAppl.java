package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestModeratorAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestUserAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;

public class RequestControllerAppl {

    private RequestUserAPI regularUserBoundary;

    private RequestModeratorAPI moderatorBoundary;



    public RequestControllerAppl(RequestUserAPI regularUserBoundary, RequestModeratorAPI moderatorBoundary){
        this.regularUserBoundary = regularUserBoundary;
        this.moderatorBoundary = moderatorBoundary;
    }

    public void processRequest(RequestBean requestBean) throws PersistanceLayerNotAvailable, RequestAlreadyDone {
        //The aim of this method is to register the new request and notify the view controller of moderators


            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();

            //salvataggio della richiesta sulla memoria di persistenza
            requestDAOJDBC.registerNewRequest(requestBean.getText(), requestBean.getUsername());

            //aggiorno l'utente che l'invio della richiesta è andata a buon fine
            regularUserBoundary.updateStatus();

            //mando notifica al moderatore
            new RequestModeratorAPI(null,this).notifyModeratorNewRequest();

    }


    public void updateRequestState(String username,  String state) throws PersistanceLayerNotAvailable, ImpossibleToUpdate {

            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requestDAOJDBC.deleteRequestFromUsername(username);

            if (state.equals("accepted")){
            //cambio il ruolo dell'utente sia su filesystem che su DBMS
            UserProfileDAOJDBC userProfileDAOJDBC = new UserProfileDAOJDBC();
            userProfileDAOJDBC.changeRoleOfUser(username);

            UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
            userProfileDAOCSV.changeRoleOfUser(username);

            //informo il moderatore che l'operazione è andata a buon fine

                this.moderatorBoundary.updateStatus("ACCETTATA");
            }else{
                this.moderatorBoundary.updateStatus("RIFIUTATA");
            }

            //notifico l'utente che il suo ruolo è stato cambiato
            new RequestUserAPI(null, this).notifyUser(username, state);


    }



}
