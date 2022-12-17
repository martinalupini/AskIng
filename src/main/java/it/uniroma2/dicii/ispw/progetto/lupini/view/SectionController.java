package it.uniroma2.dicii.ispw.progetto.lupini.view;

import it.uniroma2.dicii.ispw.progetto.lupini.bean.QuestionBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SectionController implements Initializable {

    @FXML
    private ListView<?> questionList;

    @FXML
    private VBox questionLayout;

    @FXML
    void clickNewQuestion(ActionEvent event) {

    }

    @FXML
    void clickSearch(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void goToHomepage(ActionEvent event) {

    }

    @FXML
    void goToProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<QuestionBean> questions = new ArrayList<>(questions());
        for(int i=0; i<questions.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("questionItem.fxml"));

            try{
                VBox vbox = fxmlLoader.load();
                QuestionItemController questContr = fxmlLoader.getController();
                questContr.setQuestion( questions.get(i));
                questionLayout.getChildren().add(vbox);

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private List<QuestionBean> questions() {
        List<QuestionBean> rs = new ArrayList<>();
        QuestionBean r = new QuestionBean("user1", "ciao tutto ok");
        rs.add(r);

        QuestionBean r1 = new QuestionBean("user2", "ciaoooooooooooooooooooooooooooooooooooooo");
        rs.add(r1);

        QuestionBean r2 = new QuestionBean("user3", "ewhyfuw2yu3fbt3r2qrvt34y");
        rs.add(r2);

        QuestionBean r3 = new QuestionBean("user4", "ewhyfuw2yufdbgiectvyr gyrghvuerhfuiebncfjewbfjewhfnuiewfhiehfieryu3fbt3r2qrvt34y");
        rs.add(r3);

        return rs;

    }
}

