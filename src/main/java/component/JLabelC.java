package main.java.component;

import javax.swing.*;
import java.awt.*;

public class JLabelC extends JLabel {

    public JLabelC(String txt)
    {
        super(txt);
        changeStyle();
    }

    private void changeStyle()
    {
        this.setForeground(Color.RED);
    }
}
