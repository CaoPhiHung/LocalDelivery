/*
    Author: Tan Thinh Ngo
 */

package main.java.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.sql.SQLOutput;

public class JPanelBackground extends JPanel {

    private Image bgImage;
    private Image scaled;

    public JPanelBackground(URL path)
    {
        ImageIcon icon = new ImageIcon(path);
        this.bgImage = icon.getImage();
    }


    @Override
    public void invalidate() {
        super.invalidate();
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            scaled = bgImage.getScaledInstance(getWidth(), getHeight(),
                    Image.SCALE_SMOOTH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet() || scaled == null) {
            return super.getPreferredSize();
        }
        int w = scaled.getWidth(this);
        int h = scaled.getHeight(this);
        return new Dimension(w, h);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // It needs to be calculated first
        // When first initiate , no dimension yet
        if(scaled != null && scaled.getWidth(this) > 0)
        {
            g.drawImage(scaled, 0, 0, this);
        }else
        {
            g.setColor(Color.WHITE);
        }

    }
}
