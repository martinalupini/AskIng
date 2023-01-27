package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

public class LoginControllerGraficoCLI extends EmptyScreenControllerGraficoCLI{


    public void selectedLogin(String username, String password) throws ItemNotFound, PersistanceLayerNotAvailable {

        LoginControllerAppl logctl = new LoginControllerAppl(null);

        logctl.login(username,password);


    }

}
