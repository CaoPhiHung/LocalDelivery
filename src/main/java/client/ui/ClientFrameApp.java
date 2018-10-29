package main.java.client.ui;

import main.java.client.listeners.ClientAppListener;

import javax.swing.*;
import java.awt.*;

public class ClientFrameApp extends JFrame{

    private int appWidth = 800;
    private int appHeight = 600;

    private JPanel mainPanel;
    private JPanel shoppingArea;
    private JPanel buttonArea;
    private JPanel buttonWrapper;
    private JButton backBtn;
    private JButton checkoutButton;
    private JPanel infoArea;
    private JLabel quantityLabel;

    private ClientAppListener cal;

    public ClientFrameApp(ClientAppListener cal)
    {
        this.cal = cal;
        this.setContentPane(mainPanel);
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

    private void createUIComponents() {

        //Declare
        shoppingArea = new JPanel();
        backBtn = new JButton();

        for(int i = 0; i < 20; i++)
        {
            shoppingArea.add(new JButton("test"));
        }
    }

    public void assignListeners()
    {
        backBtn.addActionListener(this.cal);
    }
}
