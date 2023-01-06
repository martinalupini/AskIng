package it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.UserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.view.LoginController;

public class LoginControllerAppl {

    LoginController viewCtl;
    UserProfileDAO userDAO;

    public LoginControllerAppl(LoginController ctl) {
        this.viewCtl = ctl;
    }

    public void login(String username, String password) throws DBNotAvailable, ItemNotFound {

        if(Math.random() > 0.5) {
            userDAO = new UserProfileDAOJDBC();
        }else {
            userDAO = new UserProfileDAOCSV();
        }
        UserProfile user;
        UserProfileBean userBean;
        CurrentUserProfileBean currentUserProfileBean;


        try {
            user = userDAO.retrieveUserFromUsernameAndPassword(username, password);

            //model part
            CurrentUserProfile currUser = CurrentUserProfile.getCurrentUserInstance();
            currUser.setCurrentUser(user);

            //bean part
            currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
            if (currUser.getRoleName().equals("regular user")) {
                userBean = new UserProfileBean(currUser.getUsername(), currUser.getEmail(), "regular user", currUser.getRole().getRoleInformation().get(0), currUser.getRole().getRoleInformation().get(1));
                currentUserProfileBean.setUser(userBean);
            } else if (currUser.getRoleName().equals("moderator")) {
                userBean = new UserProfileBean(currUser.getUsername(), currUser.getEmail(), "moderator");
                currentUserProfileBean.setUser(userBean);

            }


        } catch (ItemNotFound e) {
            throw new ItemNotFound("Username o password errati. Riprovare. ");
        } catch (Exception e) {
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi tecnici. Riprovare più tardi");
        }


    }
}
