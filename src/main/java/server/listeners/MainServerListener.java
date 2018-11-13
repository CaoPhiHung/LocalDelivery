package main.java.server.listeners;

import main.java.server.ServerFrameMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainServerListener implements ActionListener {

    JFrame jf;

    public MainServerListener(JFrame jf){this.jf = jf;}

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("Exit"))
            {
                ServerFrameMain sfm = (ServerFrameMain)jf;
                sfm.setVisible(false);
                sfm.dispose();
                System.exit(0);
            }
        }
    }

}
