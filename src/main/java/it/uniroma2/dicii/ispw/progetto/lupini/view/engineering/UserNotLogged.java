package it.uniroma2.dicii.ispw.progetto.lupini.view.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.view.EmptyScreen;
import it.uniroma2.dicii.ispw.progetto.lupini.view.LoginController;
import it.uniroma2.dicii.ispw.progetto.lupini.view.SectionController;
import it.uniroma2.dicii.ispw.progetto.lupini.view.ViewQuestionController;
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
    public UserNotLogged(){
    }

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

                LoginController loginController = loader.getController();
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
