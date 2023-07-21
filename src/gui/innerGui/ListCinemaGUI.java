package src.gui.innerGui;


import javax.swing.*;
import java.awt.*;

public class ListCinemaGUI extends JInternalFrame {

    public ListCinemaGUI(String str) {
        super(str, false, true);
        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel cinemaLabel = new JLabel("Fingindo que aqui tem uma lista de cinemas disponíveis");
        cinemaLabel.setBounds(20, 30, 240, 50);

        container.add(cinemaLabel);

        setSize(300, 300);
        setTitle("Lista de cinemas");
    }
}
