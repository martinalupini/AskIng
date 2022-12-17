package it.uniroma2.dicii.ispw.progetto.lupini.view;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.ProfileBean;
import it.uniroma2.dicii.ispw.progetto.lupini.bean.RequestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewModController extends EmptyScreen{

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



}

