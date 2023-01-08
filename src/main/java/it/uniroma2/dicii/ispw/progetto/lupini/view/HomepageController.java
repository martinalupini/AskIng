package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController extends EmptyScreen{


        @FXML
        void visualizeRequests(ActionEvent event) {

                try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("pendingRequests.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                }catch(IOException e){
                        throw new RuntimeException(e);
                }

        }

        @Override
        @FXML
        public void goToHomepage(ActionEvent e){}


        @FXML
        void exit(ActionEvent event) {

                /*This method is empty because have to be finished

                 */


        }
}