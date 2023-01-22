package it.uniroma2.dicii.ispw.progetto.lupini.second_view.engineering;

public class ClearScreen {

    private ClearScreen(){}

    public static void clearScreen(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
