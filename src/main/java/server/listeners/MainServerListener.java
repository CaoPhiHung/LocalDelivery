package main.java.server.listeners;

import main.java.client.ClientFrameMain;
import main.java.model.*;
import main.java.server.ServerFrameMain;
<<<<<<< HEAD
//import main.java.server.map.Maze;
import main/java/server/map/Maze.java;
=======
import main.java.server.map.Maze;
import main.java.service.GoodsService;
import main.java.service.OrderDetailService;
import main.java.service.OrderService;
>>>>>>> b5640e4178344fe4f0949f6084d368fb43ae5dac

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
            }else if(clickedBtn.getText().equals("View Map"))
            {
                Maze m = new Maze();
                m.initialize();
                Maze.frame.setVisible(true);
                Maze.frame.setLocationRelativeTo(null);

                //Fake location
                ArrayList<String> arrLoc = new ArrayList<>();
                arrLoc.add("3");
                arrLoc.add("5");
                Maze.setTargetFromAL(arrLoc);
                Maze.solveMaze(Maze.alpPoints);
            }
        }else if(e.getSource() instanceof JComboBox)
        {
            JComboBox cbox = (JComboBox)e.getSource();
            int selectedIndex = cbox.getSelectedIndex();

            if(selectedIndex > 0)
            {
                ServerFrameMain sfm = (ServerFrameMain)jf;

                //Set user
                User selectedUser = sfm.getSfa().getUserList().get(selectedIndex);
                sfm.getModel().setLoginUser(selectedUser);

                //Get all list
                OrderService os = new OrderService();
                OrderDetailService ods = new OrderDetailService();
                getAllGoods();

                try {

                    // View orders
                    sfm.getModel().setOrderList(os.getAllOrderList(selectedUser.userId));

                    sfm.getModel().setOrderListDetail(new GenericDLinkedList<>()); // Not null

                    //Check
                    GenericNode<Order> ord = sfm.getModel().getOrderList().getRoot();
                    while(ord != null)
                    {
                        System.out.println("display: " + ord.data.displayOrder());
                        ord = ord.next;
                    }

                    // Reselected list
                    sfm.getSfa().updateListOrder();
                }catch(IOException ioe)
                {
                    System.out.println("IOException in ClientMenuListener, in View Orders button.");
                }

            }

        }
    }

    private void getAllGoods()
    {
        ServerFrameMain sfm = (ServerFrameMain)jf;
        GoodsService gs = new GoodsService();

        // Order goods
        try {
            sfm.getModel().setGoodsList(gs.getAllGoodsList());
        }catch(IOException ioe)
        {

        }
    }

}
