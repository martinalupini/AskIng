package it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.dao.jdbc.ForumSectionDAOJDBC;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.model.ForumSection;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Moderator;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Question;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Response;
import java.util.ArrayList;
import java.util.List;

/*
    Questa classe ha lo scopo di recuperare dallo strato di persistenza le classi richieste dall'applicazione, ovvero
    le sezioni e le domande che contengono e le risposte delle domande.
*/
public class QuestionOfSectionFactory {

    private static QuestionOfSectionFactory instance = null;

    private List<ForumSection> sections;

    private QuestionOfSectionFactory(){
        this.sections = new ArrayList<>();
    }

    public static QuestionOfSectionFactory getCurrentInstance(){
        if(instance == null){
            instance = new QuestionOfSectionFactory();
        }

        return instance;
    }

    /*
    Questo metodo viene invocato dai controller grafici e chiama a sua volta altri due metodi. retrieveQuestionOfSection
    restituisci le domande della sezione cercata. convertQuestion si occupa della conversione da model a bean.
     */
    public List<QuestionBean> returnQuestionBeanOfSection(String sectionName) throws PersistanceLayerNotAvailable {

        List<QuestionBean> list = new ArrayList<>();

        //per prima cosa vado a recuperare le domande della sezione
        List<Question> questions = retrieveQuestionsFromSection(sectionName);

        //converto in bean da passare al controller grafico
        for(Question q : questions){
            list.add(convertQuestion(q));
        }

        return list;


    }


    private List<Question> retrieveQuestionsFromSection(String sectionName) throws PersistanceLayerNotAvailable {

        //per prima cosa controllo se la sezione è già stata recuperata in precedenza
        for( ForumSection f: this.sections){
            if(f.getSectionName().equalsIgnoreCase(sectionName)){
                return f.getQuestions();
            }
        }

        //se non è stata recuperata consulto il DAO e la aggiungo alla lista di sezioni
        ForumSectionDAOJDBC forumSectionDAOJDBC = new ForumSectionDAOJDBC();
        ForumSection newSection =  forumSectionDAOJDBC.retrieveSection(sectionName);
        this.sections.add(newSection);
        return newSection.getQuestions();


    }


    private QuestionBean convertQuestion(Question question){
        return new QuestionBean(question.getAuthor().getUsername(), question.getQuestionText(), question.getKeywords(), question.getId());

    }

    /*
    Questi metodi hanno un funzionamento analogo al precedente.
     */

    public List<ResponseBean>  retrieveResponsesBeanFromQuestion(int idQuestion) throws PersistanceLayerNotAvailable {

        List<ResponseBean> responses= new ArrayList<>();

        for(Response r: retrieveResponsesFromQuestion(idQuestion)){
            responses.add(convertResponse(r));
        }

        return responses;


    }



    private List<Response>  retrieveResponsesFromQuestion(int idQuestion){
        for( ForumSection f: this.sections){
            for(Question q: f.getQuestions()){
                if(q.getId() == idQuestion){
                    return q.getResponses();
                }
            }
        }
        return new ArrayList<>();
    }


    public static ResponseBean convertResponse( Response response){
        String username = response.getAuthor().getUsername();
        String text = response.getResponseText();

        return new ResponseBean(username, text);

    }


    //metodi per l'aggiunta di una domanda e di una risposta
    public void addQuestionToSection(String section, Question q){
        for( ForumSection f: this.sections){
            if(f.getSectionName().equalsIgnoreCase(section)){
                f.addQuestion(q);
                return;
            }
        }
    }

    public void addResponseToQuestion(int idQuestion, Response r){
        for( ForumSection f: this.sections){
            for(Question q: f.getQuestions()){
                if(q.getId() == idQuestion){
                    f.addResponseToQuestion(idQuestion, r);
                    return;
                }
            }
        }
    }


    public void changeRoleOfUser(String username){
        for( ForumSection f: this.sections){
            for(Question q: f.getQuestions()){
                if(q.getAuthor().getUsername().equals(username)){
                    q.getAuthor().setRole(new Moderator());
                }

                for(Response r: q.getResponses()){
                    if(r.getAuthor().getUsername().equals(username)){
                        r.getAuthor().setRole(new Moderator());
                    }
                }
            }
        }
    }
}
