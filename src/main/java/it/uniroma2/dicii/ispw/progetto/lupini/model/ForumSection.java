package it.uniroma2.dicii.ispw.progetto.lupini.model;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

import java.util.ArrayList;
import java.util.List;

public class ForumSection {

    private String section;
    private List<Question> questions;

    public ForumSection(String name){
        questions = new ArrayList<>();
        this.section = name;
    }

    //A copy of the list is returned since it is impossible to pass a reference of this.questions outside ForumSection
    public List<Question> getQuestions() throws PersistanceLayerNotAvailable {

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
        }catch(PersistanceLayerNotAvailable e){
            throw new PersistanceLayerNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle domande. Riprovare più tardi");
        }

        return newList;
    }
}
