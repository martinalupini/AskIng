package it.uniroma2.dicii.ispw.progetto.lupini.view;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class TitleCourseController implements Initializable {
    @FXML
    private Button title;

    public void setTitle(String title) {
        this.title.setText(title);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
