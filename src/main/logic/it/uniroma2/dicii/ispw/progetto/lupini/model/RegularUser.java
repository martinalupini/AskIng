package it.uniroma2.dicii.ispw.progetto.lupini.model;


public class RegularUser extends Role{

    private int points;
    private int badBehaviour;

    public RegularUser(int points, int badBehaviour){
        this.points = points;
        this.badBehaviour = badBehaviour;
    }
    @Override
    public String userRoleIs(){
        return "regular user";
    }

    public void increasePoints(){
        points += 5;
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



}
