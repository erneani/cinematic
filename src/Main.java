package src;

import src.cli.LoginCLI;
import src.gui.LoginGUI;
import src.gui.TicketsGUI;

public class Main {
    public static void main(String[] args) {
        Database.createTables();
        TicketsGUI ticketsGUI = new TicketsGUI();

        ticketsGUI.setVisible(true);
    }
}
