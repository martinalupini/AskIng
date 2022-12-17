module it.uniroma2.dicii.ispw.progetto.lupini.view.interfacce {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.uniroma2.dicii.ispw.progetto.lupini.view to javafx.fxml;
    exports it.uniroma2.dicii.ispw.progetto.lupini.view;
    exports it.uniroma2.dicii.ispw.progetto.lupini.view.interfacce;
    opens it.uniroma2.dicii.ispw.progetto.lupini.view.interfacce to javafx.fxml;
}