package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import org.junit.Test;

import java.util.List;

public class TestQuestionDAOJDBC {

    @Test
    public void testRetrieveResponsesFromQuestionID(){

        QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
        List<Response> list;
        try {
            list = questionDAOJDBC.retrieveResponseFromQuestionID(1);
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

        for(Response r: list){

            System.out.println(r.getResponseText()+ "    "+ r.getAuthor().getUsername());
        }
    }
}
