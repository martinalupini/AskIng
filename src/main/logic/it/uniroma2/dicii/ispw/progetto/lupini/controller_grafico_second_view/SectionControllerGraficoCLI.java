package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.QuestionOfSectionFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.LoginView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.NewQuestionView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.QuestionView;

import java.util.List;

public class SectionControllerGraficoCLI extends EmptyScreenControllerGraficoCLI {

    private List<QuestionBean> questions;
    private String sectionName;

    public SectionControllerGraficoCLI(String section){
        this.sectionName = section;
    }

    public  List<QuestionBean> getQuestionOfSection(String sectionName) throws PersistanceLayerNotAvailable {


        QuestionOfSectionFactory factory = QuestionOfSectionFactory.getCurrentInstance();

        this.questions =  factory.returnQuestionBeanOfSection(sectionName.toLowerCase());

        return this.questions;

    }

    public void goToQuestion(int index){

        QuestionView questionView = new QuestionView(this.questions.get(index-1));
        questionView.displayQuestion();

    }

    public void doNewQuestion(){
        if(!CurrentUserProfileBean.getProfileInstance().isLogged()){
            LoginView loginView = new LoginView();
            loginView.displayForm();
        }
        NewQuestionView newQuestionView = new NewQuestionView(this.sectionName);
        newQuestionView.displayForm();
    }
}
