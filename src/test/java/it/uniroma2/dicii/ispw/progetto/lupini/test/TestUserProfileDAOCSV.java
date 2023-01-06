package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;

public class TestUserProfileDAOCSV {

    @Test
    public void TestRetrieveUserFromUsernameAndPassword(){
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
        try {
            UserProfile user = userProfileDAOCSV.retrieveUserFromUsernameAndPassword("ludoBianchi", "ludo1");
            CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
            //currentUserProfileBean.setUser(user);
            System.out.println(user.toString());
            System.out.println(currentUserProfileBean.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
