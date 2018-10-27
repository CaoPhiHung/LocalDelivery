package main.java.client.listeners;

import main.java.client.ClientFrameMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientLoginListener implements ActionListener {

    private JFrame jf;
    public ClientLoginListener(JFrame jf)
    {
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("Login"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(1); // Change to main
            }
        }

    }

}
