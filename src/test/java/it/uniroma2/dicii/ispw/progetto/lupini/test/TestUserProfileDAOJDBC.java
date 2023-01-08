package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Role;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.view.LoginController;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserProfileDAOJDBC {

    @Test
    public void TestLoginEmail(){
        String username = "martinalupini";
        String password = "1234";


        UserProfileDAOJDBC userDAO = new UserProfileDAOJDBC();

        try {
            UserProfile user = userDAO.retrieveUserFromUsernameAndPassword(username, password);
            System.out.println(user.getUsername()+"   "+ user.getEmail()+ "   "+user.getRoleName()+ ((RegularUser)user.getRole()).getPoints()+ ((RegularUser)user.getRole()).getBadBehaviour());



        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

    }
}
