package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.model.*;
import main.java.service.OrderDetailService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientOrderListener implements ActionListener, ListSelectionListener {

    JFrame jf;

    public ClientOrderListener(JFrame jf)
    {
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("Return to menu"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(ClientFrameMain.MENU_SCENE); // Change to Menu
            }
        }
    }

    /**
     * Trigger by Clicking on JList
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        if(e.getSource() instanceof JList && !e.getValueIsAdjusting()) // Make sure only trigger once
        {
            ClientFrameMain cfm = (ClientFrameMain)jf;
            JList jl = (JList)e.getSource();

            //Get index and update items
            int ind = jl.getSelectedIndex();
            cfm.getClientFrameOrder().updateDetailsItem(ind);
        }

    }
}
