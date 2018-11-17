package main.java.component.customJPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JPanelItemDisplay extends JPanelWithImage {

    private JLabel itemDisplay;
    private JPanel wrapper;

    public JPanelItemDisplay(URL url, int w, int h, String quantity)
    {
        super(url,w,h);

        itemDisplay = new JLabel(quantity);
        wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        wrapper.add(itemDisplay);
        this.add(wrapper,BorderLayout.SOUTH);
    }

    public JPanelItemDisplay(URL url, int w, int h, String quantity, Color bg)
    {
        this(url,w,h,quantity);
        this.setOpaque(true);
        this.setBackground(bg);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
