package it.uniroma2.dicii.ispw.progetto.lupini;


import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.Homepage;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.HomepageView;
import it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering.Print;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args){

        Scanner reader = new Scanner(System.in);
        int selection;
        Print.print("SELEZIONARE LA MODALITA' DI VISUALIZZAZIONE DELL'INTERFACCIA\n\n1) interfaccia grafica\n2) da linea di comando\n\nPer selezionare inserire il numero corrispondente: ");


        while(true){
            selection = reader.nextInt();
            if(selection==1) {
                launch();
                break;
            }else if(selection ==2){
                HomepageView homepageView = new HomepageView();
                homepageView.displayHomepage();
            }else{
                Print.print("Inserire il  numero 1 o 2 a seconda dell'interfaccia scelta: ");
            }

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Homepage.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),335, 650);
        stage.setScene(scene);
        stage.show();
    }
}
