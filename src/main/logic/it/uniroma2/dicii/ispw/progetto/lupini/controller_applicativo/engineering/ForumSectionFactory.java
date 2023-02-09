package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;

public class ForumSectionFactory {

    public ForumSection retrieveSection(String sectionName) throws PersistanceLayerNotAvailable {

        return new ForumSection(sectionName, new QuestionDAOJDBC().retrieveQuestionsOfSection(sectionName));
    }
}
