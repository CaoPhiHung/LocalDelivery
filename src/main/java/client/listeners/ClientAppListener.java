package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.client.ui.ClientFrameApp;
import main.java.component.customJPanel.JPanelItemControl;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.service.OrderDetailService;
import main.java.service.OrderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
                System.out.println("Checking out");
                ClientFrameMain cfm = (ClientFrameMain)jf;
                ClientFrameApp cfa = cfm.getClientFrameApp();

                OrderService os = new OrderService();

                ArrayList<JPanelItemControl> arrControl = cfm.getClientFrameApp().getListItemDisplay();
                ArrayList<OrderDetail> tempOrdered = new ArrayList<>();

                double totalPrice = 0.0;
                int latitude = Integer.parseInt(cfa.getLatitudeBox().getText());
                int longitude = Integer.parseInt(cfa.getLongitudeBox().getText());
                String latLong = latitude + "-" + longitude;

                for(int i = 0; i < arrControl.size(); i++)
                {
                    if(arrControl.get(i).getJcbox().isSelected())
                    {
                        Goods curGoods = arrControl.get(i).getGoods();

                        int quantity = (int)arrControl.get(i).getJtf().getValue();
                        double totalCur = curGoods.getPrice() * quantity;
                        totalPrice += totalCur;
                        OrderDetail newOrderDetail = new OrderDetail(0,0,curGoods.getGoodsId(),
                                quantity);
                        System.out.println("Order detail: " + curGoods.getName() + " - Q: " + quantity);

                        tempOrdered.add(newOrderDetail);
                    }
                }

                Order newOrder = new Order(0,totalPrice,latLong);
                System.out.println("Total price: " + totalPrice + " latlong: " + latLong);

                try {
                    os.createNewOrder(newOrder, tempOrdered);
                }catch(IOException ioe)
                {
                    System.out.println("Cannot create new order");
                }

            }
        }
    }
}
