package main.java.client.ui;

import main.java.client.listeners.ClientMenuListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrameMenu extends JFrame{
    private int appWidth = 800;
    private int appHeight = 600;

    private JPanel mainPanel;
    private JButton viewOrdersButton;
    private JButton orderGoodsButton;
    private JButton logOutButton;

    private ClientMenuListener cml;

    public ClientFrameMenu(ClientMenuListener cml)
    {
        this.cml = cml;
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

        orderGoodsButton = new JButton();
        logOutButton = new JButton();

    }

    public void assignListeners()
    {
        orderGoodsButton.addActionListener(this.cml);
        logOutButton.addActionListener(this.cml);
    }
}
