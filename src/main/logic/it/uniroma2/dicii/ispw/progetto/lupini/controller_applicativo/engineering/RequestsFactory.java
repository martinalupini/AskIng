package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.RequestDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestsFactory {

    private List<Request> requests = null;

    private static RequestsFactory instance = null;

    private RequestsFactory(){}

    public static RequestsFactory getCurrentInstance(){
        if(instance==null){
            instance = new RequestsFactory();
        }

        return instance;
    }

    public List<RequestBean> getRequestsBean() throws PersistanceLayerNotAvailable {

        List<RequestBean> req = new ArrayList<>();

        if(requests == null){
            RequestDAOJDBC requestDAOJDBC = new RequestDAOJDBC();
            requests =  requestDAOJDBC.retrieveRequests();
        }

        for (Request r : requests) {

            req.add(convertRequest(r));
        }

        return req;
    }

    private RequestBean  convertRequest(Request req ){

        String username = req.getAuthor().getUsername();
        String email = req.getAuthor().getEmail();
        int points = ((RegularUser)req.getAuthor().getRole()).getPoints();
        int badBehaviour = ((RegularUser)req.getAuthor().getRole()).getBadBehaviour();

        return new RequestBean(req.getText(), username,email,points, badBehaviour);
    }
}
