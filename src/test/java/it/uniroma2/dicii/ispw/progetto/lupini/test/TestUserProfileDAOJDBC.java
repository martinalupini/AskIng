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
        String username = "giada";
        String password = "password";


        UserProfileDAOJDBC userDAO = new UserProfileDAOJDBC();

        try {
            UserProfile user = userDAO.retrieveUserFromUsernameAndPassword(username, password);
            System.out.println(user.getUsername()+"   "+ user.getEmail()+ "   "+user.getRoleName());


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }

    }
}
