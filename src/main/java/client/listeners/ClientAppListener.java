package main.java.client.listeners;

import main.java.client.ClientFrameMain;
import main.java.client.ui.ClientFrameApp;
import main.java.component.customJPanel.JPanelItemControl;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;
import main.java.service.OrderDetailService;
import main.java.service.OrderService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ClientAppListener implements ActionListener, DocumentListener {

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
                int option = JOptionPane.showConfirmDialog(
                        null, "Are you sure you want to cancel order?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    cfm.setScene(ClientFrameMain.MENU_SCENE); // Change to Menu
                }

            }else if(clickedBtn.getText().equals("Checkout"))
            {
                ClientFrameMain cfm = (ClientFrameMain)jf;
                ClientFrameApp cfa = cfm.getClientFrameApp();

                OrderService os = new OrderService();
                User curUser = cfm.getModel().getLoginUser();

                ArrayList<JPanelItemControl> arrControl = cfm.getClientFrameApp().getListItemDisplay();
                ArrayList<OrderDetail> tempOrdered = new ArrayList<>();

                double totalPrice = 0.0;
                int latitude = Integer.parseInt(cfa.getLatitudeBox().getText());
                int longitude = Integer.parseInt(cfa.getLongitudeBox().getText());
                String latLong = latitude + "-" + longitude;

                boolean checkedQuantity = true;
                int numChosenItems = 0;

                for(int i = 0; i < arrControl.size(); i++)
                {
                    if(arrControl.get(i).getJcbox().isSelected())
                    {
                        numChosenItems++;
                        Goods curGoods = arrControl.get(i).getGoods();

                        int quantity = (int)arrControl.get(i).getJtf().getValue();
                        if(quantity <= 0)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "Please add quantity to good \"" + curGoods.getName() + "\"",
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                            checkedQuantity = false;
                            break;
                        }else
                        {
                            double totalCur = curGoods.getPrice() * quantity;
                            totalPrice += totalCur;
                            OrderDetail newOrderDetail = new OrderDetail(0,0,curGoods.getGoodsId(),
                                    quantity);
                            System.out.println("Order detail: " + curGoods.getName() + " - Q: " + quantity);

                            tempOrdered.add(newOrderDetail);
                        }

                    }
                }

                // Not choosing an item ?? Warning message
                if(numChosenItems == 0)
                {
                    JOptionPane.showMessageDialog(null,
                            "Please choose at least one item",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }

                // Fine checking
                if(checkedQuantity && numChosenItems > 0)
                {
                    Order newOrder = new Order(curUser.userId,totalPrice,latLong);
                    System.out.println("Total price: " + totalPrice + " latlong: " + latLong);

                    try {
                        int statusCode = os.createNewOrder(newOrder, tempOrdered);
                        switch(statusCode)
                        {
                            case 1: // Success
                                JOptionPane.showMessageDialog(null,
                                        "New order has been added",
                                        "Message",JOptionPane.INFORMATION_MESSAGE);
                                break;

                            case 2: // Fail
                                JOptionPane.showMessageDialog(null,
                                        "Cannot send new order. Please try again later",
                                        "Message",JOptionPane.ERROR_MESSAGE);
                                break;

                            default:
                                break;
                        }
                    }catch(IOException ioe)
                    {
                        System.out.println("Cannot create new order");
                    }
                }

            }
        }else if(e.getSource() instanceof JCheckBox) // hidden box
        {
            updateTotalPrice();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        JPanelItemControl jpit = null;

        if(e.getDocument().getProperty("itemControl") != null)
        {
            jpit = (JPanelItemControl) e.getDocument().getProperty("itemControl");
            Goods goods = jpit.getGoods();
            JFormattedTextField jft = jpit.getJtf();

            int qty = (int)jft.getValue();
            double tempTotal = goods.getPrice() * qty;
            jpit.setTotalPrice(tempTotal);
        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }


    public void updateTotalPrice()
    {
        ClientFrameMain cfm = (ClientFrameMain)jf;

        double total = 0.0;
        for(int i = 0; i < cfm.getClientFrameApp().getListItemDisplay().size(); i++)
        {
            JPanelItemControl cur = cfm.getClientFrameApp().getListItemDisplay().get(i);
            if(cur.getJcbox().isSelected())
            {
                total += cur.getTotalPrice();
            }
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        cfm.getClientFrameApp().getTotalValue().setText(formatter.format(total));
        cfm.getClientFrameApp().getTotalValue().updateUI();
    }
}
