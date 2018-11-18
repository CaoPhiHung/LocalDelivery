package main.java.component.customJPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JPanelItemDisplay extends JPanelWithImage {

    private JLabel itemTitle;
    private JLabel itemDisplay;
//    private JPanel wrapperTitle;
    private JPanel wrapper;

    public JPanelItemDisplay(URL url, int w, int h,String title, String quantity)
    {
        super(url,w,h);

        //Set background item
        this.jpPic.setOpaque(true);
        this.jpPic.setBackground(new Color(246,241,244));

        //Display title
        itemTitle = new JLabel(title);
        itemTitle.setOpaque(false);
//        itemTitle.setPreferredSize(new Dimension(120,20));
        itemTitle.setHorizontalAlignment(JLabel.CENTER);
        itemTitle.setVerticalAlignment(JLabel.TOP);
        itemTitle.setFont(new Font("Courier", Font.BOLD,14));

        //Display quantity
        itemDisplay = new JLabel("Quantity: " + quantity);
        itemDisplay.setOpaque(false);
        itemDisplay.setHorizontalAlignment(JLabel.CENTER);
        itemDisplay.setVerticalAlignment(JLabel.TOP);
        itemDisplay.setVerticalTextPosition(JLabel.TOP);
        itemDisplay.setBorder(BorderFactory.createEmptyBorder( 0 /*top*/, 0, 3, 0 ));

        wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setPreferredSize(new Dimension(120,40));
        wrapper.setAlignmentY(TOP_ALIGNMENT);

        //Add to main Panel
        wrapper.add(itemTitle, BorderLayout.NORTH);
        wrapper.add(itemDisplay, BorderLayout.SOUTH);

//        this.add(wrapperTitle, BorderLayout.NORTH);
        this.add(wrapper,BorderLayout.SOUTH);
    }

    public JPanelItemDisplay(URL url, int w, int h, String title, String quantity, Color bg)
    {
        this(url,w,h,title,quantity);
        this.setOpaque(true);
        this.setBackground(bg);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
