package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.ResponseBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewQuestionController extends EmptyScreen implements Initializable {

    @FXML
    private VBox responseLayout;

    @FXML
    public void replyToQuestion(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ResponseBean> responses = new ArrayList<>(responses());
        for(int i=0; i<responses.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("responseItem.fxml"));

            try{
                VBox vbox = fxmlLoader.load();
                ResponseItemController resContr = fxmlLoader.getController();
                resContr.setResponse( responses.get(i));
                responseLayout.getChildren().add(vbox);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private List<ResponseBean> responses() {
        List<ResponseBean> rs = new ArrayList<>();
        ResponseBean r = new ResponseBean("user1", "ciao tutto ok");
        rs.add(r);

        ResponseBean r1 = new ResponseBean("user2", "ciaoooooooooooooooooooooooooooooooooooooo");
        rs.add(r1);

        ResponseBean r2 = new ResponseBean("user3", "ewhyfuw2yu3fbt3r2qrvt34y");
        rs.add(r2);

        ResponseBean r3 = new ResponseBean("user4", "ewhyfuw2yufdbgiectvyr gyrghvuerhfuiebncfjewbfjewhfnuiewfhiehfieryu3fbt3r2qrvt34y");
        rs.add(r3);

        return rs;

    }
}
