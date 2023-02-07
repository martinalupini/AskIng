package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.CurrentUserProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_applicativo.LogoutControllerAppl;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.ImpossibleStartGUI;
import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.UserNotLogged;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmptyScreen{

    private static final String ERROR_GUI = "Error on starting the GUI";

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
            throw new ImpossibleStartGUI( ERROR_GUI);
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
            throw new ImpossibleStartGUI(ERROR_GUI);
        }

    }

    @FXML
    public void goToProfile(ActionEvent event) {
        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();
        String view;
        nextView = "profileView";

        //first I check if the user is logged
        if(!currUser.isLogged()){
            UserNotLogged userNotLogged = new UserNotLogged();
            userNotLogged.userNotLogged(nextView, event);
            return;
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
            throw new ImpossibleStartGUI(ERROR_GUI);
        }
    }






    @FXML
    void clickBecomeModerator(ActionEvent event) {

        CurrentUserProfileBean currUser = CurrentUserProfileBean.getProfileInstance();
        String view = "doNewModeratorRequest.fxml";
        nextView = "DoNewModeratorRequest";

        //first I check if the user is logged
        if(!currUser.isLogged()){
            UserNotLogged userNotLogged = new UserNotLogged();
            userNotLogged.userNotLogged(nextView, event);
            return;
        }

        //if the user is a moderator they cannot do the request
        if(currUser.getRole().equals("moderator")){
            goToHomepage(event);
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
            root = loader.load();

            DoNewRequestController doNewRequestController = loader.getController();
            doNewRequestController.displayUserData(currUser.getUsername(), currUser.getEmail(), currUser.getPoints(), currUser.getBadBehaviour());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            throw new ImpossibleStartGUI(ERROR_GUI);
        }
    }

    @FXML
    public void exit(ActionEvent event){

        if(!CurrentUserProfileBean.getProfileInstance().isLogged())  return;

        LogoutControllerAppl logoutControllerAppl =new LogoutControllerAppl();
        logoutControllerAppl.exit();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            root = loader.load();

            HomepageController homepageController = loader.getController();
            homepageController.setLogoutLabel("logout effettuato con successo!");

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            throw new ImpossibleStartGUI(ERROR_GUI);
        }
    }

}
