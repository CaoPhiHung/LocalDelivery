package main.java.client.ui;

import main.java.client.listeners.ClientAppListener;
import main.java.component.JPanelItem;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ClientFrameApp extends JFrame{

    private int appWidth = 800;
    private int appHeight = 600;

    private JPanel mainPanel;
    private JPanel shoppingArea;
    private JPanel buttonArea;
    private JPanel buttonWrapper;
    private JButton backBtn;
    private JButton checkoutButton;
    private JPanel quantityWrapper;
    private JLabel quantityLabel;
    private JLabel quantityValue;
    private JPanel InfoArea;
    private JPanel locationArea;
    private JPanel userArea;
    private JPanel userInfoPanel;
    private JPanel userWrapper;
    private JPanel locationWrapper;
    private JTextField latitudeBox;
    private JTextField longitudeBox;

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
        shoppingArea = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backBtn = new JButton();
        URL url = this.getClass().getResource("../../../resources/images/orange_pic.png");

        for(int i = 0; i < 10; i++)
        {

            shoppingArea.add(new JPanelItem(url,120,120));
        }
    }

    public void assignListeners()
    {
        backBtn.addActionListener(this.cal);
    }
}
