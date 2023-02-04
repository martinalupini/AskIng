package it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Notification {

    public void notify(String destination, String message){
        File notification = new File("src/main/resources/notifications/Notification_for_"+destination+".txt");
        try {
            notification.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(notification));
        writer.write(message);
        writer.close();
        } catch (IOException e) {
            //It's not a big deal if the notification can't be sent so we just catch the exception
        }

    }
}
