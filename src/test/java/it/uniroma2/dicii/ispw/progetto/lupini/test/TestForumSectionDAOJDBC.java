package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import org.junit.Test;

import java.util.List;

public class TestForumSectionDAOJDBC {

    @Test
    public void testRetrieveQuestionOfSection(){
        ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();

        List<Question> question = forumSectionDAOJDBC.retrieveQuestionOfSection("analisi 1");

        for (Question quest : question) {

            System.out.println( "\n"+quest.getAuthor()+ " "+quest.getKeywords()+" "+quest.getQuestionText());

        }


    }
}
