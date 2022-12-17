package it.uniroma2.dicii.ispw.progetto.lupini.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ProfileController {

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


    }

