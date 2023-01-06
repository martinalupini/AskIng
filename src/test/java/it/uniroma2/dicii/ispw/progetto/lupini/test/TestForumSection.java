package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import org.junit.Test;

import java.util.List;

public class TestForumSection {

    @Test
    public void testGetQuestions(){
        ForumSection forumSection = new ForumSection("Analisi 1");
        List<Question> questionsFromModel = forumSection.getQuestions();

        for (Question quest : questionsFromModel) {

            System.out.println( "\n"+quest.getAuthor()+ " "+quest.getKeywords()+" "+quest.getQuestionText());

        }
    }
}
