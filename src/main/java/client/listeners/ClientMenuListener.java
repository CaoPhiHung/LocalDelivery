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
                OrderService os = new OrderService();
                OrderDetailService ods = new OrderDetailService();
                GoodsService gs = new GoodsService();


                try {
                    User user = cfm.getModel().getLoginUser();

                    // View orders
                    cfm.getModel().setOrderList(os.getAllOrderList(user.userId));
                    cfm.getModel().setOrderListDetail(ods.getAllOrderDetailList(1));

                    // Order goods
                    cfm.getModel().setGoodsList(gs.getAllGoodsList());

                    cfm.setScene(ClientFrameMain.ORDER_SCENE); // Change to order scene

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
