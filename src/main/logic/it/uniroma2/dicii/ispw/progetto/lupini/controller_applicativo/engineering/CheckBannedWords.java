package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAOFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.BannedWordsDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;

import java.util.List;

public class CheckBannedWords {

    private CheckBannedWords(){}

    //metodo statico usato da più casi d'uso che serve per verificare che il testo della domanda/risposta
    // non contenga parole che sono state bannate
    public static void checkText(String text) throws PersistanceLayerNotAvailable, BannedWordFoundException {

        //recupero la lista di parole bannate tramite l'apposito DAO
        List<String> bannedWords = BannedWordsDAOCSV.retrieveBannedWords();

        //per ogni parola della lista controllo se è contenuta nel testo
        for(String word: bannedWords){
            if(text.contains(word)){

                //aumento il punteggio badBehaviour dell'utente nelle rispettive classi
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
                        UserProfileDAO userProfileDAOJDBC = UserProfileDAOFactory.getInstance().createUserDAOJDBC();
                        userProfileDAOJDBC.increaseBadBehaviourUser(username);

                        UserProfileDAO userProfileDAOCSV = UserProfileDAOFactory.getInstance().createUserDAOCSV();
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
