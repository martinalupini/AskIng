package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.RequestAlreadyDone;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestRequestDAO {

    @Test
    public void testRetrieveRequests(){

        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();

        List<Request> list;
        try {
            list = requestDAOJDBC.retrieveRequests();
        } catch (DBNotAvailable e) {
            throw new RuntimeException(e);
        }

        for(Request r: list ){

            System.out.println(r.getAuthor()+ "  " +r.getText());
        }

        assertThat("non vuoto", list!=null);
    }

    @Test
    public void testRegisterNewRequest(){
        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
        try {
            requestDAOJDBC.registerNewRequest("ciao", "luigi");
        } catch (DBNotAvailable | RequestAlreadyDone e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDeleteRequestFromUsername(){
        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
        try {
            requestDAOJDBC.deleteRequestFromUsername("luigi");
        } catch (DBNotAvailable e) {
            e.printStackTrace();
        }
    }
}
