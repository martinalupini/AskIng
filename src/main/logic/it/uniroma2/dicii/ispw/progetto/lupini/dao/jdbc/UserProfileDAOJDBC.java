package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleToUpdate;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Moderator;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Role;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileDAOJDBC implements UserProfileDAO {


    private static final String ERR_REG = "Error in registration of changes";
    @Override
    public UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws ItemNotFound, PersistanceLayerNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();
        try {

            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from users where username = ? and password = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return  UserProfileDAOJDBC.fetchInformationOfUserProfile(rs, username);

        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

    }

    @Override
    public UserProfile retrieveUserFromUsername(String username) throws ItemNotFound, PersistanceLayerNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from users where username = ?");
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();


            return UserProfileDAOJDBC.fetchInformationOfUserProfile(rs, username);


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

    }

    //metodo privato creato per evitare duplicazioni
    private static UserProfile fetchInformationOfUserProfile(ResultSet rs,String username) throws ItemNotFound, SQLException {
        Role role;
        if(rs.next()) {
            //if the result set is not empty it is formed by one element
            String email = rs.getString("email");
            String roleName = rs.getString("role");

            //I check the role first in order to instantiate the correct role
            if (roleName.equals("moderator")) {
                role = new Moderator();

            } else {
                int points = rs.getInt("points");
                int badBehaviour = rs.getInt("bad_behaviour");
                role = new RegularUser(points, badBehaviour);
            }
            return new UserProfile(username, email, role);
        }else{
            throw new ItemNotFound("Item not found in database");
        }
    }

    public void changeRoleOfUser(String username) throws ImpossibleToUpdate {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {

            Connection connDB = getConn.getConnection();

            PreparedStatement stmt = connDB.prepareStatement("update users set role='moderator', points = '0', bad_behaviour = '0' where username = ?");
            stmt.setString(1, username);
            stmt.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new ImpossibleToUpdate(ERR_REG);
        }
    }

    @Override
    public void increaseBadBehaviourUser(String username) throws ImpossibleToUpdate {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();


        try {

            int bb = 0;
            Connection connDB = getConn.getConnection();

            PreparedStatement statement = connDB.prepareStatement("select bad_behaviour from users where username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                bb = rs.getInt("bad_behaviour")+1;
            }

            PreparedStatement stmt = connDB.prepareStatement("update users set  bad_behaviour = ? where username = ?");
            stmt.setInt(1, bb);
            stmt.setString(2, username);
            stmt.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new ImpossibleToUpdate(ERR_REG);
        }
    }

    @Override
    public void increaseUserPoints(String username) throws ImpossibleToUpdate {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();


        try {

            int points = 0;
            Connection connDB = getConn.getConnection();

            PreparedStatement statement = connDB.prepareStatement("select points from users where username = ?");
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                points = rs.getInt("points")+5;
            }

            PreparedStatement stmt = connDB.prepareStatement("update users set  points = ? where username = ?");
            stmt.setInt(1, points);
            stmt.setString(2, username);
            stmt.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new ImpossibleToUpdate(ERR_REG);
        }

    }
}
