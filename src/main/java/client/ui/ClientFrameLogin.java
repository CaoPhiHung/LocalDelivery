package main.java.client.ui;

import main.java.client.listeners.ClientLoginListener;
import main.java.component.JLabelC;
import main.java.component.JPanelBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ClientFrameLogin extends JFrame{

    private int appWidth = 500;
    private int appHeight = 300;

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
        String path = System.getProperty("user.dir") + "/src/main/resources/images/background.jpg";
        URL url = this.getClass().getResource("../../../resources/images/background.jpg");
        mainPanel = new JPanelBackground(url);

        titleLabel = new JLabelC("LOCAL DELIVERY");
        titleLabel.setSize(new Dimension(200,300));
    }

    private void assignListeners()
    {
        loginBtn.addActionListener(cll);
    }

    public int getAppWidth() {
        return appWidth;
    }

    public void setAppWidth(int appWidth) {
        this.appWidth = appWidth;
    }

    public int getAppHeight() {
        return appHeight;
    }

    public void setAppHeight(int appHeight) {
        this.appHeight = appHeight;
    }
}
