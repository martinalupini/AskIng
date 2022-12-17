package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmptyScreen {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void exit(ActionEvent event) {

    }

    @FXML
    public void goToForum(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("forum.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void goToHomepage(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void goToProfile(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("profileView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
