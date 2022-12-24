package it.uniroma2.dicii.ispw.progetto.lupini.dao;

import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

public interface UserProfileDAO {

    UserProfile retrieveUserFromUsernameAndPassword(String username, String password)  throws Exception;
}
