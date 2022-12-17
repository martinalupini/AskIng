package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForumController extends EmptyScreen implements Initializable  {

    @FXML
    private ListView<String> courseList;

    @FXML
    private VBox menuLayout;

    String[]  courses = {"Algebra e Logica", "Analisi I", "Analisi II", "Automi e Linguaggi", "Basi di Dati", "Calcolatori Elettronici", "Campi Elettromagnetici", "Fisica I", "Fisica II", "Fondamenti di Controlli", "Fondamenti di Informatica", "Fondamenti di Elelttronica","Fondamenti di Telecomunicazione", "Geometria", "Ingegneria degli Algoritmi", "Ingegneria di Internet e Web","Ingegneria del Software e Progettazione Web", "Probabilità e Statistica", "Ricerca Operativa", "Sistemi Operativi"};
    String currentCourse;

    @Override
    @FXML
    public void goToForum(ActionEvent e){}  //This method is empty because the screen is already in the forum page

    @FXML
    void clickSearch(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        courseList.getItems().addAll(courses);
        /*courseList.getSelectionModel().selectionModeProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends SelectionMode> observableValue, SelectionMode selectionMode, SelectionMode t1) {

            }
        });*/
    }
}
