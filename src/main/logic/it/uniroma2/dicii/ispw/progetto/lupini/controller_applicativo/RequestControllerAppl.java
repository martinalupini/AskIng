package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestModeratorAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.api_boundary.RequestUserAPI;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionsAndResponsesFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.DAOFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;

public class RequestControllerAppl {

    private RequestUserAPI regularUserBoundary;

    private RequestModeratorAPI moderatorBoundary;


    public void setModeratorBoundary(RequestModeratorAPI moderatorBoundary) {
        this.moderatorBoundary = moderatorBoundary;
    }

    public void setRegularUserBoundary(RequestUserAPI regularUserBoundary) {
        this.regularUserBoundary = regularUserBoundary;
    }

    public void processRequest(RequestBean requestBean) throws PersistanceLayerNotAvailable, RequestAlreadyDone {

           Request request = new Request(requestBean.getText(), CurrentUserProfile.getCurrentUserInstance().getCurrentUser());

            //salvataggio della richiesta sulla memoria di persistenza
            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requestDAOJDBC.registerNewRequest(request);

            //aggiorno l'utente che l'invio della richiesta è andata a buon fine
            regularUserBoundary.updateStatus();

            //mando notifica al moderatore
            this.moderatorBoundary = new RequestModeratorAPI();
            this.moderatorBoundary.setRequestControllerAppl(this);
            this.moderatorBoundary.notifyModeratorNewRequest();

    }


    public void updateRequestState(String username,  String state) throws PersistanceLayerNotAvailable, ImpossibleToUpdate {

            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requestDAOJDBC.deleteRequestFromUsername(username);

            if (state.equals("accepted")){
            //cambio il ruolo dell'utente sia su filesystem che su DBMS
            UserProfileDAO userProfileDAOJDBC = DAOFactory.getInstance().createUserDAOJDBC();
            userProfileDAOJDBC.changeRoleOfUser(username);

            UserProfileDAO userProfileDAOCSV = DAOFactory.getInstance().createUserDAOCSV();
            userProfileDAOCSV.changeRoleOfUser(username);

            //informo il moderatore che l'operazione è andata a buon fine
                this.moderatorBoundary.updateStatus("ACCETTATA");
            }else{
                this.moderatorBoundary.updateStatus("RIFIUTATA");
            }

            //notifico l'utente che il suo ruolo è stato cambiato
            this.regularUserBoundary = new RequestUserAPI();
            this.regularUserBoundary.setRequestControllerAppl(this);
            this.regularUserBoundary.notifyUser(username, state);


            //aggiorno il profilo drll'utente nelle domande e nelle risposte
            QuestionsAndResponsesFactory.getCurrentInstance().changeRoleOfUser(username);


    }



}
