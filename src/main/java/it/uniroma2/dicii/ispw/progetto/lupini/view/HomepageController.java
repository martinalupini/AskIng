package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController extends EmptyScreen{


        @FXML
        void clickBecomeModerator(ActionEvent event) {

                CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();
                String view = "doNewModeratorRequest.fxml";
                nextView = "DoNewModeratorRequest";

                //first I check if the user is logged
                if(!currUser.isLogged()){
                        try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                                Parent root = loader.load();

                                LoginController loginController = loader.getController();
                                loginController.setNextView(nextView);

                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                                return;
                        }catch(IOException e){
                                e.printStackTrace();
                        }
                }

                //if the user is a moderator they cannot do the request
                if(currUser.getRole().equals("moderator")){
                        return;
                }

                try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
                        Parent root = loader.load();

                        DoNewRequestController doNewRequestController = loader.getController();
                        doNewRequestController.loadData(currUser.getUsername(), currUser.getEmail(), currUser.getPoints(), currUser.getBadBehaviour());

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                }catch(IOException e){
                        e.printStackTrace();
                }
        }


        @FXML
        void visualizeRequests(ActionEvent event) {

                try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("pendingRequests.fxml"));
                        Parent root = loader.load();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                }catch(IOException e){
                        e.printStackTrace();
                }

        }

        @Override
        @FXML
        public void goToHomepage(ActionEvent e){}


        @FXML
        void exit(ActionEvent event) {



        }
}