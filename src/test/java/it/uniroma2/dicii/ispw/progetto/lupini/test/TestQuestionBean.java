package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Moderator;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Role;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.view.SectionController;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuestionBean {

    @Test
    public void testConvert(){
        List<String> list = new ArrayList<>();
        list.add("kw");
        list.add("kw2");
        Role role = new Moderator();
        UserProfile user = new UserProfile("martina", "bellissima", role );
        Question q = new Question("ciao", list, user);

        QuestionBean qb = SectionController.convertQuestion(q);

        System.out.println(qb.getText()+" "+ qb.getUsername()+" "+ qb.getKeywords());
    }
}
