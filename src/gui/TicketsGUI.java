package src.gui;

import src.gui.innerGui.ListCinemaGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketsGUI extends JFrame implements ActionListener {

    JDesktopPane panel = new JDesktopPane();

    ListCinemaGUI listCinemaGUI = new ListCinemaGUI("");


    JMenuItem listCinemasItem = new JMenuItem("Listar todos os cinemas.");
    JMenuItem listSessionsItem = new JMenuItem("Listar todas as sessões de um cinema.");
    JMenuItem buyTicketItem = new JMenuItem("Comprar ingresso para uma sessão.");

    public TicketsGUI() {
        setSize(800, 600);
        setResizable(false);
        setTitle("Tela de usuário e compra de ingressos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenu();

        Container container = this.getContentPane();

        panel.add(listCinemaGUI);

        container.add(BorderLayout.CENTER, panel);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == listCinemasItem) {
            listCinemaGUI.setVisible(true);
        }
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu ticketsMenu = new JMenu("Tickets");
        menuBar.add(ticketsMenu);

        ticketsMenu.add(listCinemasItem);
        ticketsMenu.add(listSessionsItem);
        ticketsMenu.add(buyTicketItem);

        listCinemasItem.addActionListener(this);
        listSessionsItem.addActionListener(this);
        buyTicketItem.addActionListener(this);
    }
}
