package main.java.client.ui;

import main.java.client.listeners.ClientOrderListener;
import main.java.component.customJPanel.JPanelItemDisplay;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ClientFrameOrder extends JFrame{

    private int appWidth = 800;
    private int appHeight = 600;

    private JPanel mainPanel;
    private JList listOrder;
    private JPanel userPanel;
    private JPanel userItemList;
    private JPanel userInfoList;
    private JPanel controlArea;
    private JButton backButton;
    private JPanel userArea;
    private JPanel userWrapper;
    private JPanel locationWrapper;
    private ClientOrderListener col;

    public ClientFrameOrder(ClientOrderListener col)
    {
        this.col = col;
        this.setContentPane(mainPanel);
        assignListeners();
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

    private void assignListeners()
    {
        backButton.addActionListener(this.col);
    }

    private void createUIComponents() {

        DefaultListModel listModel = new DefaultListModel();
        this.listOrder = new JList(listModel);
        backButton = new JButton("Back");

        // Add list
        this.userItemList = new JPanel(new FlowLayout(FlowLayout.LEFT));
        URL url = this.getClass().getResource("../../../resources/images/orange_pic.png");

        for(int i = 0; i < 10; i++)
        {
            this.userItemList.add(new JPanelItemDisplay(url,120,120, i+""));
        }
    }


}
