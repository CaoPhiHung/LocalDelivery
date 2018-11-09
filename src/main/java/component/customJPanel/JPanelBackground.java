/*
    Author: Tan Thinh Ngo
 */

package main.java.component.customJPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class JPanelBackground extends JPanel {

    private Image bgImage;
    private Image scaled;
    private float alpha = 0.5f;

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
//        Graphics2D g2 = (Graphics2D) g;
//        AlphaComposite alcom = AlphaComposite.getInstance(
//                AlphaComposite.SRC_OVER, alpha);
//        g2.dra
//        g2.setComposite(alcom);

        if(scaled != null && scaled.getWidth(this) > 0)
        {
            g.drawImage(scaled, 0, 0, this);
        }else
        {
//            g.setColor(Color.BLUE);
            g.fillRect(0,0,getWidth(),getHeight());
        }

    }
}
