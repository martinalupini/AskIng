package it.uniroma2.dicii.ispw.progetto.lupini.model;

public class RegularUser extends Role{

    private int points;
    private int badBehaviour;

    public RegularUser(int points, int badBehaviour){
        this.points = points;
        this.badBehaviour = badBehaviour;
    }

    //the badBehvaiour and points of the user can be increased only
    public void increasePoints(){
        points++;
    }

    public void increaseBadBehaviour(){
        badBehaviour++;
    }


    public int getBadBehaviour() {
        return badBehaviour;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String userRoleIs(){
        return "regular";
    }
}
