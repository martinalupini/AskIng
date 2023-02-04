package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico_second_view;

import it.uniroma2.dicii.ispw.progetto.lupini.second_view.SectionView;

public class ForumControllerGraficoCLI extends EmptyScreenControllerGraficoCLI{


    public Boolean goToSection(String sectionName){

        SectionView sectionView = new SectionView(sectionName);
        sectionView.displaySection();


        return false;
    }
}
