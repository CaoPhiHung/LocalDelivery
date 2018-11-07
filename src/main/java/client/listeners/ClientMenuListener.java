package main.java.client.listeners;

import main.java.client.ClientFrameMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenuListener implements ActionListener {

    private JFrame jf;
    public ClientMenuListener(JFrame jf)
    {
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("Order goods"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(ClientFrameMain.MAIN_SCENE); // Change to main
            }else if(clickedBtn.getText().equals("Log out"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(ClientFrameMain.LOGIN_SCENE); // Change to main
            }
        }
    }

}
