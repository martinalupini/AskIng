package it.uniroma2.dicii.ispw.progetto.lupini.model;


import java.util.ArrayList;
import java.util.List;

public class ForumSection {

    private String sectionName;
    private List<Question> questions;

    public ForumSection(String name, List<Question> questions){
        this.sectionName = name;
        this.questions = new ArrayList<>();
        for(Question q: questions){
            this.questions.add(q.clone());
        }
    }

    public String getSectionName() {
        return sectionName;
    }

    //A copy of the list is returned since it is impossible to pass a reference of this.questions outside ForumSection
    public List<Question> getQuestions(){
        List<Question> copyQuestions = new ArrayList<>();
        for(Question q: this.questions){
            copyQuestions.add(q.clone());
        }
        return copyQuestions;
    }

    public void addQuestion(Question quest){
        this.questions.add(quest.clone());
    }
}
