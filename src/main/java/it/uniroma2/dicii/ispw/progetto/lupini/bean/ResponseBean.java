package it.uniroma2.dicii.ispw.progetto.lupini.bean;

public class ResponseBean {

    String username = "";
    String text = "";

    public ResponseBean(String user, String text){
        this.username = user;
        this.text = text;

    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
