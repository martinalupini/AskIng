module it.uniroma2.dicii.ispw.progetto.lupini.view.interfacce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens it.uniroma2.dicii.ispw.progetto.lupini.view to javafx.fxml;
    exports it.uniroma2.dicii.ispw.progetto.lupini.view;
}