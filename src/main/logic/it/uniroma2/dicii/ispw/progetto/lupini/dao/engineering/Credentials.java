package it.uniroma2.dicii.ispw.progetto.lupini.dao.engineering;

public class Credentials {

    private final String pass= "Marrari11!";

    private static Credentials instance = null;

    private Credentials(){}

    public static Credentials getCredentials(){
        if(instance == null){
            instance = new Credentials();
        }
        return instance;
    }

    public String getPass() {
        return pass;
    }
}
