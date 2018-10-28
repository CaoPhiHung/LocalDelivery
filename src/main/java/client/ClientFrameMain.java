package main.java.client;

import main.java.client.listeners.ClientLoginListener;
import main.java.client.ui.ClientFrameApp;
import main.java.client.ui.ClientFrameLogin;

import javax.swing.*;
import java.awt.*;

public class ClientFrameMain extends JFrame {

    /*
        scene:
            0 - Login
            1 - Main
     */
    private int scene = 0;

    public static int LOGIN_SCENE = 0;
    public static int MAIN_SCENE = 1;

    //Frame Login
    ClientFrameLogin clientFrameLogin = new ClientFrameLogin();
    ClientLoginListener cll = new ClientLoginListener(this);

    //Frame app
    ClientFrameApp clientFrameApp;

    public ClientFrameMain()
    {
        clientFrameLogin.setListener(cll);
        setScene(LOGIN_SCENE); // Login scene by default

        this.setTitle("Client Local Delivery");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(clientFrameLogin.getAppWidth(),clientFrameLogin.getAppHeight()));
        this.setLocationRelativeTo(null); // Center app
        this.setVisible(true);
    }

    public void setScene(int scene)
    {
        if(scene == LOGIN_SCENE)
        {
            this.setContentPane(clientFrameLogin.getContentPane());
        }else if(scene == MAIN_SCENE)
        {
            clientFrameApp = new ClientFrameApp();
            this.setVisible(false);
            this.setSize(new Dimension(clientFrameApp.getAppWidth(), clientFrameApp.getAppHeight()));
            this.setLocationRelativeTo(null); // Center app
            this.setContentPane(clientFrameApp.getContentPane());
            this.setVisible(true);
        }

        this.repaint(); // Repaint to refresh screen
    }
}
