package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ForumSectionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import org.junit.Test;

import java.util.List;

public class TestForumSectionBean {

    @Test
    public void testGetQuestions(){
        ForumSectionBean forumSectionBean = new ForumSectionBean("Analisi 1");
        List<QuestionBean>  list = forumSectionBean.getQuestions();

        for( QuestionBean qb : list){
            System.out.println(qb.getText()+" "+ qb.getUsername()+" "+ qb.getKeywords());
        }
    }
}
