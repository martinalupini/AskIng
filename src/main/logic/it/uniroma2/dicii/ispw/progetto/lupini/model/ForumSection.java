package it.uniroma2.dicii.ispw.progetto.lupini.model;


import java.util.ArrayList;
import java.util.List;

public class ForumSection {

    private String section;
    private List<Question> questions;

    public ForumSection(String name){
        questions = new ArrayList<>();
        this.section = name;
    }

    public String getSection() {
        return section;
    }

    //A copy of the list is returned since it is impossible to pass a reference of this.questions outside ForumSection
    public List<Question> getQuestions(){
        return this.questions;
    }

    public void addQuestion(Question quest){
        this.questions.add(quest);
    }
}
