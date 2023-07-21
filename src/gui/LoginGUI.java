package src.gui;

import src.repositories.UsersRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    private Font font = new Font("Courier", Font.PLAIN, 12);

    private JLabel userLabel = new JLabel("Usuário: ");
    private JLabel pinLabel = new JLabel("PIN: ");

    private JTextField userField = new JTextField();
    private JTextField pinField = new JTextField();
    
    private JButton enterButton = new JButton("Acessar");

    public LoginGUI() {
        setTitle("Realizar Login");
        setSize(400, 200);

        Container container = this.getContentPane();
        container.setLayout(null);

        userLabel.setFont(font);
        pinLabel.setFont(font);

        userField.setFont(font);
        pinField.setFont(font);

        enterButton.setFont(font);

        userLabel.setBounds(20, 20, 80, 40);
        pinLabel.setBounds(20, 70, 80, 40);

        userField.setBounds(90, 20, 290, 40);
        pinField.setBounds(90, 70, 290, 40);

        enterButton.setBounds(20, 120, 360, 40);

        enterButton.addActionListener(this);

        container.add(userLabel);
        container.add(userField);
        container.add(pinLabel);
        container.add(pinField);
        container.add(enterButton);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.enterButton) {
            boolean userValid = UsersRepository.checkUserAndPin(this.userField.getText(), this.pinField.getText());

            if (userValid) {
                TicketsGUI ticketsGUI = new TicketsGUI();
                this.setVisible(false);
                ticketsGUI.setVisible(true);
            }
        }
    }
}
