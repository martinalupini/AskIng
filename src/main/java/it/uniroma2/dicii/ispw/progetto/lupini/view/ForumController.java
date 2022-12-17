package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.view.MenuItemController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForumController implements Initializable {

    @FXML
    private ListView<String> courseList;

    @FXML
    private VBox menuLayout;

    String[]  courses = {"Algebra e Logica", "Analisi I", "Analisi II", "Automi e Linguaggi", "Basi di Dati", "Calcolatori Elettronici", "Campi Elettromagnetici", "Fisica I", "Fisica II", "Fondamenti di Controlli", "Fondamenti di Informatica", "Fondamenti di Elelttronica","Fondamenti di Telecomunicazione", "Geometria", "Ingegneria degli Algoritmi", "Ingegneria di Internet e Web","Ingegneria del Software e Progettazione Web", "Probabilità e Statistica", "Ricerca Operativa", "Sistemi Operativi"};
    String currentCourse;


    @FXML
    void clickSearch(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        MenuItemController mc = new MenuItemController();
        mc.exit(event);

    }


    @FXML
    void goToHomepage(ActionEvent event){
        MenuItemController mc = new MenuItemController();
        mc.goToHomepage(event);

    }

    @FXML
    void goToProfile(ActionEvent event) {
        MenuItemController mc = new MenuItemController();
        mc.goToProfile(event);
    }

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

        courseList.getItems().addAll(courses);
        /*courseList.getSelectionModel().selectionModeProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends SelectionMode> observableValue, SelectionMode selectionMode, SelectionMode t1) {

            }
        });*/
    }
}
