package it.uniroma2.dicii.ispw.progetto.lupini.model;

//Questa classe utilizza il pattern singleton. Questo comporta che l'applicazione sia monoutente
public class CurrentUserProfile {

    private static CurrentUserProfile currentUserInstance = null;

    private UserProfile currentUser = null;

    private boolean logged = false;

    private CurrentUserProfile(){
    }

    public static CurrentUserProfile getCurrentUserInstance(){
        if(CurrentUserProfile.currentUserInstance == null){
            CurrentUserProfile.currentUserInstance = new CurrentUserProfile();
        }
        return currentUserInstance;
    }

    public void setCurrentUser(UserProfile profile){
        if(this.currentUser == null && !this.logged){
            this.currentUser = profile;
            this.logged = true;
        }
    }

    public void unsetCurrentUser(){
        this.currentUser = null;
        this.logged = false;
    }

    public UserProfile getCurrentUser() {
        return currentUser;
    }


    public String getRoleName(){
        return currentUser.getRoleName();
    }

    public String getUsername(){
        return currentUser.getUsername();
    }

    public String getEmail(){
        return currentUser.getEmail();
    }

    public Role getRole(){
        return currentUser.getRole();
    }

}


