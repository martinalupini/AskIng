package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewQuestionController implements Initializable {
    @FXML
    VBox menuLayout;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menuItem.fxml"));

        try{
            HBox hbox = fxmlLoader.load();
            MenuItemController menuContr = fxmlLoader.getController();

            menuLayout.getChildren().add(hbox);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
