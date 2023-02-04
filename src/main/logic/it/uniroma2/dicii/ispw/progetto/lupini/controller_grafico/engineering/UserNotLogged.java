package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.EmptyScreen;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.LoginControllerGrafico;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.SectionController;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.ViewQuestionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserNotLogged {

    SectionController sectionController;
    ViewQuestionController viewQuestionController;


    public void setSectionController(SectionController sectionController) {
        this.sectionController = sectionController;
    }

    public void setViewQuestionController(ViewQuestionController viewQuestionController){
        this.viewQuestionController = viewQuestionController;
    }

    public void userNotLogged(String nextView, ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(EmptyScreen.class.getResource("login.fxml"));
                Parent root = loader.load();

                LoginControllerGrafico loginController = loader.getController();
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
