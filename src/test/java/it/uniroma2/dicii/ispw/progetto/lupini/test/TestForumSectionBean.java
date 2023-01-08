package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ForumSectionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import org.junit.Test;

import java.util.List;

public class TestForumSectionBean {

    @Test
    public void testGetQuestions(){
        ForumSectionBean forumSectionBean = new ForumSectionBean("Analisi 1");
        List<QuestionBean>  list;
        try {
            list = forumSectionBean.getQuestions();
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

        for( QuestionBean qb : list){
            System.out.println(qb.getText()+" "+ qb.getUsername()+" "+ qb.getKeywords());
        }
    }
}
