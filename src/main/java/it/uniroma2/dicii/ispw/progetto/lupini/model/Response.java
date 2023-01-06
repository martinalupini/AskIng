package it.uniroma2.dicii.ispw.progetto.lupini.model;

public class Response {

    private String responseText;
    private UserProfile author;

    //constructor with parametres because is used in question (composition)
    public Response(String text, UserProfile author){
        this.responseText = text;
        this.author =  author;
    }


    public Response cloneResponse(){

        Response clone = new Response(this.responseText, this.author);
        return clone;
    }

    //getters
    public String getResponseText() {
        return responseText;
    }

    public UserProfile getAuthor() {
        return author;
    }
}
