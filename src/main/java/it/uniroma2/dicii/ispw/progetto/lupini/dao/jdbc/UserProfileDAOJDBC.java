package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.UserProfileDAO;
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

    @Override
    public UserProfile retrieveUserFromUsernameAndPassword(String username, String password) throws ItemNotFound, ClassNotFoundException {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();
        UserProfile userProfile;
        Role role;
        try {
            //opening of connection and query
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from users where username = ? and password = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(!rs.first()){
                throw new ItemNotFound("Item not found in database");
            }else{
                rs.first();
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
                userProfile = new UserProfile(username, email, role);
            }

            return userProfile;

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UserProfile retrieveUserFromUsername(String username) throws ItemNotFound, ClassNotFoundException {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();
        UserProfile userProfile;
        Role role;
        try {
            //opening of connection and query
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from users where username = ?");
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();


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
                userProfile = new UserProfile(username, email, role);
            }else{
                throw new ItemNotFound("Item not found in database");
            }

            return userProfile;

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }

        return null;
    }
}
