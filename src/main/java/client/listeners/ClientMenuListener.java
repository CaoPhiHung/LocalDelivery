package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.model.User;
import main.java.service.GoodsService;
import main.java.service.OrderDetailService;
import main.java.service.OrderService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

            if(clickedBtn.getText().equals("Order goods")) {
                ClientFrameMain cfm = (ClientFrameMain) jf;
                cfm.setScene(ClientFrameMain.MAIN_SCENE); // Change to main scene
            }else if(clickedBtn.getText().equals("View Orders"))
            {
                ClientFrameMain cfm = (ClientFrameMain) jf;
                cfm.setScene(ClientFrameMain.ORDER_SCENE); // Change to order scene

                OrderService os = new OrderService();
                OrderDetailService ods = new OrderDetailService();
                GoodsService gs = new GoodsService();
                User user = cfm.getLoginUser();

                try {

                    // View orders
                    cfm.setOrderList(os.getAllOrderList(user.userId));
                    cfm.setOrderListDetail(ods.getAllOrderDetailList(1));

                    // Order goods
                    cfm.setGoodsList(gs.getAllGoodsList());

                    System.out.println(cfm.getOrderList().size());
                    System.out.println(cfm.getOrderListDetail().size());
                    System.out.println(cfm.getGoodsList().size());

                }catch(IOException ioe)
                {
                    System.out.println("IOException in ClientMenuListener, in View Orders button.");
                }


            }else if(clickedBtn.getText().equals("Log out"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                cfm.setScene(ClientFrameMain.LOGIN_SCENE); // Change to login scene
            }
        }
    }

}
