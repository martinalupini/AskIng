package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import org.junit.Test;

import java.util.List;

public class TestQuestionDAOJDBC {

    @Test
    public void testRetrieveResponsesFromQuestionID(){

        QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
        List<Response> list = questionDAOJDBC.retrieveResponseFromQuestionID(1);

        for(Response r: list){

            System.out.println(r.getResponseText()+ "    "+ r.getAuthor().getUsername());
        }
    }
}
