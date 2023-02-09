package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.DBMSConnection;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering.RetrieveUserWithExceptions;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOJDBC {


    public int saveNewQuestion(Question newQuestion, String section) throws PersistanceLayerNotAvailable {

        DBMSConnection getConn = DBMSConnection.getInstanceConnection();

        try {
            int id = 0;
            //per prima cosa recupero l'ultimo id utilizzato
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select max(questionID) from questions");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) id = rs.getInt(1);

            //poi salvo la domanda
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
            return id+1;


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("error in opening the connection");
        }
    }


    public List<Question> retrieveQuestionsOfSection(String sectionName) throws PersistanceLayerNotAvailable {
        DBMSConnection getConn = DBMSConnection.getInstanceConnection();
        List<Question> questions = new ArrayList<>();

        try {
            Connection connDB = getConn.getConnection();
            PreparedStatement stmt = connDB.prepareStatement("select * from questions where section = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, sectionName);
            ResultSet rs = stmt.executeQuery();

            //se è vuoto non c'è nessuna domanda nella sezione
            if (!rs.first()) {
                return  questions;
            }

            //se non è vuoto recuperiamo le domande
            rs.first();
            do {
                List<String> keywords = new ArrayList<>();
                String kw;
                int i = 1;

                //recuperiamo i dati della domanda
                String text = rs.getString("text");
                int id = rs.getInt("questionID");
                while (i <= 3 && (kw = rs.getString("keyword" + i)) != null) {
                    keywords.add(kw);
                    i++;
                }

                //recuperiamo l'autore. E' necessario usare lo specifico DAO
                UserProfile author = RetrieveUserWithExceptions.retrieveUserWithExceptionsManagement(rs);

                //recuperiamo le risposte. E' necessario recuperare lo specifico DAO
                List<Response> responses = new ResponseDAOJDBC().retrieveResponseFromQuestionID(id);

                //aggiungiamo la nuova domanda
                questions.add(new Question(text, keywords, author, id, responses));

            } while (rs.next());


        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistanceLayerNotAvailable("DB is currently not available");
        }

        return questions;
    }


}
