package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOJDBC {

    public List<Response> retrieveResponseFromQuestionID(int id) throws PersistanceLayerNotAvailable {

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


        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

        return list;
    }


    public void saveNewQuestion(Question newQuestion, String section) throws PersistanceLayerNotAvailable {

        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            int id = 0;
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select max(questionID) from questions");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) id = rs.getInt(1);

            PreparedStatement statement = connDB.prepareStatement("insert into questions (questionID, text, author, keyword1, keyword2, keyword3, section ) values (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, id+1);
            statement.setString(2, newQuestion.getQuestionText());
            statement.setString(3, newQuestion.getAuthor().getUsername());
            statement.setString(7, section);

            List<String> keywords = newQuestion.getKeywords();
            statement.setString(4, keywords.get(0));

            if (keywords.size() == 1) {
                statement.setString(5, null);
                statement.setString(6, null);

            } else if (keywords.size() == 2) {
                statement.setString(5, keywords.get(1));
                statement.setString(6, null);
            } else if (keywords.size() == 3) {
                statement.setString(5, keywords.get(1));
                statement.setString(6, keywords.get(2));
            }

            statement.executeUpdate();


        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new PersistanceLayerNotAvailable("error in opening the connection");
        }
    }


}
