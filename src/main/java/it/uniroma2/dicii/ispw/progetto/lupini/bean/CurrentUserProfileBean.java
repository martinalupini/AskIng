package it.uniroma2.dicii.ispw.progetto.lupini.bean;

public class CurrentUserProfileBean {

    private boolean logged = false;
    private UserProfileBean user = null;
    private static CurrentUserProfileBean profileInstance = null;

    private CurrentUserProfileBean(){}

    public static CurrentUserProfileBean getProfileInstance() {
        if (CurrentUserProfileBean.profileInstance == null) {
            CurrentUserProfileBean.profileInstance = new CurrentUserProfileBean();
        }
        return profileInstance;
    }

    public void setUser(UserProfileBean user) {
        if(!this.logged &&  this.user ==null ){
            this.logged = true;
            this.user = user;
        }

    }

    public boolean isLogged() {
        return logged;
    }

    public String getRole(){

        return user.getRole();

    }

    public String getUsername(){

        return user.getUsername();

    }

    public String getEmail(){

        return user.getEmail();
    }

    public int getPoints(){

        return user.getPoints();

    }

    public int getBadBehaviour(){
        return user.getBadBehaviour();
    }
}
