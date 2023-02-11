package it.uniroma2.dicii.ispw.progetto.lupini.test;


import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserProfileDAOCSV {


    /*
    In questo test verifico se viene effettivamente incrementato il comportamento scorretto dell'utente.
    Per fare ciò prima recupero l'utente dal database e controllo il suo punteggio.
    Poi aumento il punteggio e infine ricarico l'utente da database per vedere se l'aumento è stato effettivamente salvato.
     */
    @Test
    public void testIncreaseBadBehaviour(){
        int previousBadBehaviour= 0;
        int nextBadBehaviour= 0;
        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
        try {
            UserProfile user = userProfileDAOCSV.retrieveUserFromUsername("martinalupini");
            previousBadBehaviour = ((RegularUser)user.getRole()).getBadBehaviour();
            userProfileDAOCSV.increaseBadBehaviourUser("martinalupini");
            user = userProfileDAOCSV.retrieveUserFromUsername("martinalupini");
            nextBadBehaviour = ((RegularUser)user.getRole()).getBadBehaviour();
        } catch (ImpossibleToUpdate | ItemNotFound | PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        }

        assertEquals(nextBadBehaviour, previousBadBehaviour+1);
    }


}
