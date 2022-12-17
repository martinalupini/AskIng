package it.uniroma2.dicii.ispw.progetto.lupini.view.interfacce;

import it.uniroma2.dicii.ispw.progetto.lupini.view.MenuItemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clickLogin(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {
        MenuItemController mc = new MenuItemController();
        mc.exit(event);

    }

    @FXML
    void goToForum(ActionEvent event){
        MenuItemController mc = new MenuItemController();
        mc.goToForum(event);
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
}

