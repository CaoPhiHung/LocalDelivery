package main.java.client.ui;

import main.java.client.listeners.ClientLoginListener;
import main.java.component.JLabelC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrameLogin extends JFrame{

    private JPanel mainPanel;
    private JButton loginBtn;
    private JLabel titleLabel;
    private JLabel usrLabel;
    private JLabel passLabel;
    private JPanel loginPanel;
    private JPanel titlePanel;
    private JPanel userWrapper;
    private JPanel passWrapper;
    private JTextField username;
    private JPasswordField password;
    private JPanel userLineWrapper;

    ClientLoginListener cll;

    /**
     * Constructor
     */
    public ClientFrameLogin()
    {
        this.setContentPane(mainPanel);
    }

    public void setListener(ClientLoginListener cll)
    {
        this.cll = cll;
        assignListeners();
    }

    /**
     * Custom UI components when creating form
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        titleLabel = new JLabelC("Local Delivery");
        titleLabel.setSize(new Dimension(200,300));
    }

    private void assignListeners()
    {
        loginBtn.addActionListener(cll);
    }
}
