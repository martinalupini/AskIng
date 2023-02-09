package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponseDAOJDBC {

    public List<Response> retrieveResponseFromQuestionID(int id) throws PersistanceLayerNotAvailable {

        List<Response> list = new ArrayList<>();

        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from responses  where question = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.first()) {
                return list;
            }

            rs.first();
            do {
                String text = rs.getString("text");
                UserProfile user  = RetrieveUserWithExceptions.retrieveUserWithExceptionsManagement(rs);

                Response res = new Response(text, user);
                list.add(res);

            } while (rs.next());


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

        return list;
    }

    public void saveNewResponse(Response res, int id) throws PersistanceLayerNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            int idRes = 0;
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select max(idresponse) from responses");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) idRes = rs.getInt(1);

            PreparedStatement statement = connDB.prepareStatement("insert into responses (idresponse, question, author, text ) values (?, ?, ?, ?)");
            statement.setInt(1, idRes+1);
            statement.setInt(2, id);
            statement.setString(3, res.getAuthor().getUsername());
            statement.setString(4, res.getResponseText());
            statement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("error in opening the connection");
        }
    }
}
