package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.controller_grafico.engineering.Notification;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;

public class TestNotification {
    @Test
    public void testNotify(){
        Notification notification = new Notification();
        notification.notify("marco", "ok");
        assertThat("non vuoto", notification!=null);
    }
}
