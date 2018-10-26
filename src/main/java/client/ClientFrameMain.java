package main.java.client;

import main.java.client.ui.ClientFrameLogin;

import javax.swing.*;
import java.awt.*;

public class ClientFrameMain extends JFrame {

    public ClientFrameMain()
    {
        displayLoginFrame();
    }

    private void displayLoginFrame()
    {
        ClientFrameLogin clientFrameLogin = new ClientFrameLogin();

        //Login Frame by default
        this.setContentPane(clientFrameLogin.getContentPane());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300,200));
        this.setTitle("Client Local Delivery");
        this.setLocationRelativeTo(null); // Center
//        this.pack();
        this.setVisible(true);
    }
}
