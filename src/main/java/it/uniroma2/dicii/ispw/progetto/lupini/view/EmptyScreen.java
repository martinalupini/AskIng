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

public class EmptyScreen {

    private Stage stage;
    private Scene scene;
    private Parent root;

    protected String nextView;

    @FXML
    public void goToForum(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("forum.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void goToHomepage(ActionEvent event){
        String view;
        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();

        //first I check if the user is a moderator
        if(currUser.isLogged() && currUser.getRole().equals("moderator")){
            view = "homepageModerator.fxml";
        }else{
            view = "homepage.fxml";
        }



        try {
            root = FXMLLoader.load(getClass().getResource(view));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void goToProfile(ActionEvent event) {
        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();
        String view = null;
        nextView = "profileView";

        //first I check if the user is logged
        if(!currUser.isLogged()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                root = loader.load();

                LoginController loginController = loader.getController();
                loginController.setNextView(nextView);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                return;
            }catch(IOException e){
                e.printStackTrace();
            }
        }else if(currUser.getRole().equals("regular user")){
            view = "profileView.fxml";
        }else{
            view = "profileViewModerator.fxml";
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            root = loader.load();

            ProfileController profileController = loader.getController();

            if(currUser.getRole().equals("regular user")) {
                profileController.loadData(currUser.getUsername(), currUser.getEmail(), currUser.getPoints(), currUser.getBadBehaviour());
            }else{
                profileController.loadData(currUser.getUsername(), currUser.getEmail());
            }


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void doNewQuestion(ActionEvent event) {

        nextView = "DoNewQuestion";
        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();

        //first I check if the user is logged
        if (!currUser.isLogged()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                root = loader.load();

                LoginController loginController = loader.getController();
                loginController.setNextView(nextView);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("questionForm.fxml"));
                root = loader.load();


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
