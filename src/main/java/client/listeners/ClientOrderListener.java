package main.java.client.listeners;

import main.java.client.ClientFrameMain;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            if(clickedBtn.getText().equals("Back"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(ClientFrameMain.MENU_SCENE); // Change to Menu
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if(e.getSource() instanceof JList && !e.getValueIsAdjusting())
        {
            JList jl = (JList)e.getSource();
            int ind = jl.getSelectedIndex();
            System.out.println("Index: " + jl.getModel().getElementAt(ind));
        }

    }
}
