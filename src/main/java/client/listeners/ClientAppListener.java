package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.component.customJPanel.JPanelItemControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientAppListener implements ActionListener {

    JFrame jf;

    public ClientAppListener(JFrame jf)
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
            }else if(clickedBtn.getText().equals("Checkout"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                ArrayList<JPanelItemControl> arrControl = cfm.getClientFrameApp().getListItemDisplay();
                for(int i = 0; i < arrControl.size(); i++)
                {
                    if(arrControl.get(i).getJcbox().isSelected())
                    {
                        System.out.println("Selected: " + arrControl.get(i).getGoods().displayGoods());
                    }
                }
            }
        }
    }
}
