package it.uniroma2.dicii.ispw.progetto.lupini.dao;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

public interface UserProfileDAO {

    UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws ItemNotFound, DBNotAvailable;

    UserProfile retrieveUserFromUsername(String username) throws ItemNotFound, DBNotAvailable;

    void changeRoleOfUser(String username) throws ImpossibleToUpdate;
}