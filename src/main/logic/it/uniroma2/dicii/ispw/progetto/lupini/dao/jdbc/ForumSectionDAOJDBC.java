package it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;

public class ForumSectionDAOJDBC {

    public ForumSection retrieveSection(String sectionName) throws PersistanceLayerNotAvailable {

        return new ForumSection(sectionName, new QuestionDAOJDBC().retrieveQuestionsOfSection(sectionName));
    }
}
