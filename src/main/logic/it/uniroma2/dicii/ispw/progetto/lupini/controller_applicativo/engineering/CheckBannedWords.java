package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.BannedWordsDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.UserProfileDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;

import java.util.List;

public class CheckBannedWords {

    private CheckBannedWords(){}
    public static void checkText(String text) throws PersistanceLayerNotAvailable, BannedWordFoundException {

        List<String> bannedWords = BannedWordsDAOCSV.retrieveBannedWords();
        for(String word: bannedWords){

            if(text.contains(word)){

                //per prima cosa cambio lo stato della classe CurrentUserProfile

                CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
                CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
                if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                    ((RegularUser)currentUserProfile.getCurrentUser().getRole()).increaseBadBehaviour();
                    currentUserProfileBean.getUser().increaseBadBehaviour();
                }

                try{

                    //dopodichè vado a cambiare lo stato dell'utente in persistenza
                    String username = currentUserProfile.getUsername();

                    if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                        UserProfileDAOJDBC userProfileDAOJDBC = new UserProfileDAOJDBC();
                        userProfileDAOJDBC.increaseBadBehaviourUser(username);

                        UserProfileDAOCSV userProfileDAOCSV = new UserProfileDAOCSV();
                        userProfileDAOCSV.increaseBadBehaviourUser(username);
                    }

                } catch (ImpossibleToUpdate e) {
                    throw new PersistanceLayerNotAvailable("error in persistance layer");
                }

                //notifico il controller grafico
                throw new BannedWordFoundException("banned word found in text");


            }
        }

    }
}
