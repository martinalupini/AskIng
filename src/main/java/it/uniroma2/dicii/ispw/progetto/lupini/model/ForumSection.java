package it.uniroma2.dicii.ispw.progetto.lupini.model;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.DBNotAvailable;

import java.util.ArrayList;
import java.util.List;

public class ForumSection {

    private String section;
    private List<Question> questions;

    public ForumSection(String name){
        questions = new ArrayList<>();
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

    //A copy of the list is returned since it is impossible to pass a reference of this.questions outside ForumSection
    public List<Question> getQuestions() throws DBNotAvailable {

        List<Question> newList = new ArrayList<Question>();

        try {
            if (questions.isEmpty()) {
                ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();
                questions = forumSectionDAOJDBC.retrieveQuestionOfSection(section.toLowerCase());
            }

            if (!questions.isEmpty()) {
                for (Question q : questions) {
                    Question newQ = q.cloneQuestion();
                    newList.add(newQ);
                }
            }
        }catch(DBNotAvailable e){
            throw new DBNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle domande. Riprovare più tardi");
        }

        return newList;
    }
}
