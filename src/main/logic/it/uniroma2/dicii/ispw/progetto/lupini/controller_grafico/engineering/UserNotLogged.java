package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.EmptyScreenControllerGraficoJavaFX;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.LoginControllerGraficoJavaFX;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.SectionControllerGraficoJavaFX;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.ViewSingleQuestionControllerGraficoJavaFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserNotLogged {

    SectionControllerGraficoJavaFX sectionController;
    ViewSingleQuestionControllerGraficoJavaFX viewQuestionController;


    public void setSectionController(SectionControllerGraficoJavaFX sectionController) {
        this.sectionController = sectionController;
    }

    public void setViewQuestionController(ViewSingleQuestionControllerGraficoJavaFX viewQuestionController){
        this.viewQuestionController = viewQuestionController;
    }

    //Ogni volta che l'utente non è loggato le si vuole eseguire un'operazione che richiede il login
    //viene chiamato questo metodo per avviare la schermata di login
    public void userNotLogged(String nextView, ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(EmptyScreenControllerGraficoJavaFX.class.getResource("login.fxml"));
                Parent root = loader.load();

                LoginControllerGraficoJavaFX loginController = loader.getController();
                loginController.setNextWindow(nextView);
                loginController.setSectionController(this.sectionController);
                loginController.setViewQuestionController(this.viewQuestionController);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }catch(IOException e){
                throw new ImpossibleStartGUI( "Errore on starting the GUI");
            }
    }
}
