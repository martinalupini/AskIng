package it.uniroma2.dicii.ispw.progetto.lupini.bean;

public class UserProfileBean {

    private String email = "";
    private String username = "";
    private int badBehaviour = 0;
    private int points = 0;

    private String role = "";



    public UserProfileBean(String username, String email,String role ){
        this.username = username;
        this.email = email;
        this.role = role;

    }

    public UserProfileBean(String username, String email, String role, int points, int badBehaviour){
        this.username = username;
        this.email = email;
        this.points = points;
        this.badBehaviour = badBehaviour;
        this.role = role;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void increaseBadBehaviour() {
        this.badBehaviour += 1;
    }


    public void increasePoints(){this.points += 5;}


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

    public String getRole() {
        return role;
    }
}
