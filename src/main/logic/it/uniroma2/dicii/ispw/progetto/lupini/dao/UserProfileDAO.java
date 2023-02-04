package it.uniroma2.dicii.ispw.progetto.lupini.dao;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

public interface UserProfileDAO {

    UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws ItemNotFound, PersistanceLayerNotAvailable;

    UserProfile retrieveUserFromUsername(String username) throws ItemNotFound, PersistanceLayerNotAvailable;

    void changeRoleOfUser(String username) throws ImpossibleToUpdate;

    void increaseBadBehaviourUser(String username) throws ImpossibleToUpdate;

    void increaseUserPoints(String username) throws ImpossibleToUpdate;
}