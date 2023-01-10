package it.uniroma2.dicii.ispw.progetto.lupini.view.engineering;


import it.uniroma2.dicii.ispw.progetto.lupini.view.EmptyScreen;
import it.uniroma2.dicii.ispw.progetto.lupini.view.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserNotLogged {

    private UserNotLogged(){}

    public static void userNotLogged(String nextView, ActionEvent event){
            try {
                FXMLLoader loader = new FXMLLoader(EmptyScreen.class.getResource("login.fxml"));
                Parent root = loader.load();

                LoginController loginController = loader.getController();
                loginController.setNextWindow(nextView);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }catch(IOException e){
                e.printStackTrace();
            }
    }
}
