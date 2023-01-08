package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;
import org.junit.Test;

import java.util.List;

public class TestRequestDAO {

    @Test
    public void testRetrieveRequests(){

        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();

        List<Request> list = null;
        try {
            list = requestDAOJDBC.retrieveRequests();
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

        for(Request r: list ){

            System.out.println(r.getAuthor()+ "  " +r.getText());
        }
    }
}
