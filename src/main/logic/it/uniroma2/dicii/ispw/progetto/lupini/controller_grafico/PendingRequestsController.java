package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.engineering.RequestsFactory;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PendingRequestsController extends EmptyScreen implements Initializable {
    @FXML
    private VBox requestLayout;

    List<RequestBean>  requests = new ArrayList<>();

    @FXML
    private Label errorLabel;

    @FXML
    private Label descriptionLabel;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        requests = requests();

        for(RequestBean r : requests){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("requestItem.fxml"));

            try{
                Button button  = fxmlLoader.load();

                RequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setRequestItem(r.getText());

                //qua devo fare che ogni volta che ci clicco apre
                button.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(TitleCourseController.class.getResource("requestView.fxml"));
                        Parent root = loader.load();

                        ViewRequestController viewRequestController = loader.getController();
                        viewRequestController.loadData(r.getUsername(), r.getEmail(), r.getPoints(), r.getBadBehaviour(), r.getText());

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new ImpossibleStartGUI( "Errore on starting the GUI");
                    }
                });



                requestLayout.getChildren().add(button);

            } catch (IOException e){
                throw new ImpossibleStartGUI( "Errore on starting the GUI");
            }
        }
    }

    private List<RequestBean> requests() {

        List<RequestBean> req = new ArrayList<>();

        try {
             req = RequestsFactory.getCurrentInstance().getRequestsBean();

        }catch(PersistanceLayerNotAvailable e){
            descriptionLabel.setVisible(false);
            errorLabel.setText(e.getMessage());
        }

        return req;


    }



}
