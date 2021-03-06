package main.java.component.customJPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JPanelWithImage extends JPanel  {

    protected URL url;

    protected JPanelBackground jpPic;

    public JPanelWithImage(URL url, int w, int h)
    {
        this.url = url;
        jpPic = new JPanelBackground(url);

        //Set background item
        this.jpPic.setOpaque(true);
        this.jpPic.setBackground(new Color(246,241,244));

        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setPreferredSize(new Dimension(w,h));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout(0,0));

        this.add(jpPic,BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
