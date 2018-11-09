package main.java.client;

import main.java.client.listeners.ClientAppListener;
import main.java.client.listeners.ClientLoginListener;
import main.java.client.listeners.ClientMenuListener;
import main.java.client.listeners.ClientOrderListener;
import main.java.client.ui.ClientFrameApp;
import main.java.client.ui.ClientFrameLogin;
import main.java.client.ui.ClientFrameMenu;
import main.java.client.ui.ClientFrameOrder;

import javax.swing.*;
import java.awt.*;

public class ClientFrameMain extends JFrame {

    /*
        scene:
            0 - Login
            1 - Menu
            2- Main
     */
    private int scene = 0;

    public static int LOGIN_SCENE = 0;
    public static int MENU_SCENE = 1;
    public static int MAIN_SCENE = 2;
    public static int ORDER_SCENE = 3;

    //Frame Login
    ClientLoginListener cll = new ClientLoginListener(this);
    ClientFrameLogin clientFrameLogin;

    //Frame app
    ClientAppListener cal = new ClientAppListener(this);
    ClientFrameApp clientFrameApp;

    //Frame menu
    ClientMenuListener cml = new ClientMenuListener(this);
    ClientFrameMenu clientFrameMenu;

    //Frame order
    ClientOrderListener col = new ClientOrderListener(this);
    ClientFrameOrder clientFrameOrder;



    public ClientFrameMain()
    {
        clientFrameMenu = new ClientFrameMenu(cml);
        clientFrameLogin = new ClientFrameLogin(cll);
        clientFrameApp = new ClientFrameApp(cal);
        clientFrameOrder = new ClientFrameOrder(col);

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
            this.setSize(new Dimension(clientFrameLogin.getAppWidth(), clientFrameLogin.getAppHeight()));
            this.setLocationRelativeTo(null); // Center app
            this.setContentPane(clientFrameLogin.getContentPane());
        }else if(scene == MENU_SCENE)
        {
            this.setSize(new Dimension(clientFrameMenu.getAppWidth(), clientFrameMenu.getAppHeight()));
            this.setLocationRelativeTo(null); // Center app
            this.setContentPane(clientFrameMenu.getContentPane());
        }else if(scene == MAIN_SCENE)
        {
            this.setSize(new Dimension(clientFrameApp.getAppWidth(), clientFrameApp.getAppHeight()));
            this.setLocationRelativeTo(null); // Center app
            this.setContentPane(clientFrameApp.getContentPane());
        }else if(scene == ORDER_SCENE)
        {
            this.setSize(new Dimension(clientFrameOrder.getAppWidth(), clientFrameOrder.getAppHeight()));
            this.setLocationRelativeTo(null); // Center app
            this.setContentPane(clientFrameOrder.getContentPane());
        }
        this.setVisible(true);
//        this.repaint(); // Repaint to refresh screen
    }


}
