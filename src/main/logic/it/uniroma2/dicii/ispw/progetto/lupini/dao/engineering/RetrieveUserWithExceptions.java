package it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveUserWithExceptions {

    private RetrieveUserWithExceptions(){}

    //metodo statico usato in molti metodi dei DAO
    public static UserProfile retrieveUserWithExceptionsManagement(ResultSet rs) throws SQLException, PersistanceLayerNotAvailable {

        String username = rs.getString("author");

        UserProfileDAO userProfileDAO = new UserProfileDAOJDBC();
        try{
            return userProfileDAO.retrieveUserFromUsername(username);

        }catch(ItemNotFound e){
            //se non si riesce a recuperare l'autore va bene anche non inserire informazioni
          return new UserProfile("unknown", "unknown", null);

        }
    }
}
