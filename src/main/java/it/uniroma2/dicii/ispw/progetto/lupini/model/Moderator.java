package it.uniroma2.dicii.ispw.progetto.lupini.model;

import java.util.ArrayList;
import java.util.List;

public class Moderator extends Role {

    @Override
    public String userRoleIs(){
        return "moderator";
    }

    @Override
    public List<Integer> getRoleInformation(){
        return  new ArrayList<>();
    }
}
