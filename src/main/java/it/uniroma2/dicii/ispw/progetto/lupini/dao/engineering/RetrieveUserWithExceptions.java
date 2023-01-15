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

    public static UserProfile retrieveUserWithExceptionsManagement(ResultSet rs) throws SQLException, PersistanceLayerNotAvailable {


        String username = rs.getString("author");

        UserProfileDAO userProfileDAO = new UserProfileDAOJDBC();
        try{
            return userProfileDAO.retrieveUserFromUsername(username);

        }catch(ItemNotFound e){
          return new UserProfile("unknown", "unknown", null);

        }
    }
}
