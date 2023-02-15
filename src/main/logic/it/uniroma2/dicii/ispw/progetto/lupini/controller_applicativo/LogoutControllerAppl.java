package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;

public class LogoutControllerAppl {

    public void exit(){
        CurrentUserProfileBean currentUserProfileBean = CurrentUserProfileBean.getProfileInstance();
        currentUserProfileBean.unsetUser();

        CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
        currentUserProfile.unsetCurrentUser();

        int a;

    }

}
