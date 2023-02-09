package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;


import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ForumControllerGraficoJavaFX extends EmptyScreenControllerGraficoJavaFX implements Initializable{

    @FXML
    private VBox titleLayout;

    @FXML
    private Label notImplemented;


    private final String[]  courses = {"Algebra e Logica", "Analisi 1", "Analisi 2", "Automi e Linguaggi", "Basi di Dati", "Calcolatori Elettronici", "Campi Elettromagnetici", "Fisica 1", "Fisica 2", "Fondamenti di Controlli", "Fondamenti di Informatica", "Fondamenti di Elelttronica","Fondamenti di Telecomunicazione", "Geometria", "Ingegneria degli Algoritmi", "Ingegneria di Internet e Web","Ingegneria del Software e Progettazione Web", "Probabilità e Statistica", "Ricerca Operativa", "Sistemi Operativi"};


    @Override
    @FXML
    public void goToForum(ActionEvent e){
        //This method is empty because the screen is already in the forum page
    }

    @FXML
    void clickSearch(ActionEvent event) {
        this.notImplemented.setText("NOT IMPLEMENTED");
    }

    //metodo per il caricamento della schermata
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(String c : courses){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("courseTitle.fxml"));

            try{
                Button button  = fxmlLoader.load();
                TitleCourseControllerGraficoJavaFX titleCourseController = fxmlLoader.getController();
                titleCourseController.setTitle(c);


                button.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(TitleCourseControllerGraficoJavaFX.class.getResource("section.fxml"));
                        Parent root = loader.load();

                        SectionControllerGraficoJavaFX sectionController = loader.getController();
                        sectionController.setSectionName(button.getText());
                        sectionController.initialize(button.getText());

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new ImpossibleStartGUI("Errore on starting the GUI");
                    }
                });



                titleLayout.getChildren().add(button);

            } catch (IOException e){
                throw new ImpossibleStartGUI( "Errore on starting the GUI");
            }
        }
    }
}
