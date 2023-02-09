package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class SubjectQuestionBean {

    private List<ObserverOfQuestionBean> observers = new ArrayList<>();

    public void attach(ObserverOfQuestionBean obs){
        observers.add(obs);

    }


    public void notifyObservers(){
        for( ObserverOfQuestionBean o: observers){
            o.update();
        }
    }

}
