package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseDAOJDBC {

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
            e.printStackTrace();
            throw new PersistanceLayerNotAvailable("error in opening the connection");
        }
    }
}
