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

    public void selectedBecomeModerator(){
        if(!CurrentUserProfileBean.getProfileInstance().isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }

        BecomeModeratorFormView becomeModeratorFormView = new BecomeModeratorFormView();
        becomeModeratorFormView.displayForm();
    }

    public void selectedViewModeratorRequests(){
        ManageRequestsView manageRequestsView = new ManageRequestsView();
        manageRequestsView.displayRequests();
    }
}
