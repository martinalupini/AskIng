package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOJDBC {

    public List<Response> retrieveResponseFromQuestionID(int id) throws DBNotAvailable {

        List<Response> list = new ArrayList<>();

        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from responses  where question = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            //if rs is empty there is no question in the section
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
            throw new DBNotAvailable("DB is currently not available");
        }

        return list;
    }


}
