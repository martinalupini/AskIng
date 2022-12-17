package it.uniroma2.dicii.ispw.progetto.lupini.bean;

public class ProfileBean {

    private String pfpURL;
    private String email;
    private String username;
    private int badBehaviour;
    private int points;
    private static ProfileBean profileInstance = null;

    protected ProfileBean(String username, String email ){
        this.username = username;
        this.email = email;

    }

    public static ProfileBean getProfileInstance(String username, String email) {
        if(ProfileBean.profileInstance ==null){
            ProfileBean.profileInstance = new ProfileBean(username, email);
        }
        return profileInstance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBadBehaviour(int badBehaviour) {
        this.badBehaviour = badBehaviour;
    }

    public void setPfpURL(String pfpURL) {
        this.pfpURL = pfpURL;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public int getBadBehaviour() {
        return badBehaviour;
    }

    public int getPoints() {
        return points;
    }

    public String getEmail() {
        return email;
    }

    public String getPfpURL() {
        return pfpURL;
    }


}
