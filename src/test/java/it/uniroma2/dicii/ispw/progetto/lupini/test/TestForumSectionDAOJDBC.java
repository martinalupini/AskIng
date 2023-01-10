package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestForumSectionDAOJDBC {

    @Test
    public void testRetrieveQuestionOfSection(){
        ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();

        List<Question> question;
        try {
            question = forumSectionDAOJDBC.retrieveQuestionOfSection("analisi 1");
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

        for (Question quest : question) {

            System.out.println( "\n"+quest.getAuthor()+ " "+quest.getKeywords()+" "+quest.getQuestionText());

        }

        assertThat("non vuoto", question!=null);

    }
}
