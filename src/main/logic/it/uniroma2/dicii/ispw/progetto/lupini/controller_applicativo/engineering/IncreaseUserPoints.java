package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.DAOFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;

public class IncreaseUserPoints {

    private IncreaseUserPoints(){}

    public static void increaseUserPoints() throws PersistanceLayerNotAvailable {
        CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        String username = currentUserProfile.getUsername();

        //incremento il punteggio dell'utente nelle rispettive classi
        currentUserProfileBean.getUser().increasePoints();
        ((RegularUser)currentUserProfile.getCurrentUser().getRole()).increasePoints();

        //incremento il punteggio dell'utente su entrambi gli strati di persistenza
        try {
            UserProfileDAO userProfileDAOJDBC = DAOFactory.getInstance().createUserDAOJDBC();
            userProfileDAOJDBC.increaseUserPoints(username);

            UserProfileDAO userProfileDAOCSV = DAOFactory.getInstance().createUserDAOCSV();
            userProfileDAOCSV.increaseUserPoints(username);
        } catch (ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("error in persistance layer");
        }


    }
}
