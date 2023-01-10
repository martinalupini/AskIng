package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ForumSectionDAOJDBC {

    public List<Question>  retrieveQuestionOfSection( String sectionName) throws DBNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();
        List<Question>  list = new ArrayList<Question>();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from questions where section = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, sectionName);
            ResultSet rs = stmt.executeQuery();

            //if rs is empty there is no question in the section
            if (!rs.first()) {
                return list;
            }

            //if it's not empty we retrieve the questions
            rs.first();
            do {
                List<String> keywords = new ArrayList<>();
                String kw;
                int i = 1;

                //we find the text of the question
                String text = rs.getString("text");

                int id = rs.getInt("questionID");

                //we find the keywords
                while (i <= 3 && (kw = rs.getString("keyword" + i)) != null) {
                    keywords.add(kw);
                    i++;
                }

                //we find the author. To do this we use the DAO of user profile

                UserProfile author = RetrieveUserWithExceptions.retrieveUserWithExceptionsManagement(rs);

                //finally we add the new question
                list.add(new Question(text, keywords, author, id));

            } while (rs.next());


        } catch (SQLException | ClassNotFoundException e) {
            throw new DBNotAvailable("DB is currently not available");
        }

        return list;
    }
}
