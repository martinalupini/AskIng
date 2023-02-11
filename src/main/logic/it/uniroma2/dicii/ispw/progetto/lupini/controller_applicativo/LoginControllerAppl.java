package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAOFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;


public class LoginControllerAppl {

    public void login(String username, String password) throws PersistanceLayerNotAvailable, ItemNotFound {

        //chiediamo alla factory del DAO di creare un'istanza di DAO randomicamente
        UserProfileDAO userDAO = UserProfileDAOFactory.getInstance().createUserDAO();

        UserProfile user;
        UserProfileBean userBean;
        CurrentUserProfileBean currentUserProfileBean;

        //recuperiamo l'utente da username e password
        user = userDAO.retrieveUserFromUsernameAndPassword(username, password);

        //settiamo utente corrente (bean)
        CurrentUserProfile currUser = CurrentUserProfile.getCurrentUserInstance();
        currUser.setCurrentUser(user);

        //settiamo utente corrente (model)
        currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        if (currUser.getRoleName().equals("regular user")) {
            userBean = new UserProfileBean(currUser.getUsername(), currUser.getEmail(), "regular user", ((RegularUser)currUser.getRole()).getPoints(), ((RegularUser)currUser.getRole()).getBadBehaviour());
            currentUserProfileBean.setUser(userBean);
        } else if (currUser.getRoleName().equals("moderator")) {
            userBean = new UserProfileBean(currUser.getUsername(), currUser.getEmail(), "moderator");
            currentUserProfileBean.setUser(userBean);

        }



    }
}
