package main.java.component.customJButton;

import javax.swing.*;
import java.awt.*;

public class JButtonColor extends JButton {

    private Color bgColor = Color.WHITE;
    private Color txtColor = Color.BLACK;

    public JButtonColor(){
        super();
    }

    public JButtonColor(String text, Color bgColor, Color textColor)
    {
        this.bgColor = bgColor;
        this.txtColor = textColor;
        this.setOpaque(true);
        this.setContentAreaFilled(true);
        this.setBorderPainted(false);
        this.setBackground(bgColor);
        this.setForeground(textColor);
    }
}
