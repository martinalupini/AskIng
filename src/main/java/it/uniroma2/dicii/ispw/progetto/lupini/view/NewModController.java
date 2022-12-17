package it.uniroma2.dicii.ispw.progetto.lupini.view;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewModController {

    @FXML
    private TextField requestInserted;

    @FXML
    public void clickSubmit(ActionEvent event) {

        ProfileBean profile= ProfileBean.getProfileInstance("Mario", "mario@boh.com");
        String user = profile.getUsername();
        String text;
        text = requestInserted.getText();
        RequestBean request = new RequestBean(user);
        request.setText(text);

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

