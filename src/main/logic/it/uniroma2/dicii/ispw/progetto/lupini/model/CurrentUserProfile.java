package it.uniroma2.dicii.ispw.progetto.lupini.model;

public class CurrentUserProfile {

    private static CurrentUserProfile currentUserInstance = null;

    private UserProfile currentUser = null;

    private boolean logged = false;

    /*CurrentUserProfile uses the pattern Singleton. So the constructor method is private and the getCurrentIntsance returns the only instance
    of the class.*/

    private CurrentUserProfile(){
    }

    public static CurrentUserProfile getCurrentUserInstance(){
        if(CurrentUserProfile.currentUserInstance == null){
            CurrentUserProfile.currentUserInstance = new CurrentUserProfile();
        }
        return currentUserInstance;
    }

    /*setCurrentUser is a public method used to set the current user logged in the system. This method can only
    be effected the first time that is called, when the user is not yet set.
     */
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

    //used from other class to get information of the current user such as username, email, etc
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


