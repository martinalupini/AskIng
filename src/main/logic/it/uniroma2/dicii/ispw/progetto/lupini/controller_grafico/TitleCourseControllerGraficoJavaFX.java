package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TitleCourseControllerGraficoJavaFX {
    @FXML
    private Button title;

    public void setTitle(String title) {
        this.title.setText(title);
    }


}
