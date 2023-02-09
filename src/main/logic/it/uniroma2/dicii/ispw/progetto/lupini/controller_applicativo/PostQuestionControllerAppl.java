package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.CheckBannedWords;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.IncreaseUserPoints;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionOfSectionFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.interfaces.NewQuestionControllerInterface;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;

import java.util.ArrayList;

public class PostQuestionControllerAppl {

    private NewQuestionControllerInterface controllerGrafico;


    public void setControllerGrafico(NewQuestionControllerInterface controllerGrafico) {
        this.controllerGrafico = controllerGrafico;
    }

    public void checkAndProcessQuestion(QuestionBean questionBean, String section) throws PersistanceLayerNotAvailable, BannedWordFoundException {
        try {

            CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
            //per prima cosa controllo l'eventuale presenza di parole bannate
            CheckBannedWords.checkText(questionBean.getText());

            //se non sono presenti procedo a salvare la domanda in memoria
            Question newQuestion = new Question(questionBean.getText(), questionBean.getKeywords(), currentUserProfile.getCurrentUser());
            QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
            int id = questionDAOJDBC.saveNewQuestion(newQuestion, section);

            Question q = new Question(newQuestion.getQuestionText(), newQuestion.getKeywords(), newQuestion.getAuthor(), id, new ArrayList<>());

            //aggiungo la domanda alla relativa sezione
            QuestionOfSectionFactory.getCurrentInstance().addQuestionToSection(section, q);

            //aggiorno points utente
            if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                IncreaseUserPoints.increaseUserPoints();
            }



            //ora bisogna informare l'utente che l'operazione è andata a buon fine
            this.controllerGrafico.publicationSuccessful();


        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        }
    }




}
