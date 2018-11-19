package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.component.customJPanel.JPanelItemControl;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.service.OrderDetailService;

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
                OrderDetailService ods = new OrderDetailService();

                ArrayList<JPanelItemControl> arrControl = cfm.getClientFrameApp().getListItemDisplay();
                ArrayList<OrderDetail> tempOrdered = new ArrayList<>();

//                Order newOrder = new Order();
                for(int i = 0; i < arrControl.size(); i++)
                {
                    if(arrControl.get(i).getJcbox().isSelected())
                    {
                        Goods curGoods = arrControl.get(i).getGoods();
//                        OrderDetail newOrderDetail = new OrderDetail();
                        System.out.println("Selected: " + arrControl.get(i).getGoods().displayGoods());
                    }
                }
            }
        }
    }
}
