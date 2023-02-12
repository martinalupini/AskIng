package it.uniroma2.dicii.ispw.progetto.lupini.test;


import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestUserProfileDAOJDBC {

    @Test
    public void TestRetrieveUserFromUsernameAndPassword(){
        String usernameToFind = "martinalupini";
        UserProfileDAOJDBC userProfileDAOJDBC = new UserProfileDAOJDBC();
        try {
            UserProfile user = userProfileDAOJDBC.retrieveUserFromUsernameAndPassword(usernameToFind, "1234");

            assertEquals(user.getUsername(), usernameToFind);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
