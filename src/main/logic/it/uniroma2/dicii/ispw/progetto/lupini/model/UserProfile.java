package it.uniroma2.dicii.ispw.progetto.lupini.model;

public class UserProfile {

    private String username;
    private String email;

    //private String role;

    //this attribute is used to implements the metamorphosis pattern.
    Role role;

    public UserProfile(String username, String email, Role role){
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserProfile(Role actualRole){
        this.role = actualRole;
    }

    @Override
    public String toString() {
        return username+email+ role.userRoleIs();
    }

    public String getRoleName(){

        return role.userRoleIs();
    }

    public Role getRole(){
        return this.role;
    }

/*
    public UserProfile(String username, String email, String role){
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public String getRole() {
        return role;
    }*/

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }


}
