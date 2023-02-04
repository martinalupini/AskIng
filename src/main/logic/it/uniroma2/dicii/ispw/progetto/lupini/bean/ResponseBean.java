package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.engineering.CheckTextLenght;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.TextException;

public class ResponseBean {

    String username = "";
    String text = "";

    public ResponseBean(String user, String text){
        this.username = user;
        this.text = text;

    }

    public ResponseBean(String user){
        this.username = user;

    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws TextException {
        CheckTextLenght.checkTextLength(text);
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
