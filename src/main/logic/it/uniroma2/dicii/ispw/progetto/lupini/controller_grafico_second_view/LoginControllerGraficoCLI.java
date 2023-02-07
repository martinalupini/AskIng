package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LoginControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ItemNotFound;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

public class LoginControllerGraficoCLI extends EmptyScreenControllerGraficoCLI{


    public void selectedLogin(String username, String password) throws ItemNotFound, PersistanceLayerNotAvailable {

        try {
            LoginControllerAppl logctl = new LoginControllerAppl();
            logctl.login(username, password);
        }catch(ItemNotFound e){
            throw new ItemNotFound("Username o password errati. Riprovare. ");
        }catch(PersistanceLayerNotAvailable e){
            throw new PersistanceLayerNotAvailable("Spacenti, si sono verificati dei problemi tecnici. Riprovare più tardi");
        }


    }

}
