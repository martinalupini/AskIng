package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controllerApplicativo.RequestControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.model.RegularUser;
import it.uniroma2.dicii.ispw.progetto.lupini.model.Request;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PendingRequestsController extends EmptyScreen implements Initializable {
    @FXML
    private VBox requestLayout;;

    List<RequestBean>  requests = new ArrayList<>();


    public void newRequest(RequestBean request){
        requests.add(request);
    }

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
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader loader = new FXMLLoader(TitleCourseController.class.getResource("requestView.fxml"));
                            Parent root = loader.load();

                            ViewRequestController viewRequestController = loader.getController();
                            viewRequestController.loadData(r.getUsername(),r.getEmail(), r.getPoints(), r.getBadBehaviour(), r.getText());

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }catch(IOException e){
                            e.printStackTrace();
                            //throw new RuntimeException();
                        }
                    }
                });



                requestLayout.getChildren().add(button);

            } catch (IOException e){
                e.printStackTrace();
                //throw new RuntimeException();
            }
        }
    }

    private List<RequestBean> requests() {

        List<RequestBean> req = new ArrayList<>();

        RequestControllerAppl requestControllerAppl = new RequestControllerAppl();

        List<Request> request =  requestControllerAppl.getRequests();

        for(Request r : request){

            req.add(convertRequest(r));

        }

        return req;
    }


    private RequestBean  convertRequest(Request req ){

        String username = req.getAuthor().getUsername();
        String email = req.getAuthor().getEmail();
        int points = ((RegularUser)req.getAuthor().getRole()).getPoints();
        int badBehaviour = ((RegularUser)req.getAuthor().getRole()).getBadBehaviour();

        RequestBean requestBean = new RequestBean(req.getText(), username,email,points, badBehaviour);
        return requestBean;
    }
}
