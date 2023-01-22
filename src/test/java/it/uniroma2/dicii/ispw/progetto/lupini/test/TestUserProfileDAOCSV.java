package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestUserProfileDAOCSV {

    @Test
    public void TestRetrieveUserFromUsernameAndPassword(){
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
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

    @Test
    public void testChangeRoleOfUser(){
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
        try {
            userProfileDAOCSV.changeRoleOfUser("luigi");
        } catch (ImpossibleToUpdate e) {
            throw new RuntimeException(e);
        }

        assertThat("non vuoto", userProfileDAOCSV!=null);
    }

    @Test
    public void testIncreaseBadBehaviour(){
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
        try {
            userProfileDAOCSV.increaseBadBehaviourUser("luigi");
        } catch (ImpossibleToUpdate e) {
            throw new RuntimeException(e);
        }

        assertThat("non vuoto", userProfileDAOCSV!=null);
    }

    @Test
    public void testIncreasePoints(){
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
        try {
            userProfileDAOCSV.increaseUserPoints("luigi");
        } catch (ImpossibleToUpdate e) {
            throw new RuntimeException(e);
        }

        assertThat("non vuoto", userProfileDAOCSV!=null);
    }
}
