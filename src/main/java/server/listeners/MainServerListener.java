package main.java.server.listeners;

import main.java.client.ClientFrameMain;
import main.java.model.*;
import main.java.server.ServerFrameMain;
import main.java.server.map.Maze;
import main.java.service.FileHandlingService;
import main.java.service.GoodsService;
import main.java.service.OrderDetailService;
import main.java.service.OrderService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainServerListener implements ActionListener, ListSelectionListener {

    JFrame jf;

    public MainServerListener(JFrame jf){this.jf = jf;}

    @Override
    public void actionPerformed(ActionEvent e) {

        ServerFrameMain sfm = (ServerFrameMain)jf;

        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("Exit"))
            {
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
                Order curOrder = sfm.getSfa().getCurOrder();
                String[] locArr = curOrder.destination.split("-");

                ArrayList<String> arrLoc = new ArrayList<>();
                arrLoc.add(locArr[0]);
                arrLoc.add(locArr[1]);
                Maze.setTargetFromAL(arrLoc);
                Maze.solveMaze(Maze.alpPoints);
            }else if(clickedBtn.getText().equals("Refresh"))
            {
                FileHandlingService fh = new FileHandlingService();
                fh.setReadType(0);
                fh.readFile("User.txt");

                //Clear ALL
                sfm.getSfa().userCombo.removeAllItems();
                sfm.getSfa().userCombo.addItem("Select user");
                sfm.getSfa().getUserList().clear();
                sfm.getSfa().getUserList().add(null);
                sfm.getSfa().userCombo.setSelectedIndex(0);

                GenericNode<User> nodeUser = fh.user_list.getHead();
                while (nodeUser != null) {
                    sfm.getSfa().userCombo.addItem(nodeUser.data.fullname);
                    sfm.getSfa().getUserList().add(nodeUser.data);
                    nodeUser = nodeUser.next;
                }

                ((DefaultListModel)sfm.getSfa().listOrder.getModel()).removeAllElements();
                sfm.getModel().getOrderIdList().clear();
//                sfm.getSfa().
                sfm.getSfa().detailsPanel.removeAll();
                sfm.getSfa().detailsPanel.updateUI();

                sfm.getSfa().locationValue.setText("N/A");

            }else if(clickedBtn.getText().equals("Clear"))
            {
                sfm.getSfa().listOrder.clearSelection();
                sfm.getSfa().detailsPanel.removeAll();
                sfm.getSfa().detailsPanel.updateUI();
                sfm.getSfa().locationValue.setText("N/A");
            }
        }else if(e.getSource() instanceof JComboBox)
        {
            JComboBox cbox = (JComboBox)e.getSource();
            int selectedIndex = cbox.getSelectedIndex();

            if(selectedIndex > 0)
            {
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

    @Override
    public void valueChanged(ListSelectionEvent e) {

        if(e.getSource() instanceof JList && !e.getValueIsAdjusting())
        {
            System.out.println("Clicked");
            ServerFrameMain sfm = (ServerFrameMain)jf;
            JList jl = (JList)e.getSource();
            int ind = jl.getSelectedIndex();
            sfm.getSfa().updateDetailsItem(ind);
        }
    }
}
