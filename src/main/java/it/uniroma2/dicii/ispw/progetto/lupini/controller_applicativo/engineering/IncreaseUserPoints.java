package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
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

        currentUserProfileBean.getUser().increasePoints();
        ((RegularUser)currentUserProfile.getCurrentUser().getRole()).increasePoints();

        try {
            UserProfileDAOJDBC userProfileDAOJDBC = new UserProfileDAOJDBC();
            userProfileDAOJDBC.increaseUserPoints(username);

            UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
            userProfileDAOJDBC.increaseUserPoints(username);
        } catch (ImpossibleToUpdate e) {
            throw new PersistanceLayerNotAvailable("error in persistance layer");
        }


    }
}
