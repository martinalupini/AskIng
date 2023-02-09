package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX {

        @FXML
        private Label logoutLabel;

        @FXML
        private Label errorLabel;

        //visualizza tutte le richieste in sospeso
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
                        throw new ImpossibleStartGUI( "Errore on starting the GUI");
                }

        }

        @Override
        @FXML
        public void goToHomepage(ActionEvent e){
                //This method is empty because is not needed to change screen
        }

        //per indicare che il logout è andato a buon fine
        public void setLogoutLabel(String logoutLabel) {
                this.logoutLabel.setText(logoutLabel);
        }

        @FXML
        void removeQuestion(ActionEvent event) {
                this.errorLabel.setText("NOT IMPLEMENTED");
        }
}