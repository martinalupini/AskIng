package it.uniroma2.dicii.ispw.progetto.lupini.bean;


import java.util.ArrayList;
import java.util.List;

public class ForumSectionBean {

    private String name;
    private List<QuestionBean> questions = new ArrayList<>();

    public ForumSectionBean(String name){
        this.name = name;
    }


    public void setQuestions(List<QuestionBean> questions) {
        this.questions = questions;
    }


}
