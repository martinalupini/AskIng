package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleCourseController implements Initializable {
    @FXML
    private Button title;

    public void setTitle(String title) {
        this.title.setText(title);
    }

    /*@FXML
    public void openSection(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sectionView.fxml"));
            Parent root = loader.load();

            SectionController sectionController = loader.getController();
            sectionController.setSectionName(title.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
