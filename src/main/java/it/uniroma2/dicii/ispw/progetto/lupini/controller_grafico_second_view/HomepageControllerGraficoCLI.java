package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.ForumView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.LoginView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.ProfileView;

public class HomepageControllerGraficoCLI {

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
}
