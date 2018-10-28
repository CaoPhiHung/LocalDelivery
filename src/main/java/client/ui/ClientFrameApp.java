package main.java.client.ui;

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

    public ClientFrameApp()
    {
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
        // TODO: place custom component creation code here
        shoppingArea = new JPanel();
        for(int i = 0; i < 20; i++)
        {
            shoppingArea.add(new JButton("test"));
        }

    }
}
