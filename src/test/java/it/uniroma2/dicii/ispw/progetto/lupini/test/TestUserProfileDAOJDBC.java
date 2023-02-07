package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class TestUserProfileDAOJDBC {

    @Test
    public void TestLoginEmail(){
        String username = "giada";
        String password = "password";


        UserProfileDAOJDBC userDAO = new UserProfileDAOJDBC();

        try {
            UserProfile user = userDAO.retrieveUserFromUsernameAndPassword(username, password);
            System.out.println(user.getUsername()+"   "+ user.getEmail()+ "   "+user.getRoleName());

            assertThat("non vuoto", user!=null);


        } catch (ItemNotFound | PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testIncrease(){
        UserProfileDAOJDBC userDAO = new UserProfileDAOJDBC();
        try {
            userDAO.increaseBadBehaviourUser("giada");
            assertThat("non vuoto", userDAO!=null);
        } catch (ImpossibleToUpdate e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRetrieveUserFromUsernameAndPassword(){
        UserProfileDAOJDBC userProfileDAOCSV = new UserProfileDAOJDBC();
        try {
            UserProfile user = userProfileDAOCSV.retrieveUserFromUsernameAndPassword("martinalupini", "1234");
            CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
            //currentUserProfileBean.setUser(user);
            System.out.println(user.toString());
            System.out.println(currentUserProfileBean.toString());

            assertThat("non vuoto", user!=null);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
