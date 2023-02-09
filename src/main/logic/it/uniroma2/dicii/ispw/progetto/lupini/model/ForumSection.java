package it.uniroma2.dicii.ispw.progetto.lupini.model;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.ObserverOfQuestionBean;

import java.util.ArrayList;
import java.util.List;

public class ForumSection {

    private String sectionName;
    private List<Question> questions;

    public ForumSection(String name, List<Question> questions){
        this.sectionName = name;
        this.questions = new ArrayList<>();
        for(Question q: questions){
            this.questions.add(q.cloneQuestion());
        }
    }

    public String getSectionName() {
        return sectionName;
    }

    //A copy of the list is returned since it is impossible to pass a reference of this.questions outside ForumSection
    public List<Question> getQuestions(){
        List<Question> copyQuestions = new ArrayList<>();
        for(Question q: this.questions){
            copyQuestions.add(q.cloneQuestion());
        }
        return copyQuestions;
    }

    public void addQuestion(Question quest){
        this.questions.add(quest.cloneQuestion());
    }

    public void addResponseToQuestion(int idQuestion, Response response){
        for(Question q: this.questions){
            if(q.getId() == idQuestion){
                q.addResponse(response);
            }
        }
    }


}
