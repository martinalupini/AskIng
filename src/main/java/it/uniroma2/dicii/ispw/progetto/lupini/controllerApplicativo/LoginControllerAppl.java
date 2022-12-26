package it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.view.LoginController;

public class LoginControllerAppl {

    LoginController viewCtl;
    ProfileBean profile;
    UserProfileDAO userDAO;

    public LoginControllerAppl(LoginController ctl){
        this.viewCtl = ctl;
    }

    public void login(String username, String password) throws DBNotAvailable, ItemNotFound {
       userDAO = new UserProfileDAOJDBC(this);
       UserProfile user;


       try{
           user = userDAO.retrieveUserFromUsernameAndPassword(username, password);
           CurrentUserProfile currUser =   CurrentUserProfile.getCurrentUserInstance();
           currUser.setCurrentUser(user);

       }catch (ItemNotFound e){
           throw new ItemNotFound("Username o password errati. Riprovare. ");
       }
       catch(ClassNotFoundException e){
           throw new DBNotAvailable("Spacenti, si sono verificati dei problemi tecnici. Riprovare più tardi");
       }
       catch(Exception e){
           e.printStackTrace();
       }

        /*ActionEvent event = new ActionEvent();
       viewCtl.goToHomepage(event);*/
    }
    
    

}