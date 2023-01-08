package it.uniroma2.dicii.ispw.progetto.lupini.bean;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.view.SectionController;

import java.util.ArrayList;
import java.util.List;

public class ForumSectionBean {

    private String name;
    private List<QuestionBean> questions = new ArrayList<>();

    public ForumSectionBean(String name){
        this.name = name;
    }

    public List<QuestionBean> getQuestions() throws DBNotAvailable {
        getQuestionsFromModel();
        return questions;
    }

    private void getQuestionsFromModel() throws DBNotAvailable {
        if( questions.isEmpty()) {
            ForumSection forumSection = new ForumSection(name);
            List<Question> questionsFromModel = forumSection.getQuestions();

            //Is needed to convert the questions retrieved from Question to QuestionBean

            for (Question q : questionsFromModel) {
                questions.add(SectionController.convertQuestion(q));
            }
        }
    }

    public void setQuestions(List<QuestionBean> questions) {
        this.questions = questions;
    }


}
