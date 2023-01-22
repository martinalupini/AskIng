package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.CheckBannedWords;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.IncreaseUserPoints;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.QuestionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.BannedWordFoundException;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.CurrentUserProfile;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.QuestionFormController;

import java.util.ArrayList;
import java.util.List;

public class PostQuestionControllerAppl {

    private QuestionFormController questionFormController;

    public PostQuestionControllerAppl(QuestionFormController questionFormController){
        this.questionFormController = questionFormController;
    }


    public void checkAndProcessQuestion(QuestionBean questionBean, String section) throws PersistanceLayerNotAvailable {
        try {

            CurrentUserProfile currentUserProfile = CurrentUserProfile.getCurrentUserInstance();
            //per prima cosa controllo l'eventuale presenza di parole bannate
            CheckBannedWords.checkText(questionBean.getText());

            //se non sono presenti procedo a salvare la domanda in memoria
            Question newQuestion = new Question(questionBean.getText(), questionBean.getKeywords(), currentUserProfile.getCurrentUser());
            QuestionDAOJDBC questionDAOJDBC = new QuestionDAOJDBC();
            questionDAOJDBC.saveNewQuestion(newQuestion, section);

            //aggiorno points utente

            if(currentUserProfile.getCurrentUser().getRoleName().equals("regular user")) {
                IncreaseUserPoints.increaseUserPoints();
            }



            //ora bisogna informare l'utente che l'operazione è andata a buon fine
            this.questionFormController.publicationSuccessful();


        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Si sono verificati dei problemi tecnici. Riprovare più tardi.");
        } catch (BannedWordFoundException e) {
            this.questionFormController.bannedWordPresent();
        }
    }



    public List<QuestionBean> returnQuestionOfSection(String username) throws PersistanceLayerNotAvailable {

        List<QuestionBean> list = new ArrayList<>();

        try{

            ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();
             List<Question> questions = forumSectionDAOJDBC.retrieveQuestionOfSection(username);

            for(Question q : questions){
                list.add(convertQuestion(q));
            }

            return list;

        } catch (PersistanceLayerNotAvailable e) {
            throw new PersistanceLayerNotAvailable("Spacenti, si sono verificati dei problemi nel caricamento delle domande. Riprovare più tardi");
        }

    }

    private QuestionBean convertQuestion(Question question){
        return new QuestionBean(question.getAuthor().getUsername(), question.getQuestionText(), question.getKeywords(), question.getId());

    }



}
