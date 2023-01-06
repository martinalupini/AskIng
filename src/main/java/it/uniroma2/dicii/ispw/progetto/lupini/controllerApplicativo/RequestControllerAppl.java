package it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;

import java.util.List;

public class RequestControllerAppl {

    public List<Request> getRequests(){

        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
        List<Request> list = requestDAOJDBC.retrieveRequests();

        return list;

    }
}
