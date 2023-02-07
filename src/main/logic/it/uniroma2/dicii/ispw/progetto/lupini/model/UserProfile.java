package it.uniroma2.dicii.ispw.progetto.lupini.model;

//segue l'implementazione del pattern della metamorfosi
public class UserProfile {

    private String username;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
