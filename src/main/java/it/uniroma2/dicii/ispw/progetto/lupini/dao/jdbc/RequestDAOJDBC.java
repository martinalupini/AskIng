package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOJDBC {

    public List<Request> retrieveRequests() throws PersistanceLayerNotAvailable {
        List<Request> list = new ArrayList<>();

        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from requests", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery();

            //if rs is empty there is no question in the section
            if (!rs.first()) {
                return list;
            }

            rs.first();
            do {
                String text = rs.getString("text");

                UserProfile user  = RetrieveUserWithExceptions.retrieveUserWithExceptionsManagement(rs);

                Request req = new Request(text, user);
                list.add(req);

            } while (rs.next());


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

        return list;
    }


    public void registerNewRequest(String text, String username) throws PersistanceLayerNotAvailable, RequestAlreadyDone {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();


        try {

                Connection connDB = getConn.getConnection();

                PreparedStatement stmt = connDB.prepareStatement("insert into requests(text, author) values  (?, ?)");
                stmt.setString(1, text);
                stmt.setString(2, username);
                stmt.executeUpdate();


            } catch (SQLException e) {
                if (e.getMessage().startsWith("Duplicate")) {
                    throw new RequestAlreadyDone("Request from username is already present");
                } else {
                    throw new PersistanceLayerNotAvailable("Error in registration of request");
                }
            } catch (ClassNotFoundException e) {
                throw new PersistanceLayerNotAvailable("Error in registration of request");
            }
    }

    public void deleteRequestFromUsername(String username) throws PersistanceLayerNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();


        try {

            Connection connDB = getConn.getConnection();

            PreparedStatement stmt = connDB.prepareStatement("delete from requests where author = ?");
            stmt.setString(1, username);
            stmt.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("Error in elimination of request");
        }


    }
}
