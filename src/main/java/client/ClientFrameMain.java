package main.java.client;

import main.java.client.listeners.ClientLoginListener;
import main.java.client.ui.ClientFrameApp;
import main.java.client.ui.ClientFrameLogin;

import javax.swing.*;
import java.awt.*;

public class ClientFrameMain extends JFrame {

    private int appWidth = 500;
    private int appHeight = 300;

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
    ClientFrameApp clientFrameApp = new ClientFrameApp();

    public ClientFrameMain()
    {
        clientFrameLogin.setListener(cll);
        setScene(LOGIN_SCENE); // Login scene by default

        this.setTitle("Client Local Delivery");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(appWidth,appHeight));
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
            this.setContentPane(clientFrameApp.getContentPane());
        }

        this.repaint(); // Repaint to refresh screen
    }
}
