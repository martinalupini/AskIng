package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LogoutControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.*;

public class HomepageControllerGraficoCLI extends EmptyScreenControllerGraficoCLI{

    public void goToForum(){
        ForumView forumView = new ForumView();
        forumView.displayForum();
    }

    public void goToProfile(){
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        if(!currentUserProfileBean.isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }
        ProfileView profileView = new ProfileView();
        profileView.displayProfile();
    }

    public void exit(){
        if(!CurrentUserProfileBean.getProfileInstance().isLogged())  return;
        LogoutControllerAppl logoutControllerAppl =new LogoutControllerAppl();
        logoutControllerAppl.exit();
        System.out.println("Logout effettuato con successo!");
    }

    //per fare richiesta da parte di un utente normale di diventare moderatore
    public void selectedBecomeModerator(){
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();

        //prima verifico se l'utente è loggato
        if(!currentUserProfileBean.isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }

        //se è un moderatore lo reindirizzo sulla homepage
        if(currentUserProfileBean.getRole().equals("moderator")){
            goToHomepage();
        }

        //altrimenti viene mostrato il form
        BecomeModeratorFormView becomeModeratorFormView = new BecomeModeratorFormView();
        becomeModeratorFormView.displayForm();
    }

    //utilizzato quando il moderatore richiede di visualizzare le richieste
    public void selectedViewModeratorRequests(){
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();

        //per prima cosa vedo se l'utente è loggato
        if(!currentUserProfileBean.isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }

        ManageRequestsView manageRequestsView = new ManageRequestsView();
        manageRequestsView.displayRequests();
    }
}
