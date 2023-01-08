package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;

import java.util.List;

public class RequestControllerAppl {

    public List<Request> getRequests() throws DBNotAvailable {

        RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();

        try {
            return requestDAOJDBC.retrieveRequests();
        } catch (DBNotAvailable e) {
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle risposte. Riprovare più tardi");
        }

    }
}
