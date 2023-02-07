package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionOfSectionFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ResponseDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.UserProfileDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import it.uniroma2.dicii.ispw.progetto.lupini.model.UserProfile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAddQuestion {
    @Test
    public void test(){
        try {
            List<QuestionBean> list = QuestionOfSectionFactory.getCurrentInstance().returnQuestionBeanOfSection("Algebra e Logica");
            for(QuestionBean q: list){
                System.out.println(q.getText()+" "+q.getUsername()+" "+q.getKeywords()+" "+q.getId());
            }
            UserProfile user = new UserProfileDAOJDBC().retrieveUserFromUsername("giada");
            QuestionOfSectionFactory.getCurrentInstance().addQuestionToSection("Algebra e Logica", new Question("prova 2", new ArrayList<>(), user, 80, new ArrayList<>()));
            list = QuestionOfSectionFactory.getCurrentInstance().returnQuestionBeanOfSection("Algebra e Logica");
            for(QuestionBean q: list){
                System.out.println(q.getText()+" "+q.getUsername()+" "+q.getKeywords()+" "+q.getId());
            }

        } catch (PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void test2(){
        try {
            List<Question> questions = new QuestionDAOJDBC().retrieveQuestionsOfSection("Algebra e Logica");
            ForumSection f = new ForumSection("Algebra e Logica", questions);
            for(Question q: f.getQuestions()){
                System.out.println(q.getQuestionText()+" "+q.getAuthor().getUsername()+" "+q.getKeywords()+" "+q.getId());
            }
            UserProfile user = new UserProfileDAOJDBC().retrieveUserFromUsername("giada");
            f.addQuestion(new Question("prova 2", new ArrayList<>(), user, 80, new ArrayList<>()));
            for(Question q: f.getQuestions()){
                System.out.println(q.getQuestionText()+" "+q.getAuthor().getUsername()+" "+q.getKeywords()+" "+q.getId());
            }
        } catch (PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3() throws PersistanceLayerNotAvailable, ItemNotFound {

        for(QuestionBean q: QuestionOfSectionFactory.getCurrentInstance().returnQuestionBeanOfSection("Geometria")){
            System.out.println(q.getText()+" "+q.getId());
        }
        List<ResponseBean> res = QuestionOfSectionFactory.getCurrentInstance().retrieveResponsesBeanFromQuestion(15);
        for(ResponseBean r: res){
            System.out.println(r.getText()+" "+r.getUsername());
        }
        UserProfile user = new UserProfileDAOJDBC().retrieveUserFromUsername("giada");

        QuestionOfSectionFactory.getCurrentInstance().addResponseToQuestion(15, new Response("aaaaaaa", user));
        for(ResponseBean r2: QuestionOfSectionFactory.getCurrentInstance().retrieveResponsesBeanFromQuestion(15)){
            System.out.println(r2.getText()+" "+r2.getUsername());
        }
    }

    @Test
    public void test4(){
        try {
            List<Response> responses = new ResponseDAOJDBC().retrieveResponseFromQuestionID(15);
            Question q = new Question("boh", null, null, 15, responses);
            for(Response r: q.getResponses()){
                System.out.println(r.getResponseText());
            }
            UserProfile user = new UserProfileDAOJDBC().retrieveUserFromUsername("giada");
            q.addResponse(new Response("ris1", user));
            for(Response r: q.getResponses()){
                System.out.println(r.getResponseText());
            }
        } catch (PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        } catch (ItemNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
