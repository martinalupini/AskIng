package it.uniroma2.dicii.ispw.progetto.lupini.model;

import java.util.List;

public class ForumSection {

    private String section;
    private List<Question> questions = null;

    public ForumSection(String name){
        this.section = name;
    }

    /* the relationship between ForumSection and Question is a relation of composition. A response exists only if the question
        associated exists. If the question is deleted so are the responses.
         */
    public void newQuestion(String text, List<String> keywords){
        CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
        UserProfile author =   currentUserProfile.getCurrentUser();
        Question quest = new Question(text, keywords, author);
        questions.add(quest);
    }


}
