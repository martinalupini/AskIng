package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ObserverOfQuestionBean;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.HomepageView;

public class EmptyScreenControllerGraficoCLI extends ObserverOfQuestionBean {

    public void goToHomepage(){
        HomepageView homepageView = new HomepageView();
        homepageView.displayHomepage();
    }

    @Override
    public void update() {
        //Empty because is overrode in QuestionControllerGraficoCLI
    }
}
