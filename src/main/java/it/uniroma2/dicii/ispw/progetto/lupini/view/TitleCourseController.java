package it.uniroma2.dicii.ispw.progetto.lupini.view;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TitleCourseController{
    @FXML
    private Button title;

    public void setTitle(String title) {
        this.title.setText(title);
    }


}
