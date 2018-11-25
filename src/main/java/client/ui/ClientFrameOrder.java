package main.java.client.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import main.java.client.listeners.ClientOrderListener;
import main.java.component.customJPanel.JPanelItemDisplay;
import main.java.model.*;
import main.java.service.OrderDetailService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ClientFrameOrder extends JFrame {

    private int appWidth = 800;
    private int appHeight = 600;

    private ClientModel model;

    private JPanel mainPanel;
    private JList listOrder;
    private JPanel userPanel;
    private JPanel userItemArea;
    private JPanel userItemList;
    private JPanel userInfoList;
    private JPanel controlArea;
    private JButton backButton;
    private JPanel itemLabelArea;
    private JPanel locationWrapper;
    public JLabel itemLabel;
    public JLabel locationLabel;
    public JLabel locationValue;
    private ClientOrderListener col;
    private Order curOrder = null;

    public ClientFrameOrder(ClientModel mo, ClientOrderListener col) {
        this.col = col;
        this.model = mo;

        $$$setupUI$$$();
        this.setContentPane(mainPanel);
        assignListeners();
    }

    public int getAppWidth() {
        return appWidth;
    }

    public void setAppWidth(int appWidth) {
        this.appWidth = appWidth;
    }

    public int getAppHeight() {
        return appHeight;
    }

    public void setAppHeight(int appHeight) {
        this.appHeight = appHeight;
    }

    private void assignListeners() {
        backButton.addActionListener(this.col);
    }

    private void createUIComponents() {

        System.out.println("Called in ClientFrame Order");
        locationWrapper = new JPanel();
        locationWrapper.setVisible(false);
        locationValue = new JLabel("N/A");
        this.userItemList = new JPanel(new FlowLayout(FlowLayout.LEFT));
        DefaultListModel listModel = new DefaultListModel();
        this.listOrder = new JList(listModel);
        backButton = new JButton("Back");

        model.setOrderIdList(new ArrayList<>());

        if (this.model.getOrderList().getRoot() != null) {
            GenericNode<Order> gnGood = this.model.getOrderList().getRoot();
            createOrderJFrame(gnGood, listModel);
        }

        this.listOrder.addListSelectionListener(col);
    }

    /**
     * Add to Model the data of order
     * @param root
     * @param listModel
     */
    public void createOrderJFrame(GenericNode<Order> root, DefaultListModel listModel) {
        if (root != null) {
            createOrderJFrame(root.left, listModel);
            listModel.addElement(root.data.displayOrder());
            model.getOrderIdList().add(root.data.orderId);
            createOrderJFrame(root.right, listModel);
        }
    }

    /**
     * Retrieve order in Binary Tree using recursive
     * @param root - root Node
     * @param orderId - orderId of Order
     * @return
     */
    private Order retrieveCurrentOrder(GenericNode<Order> root, int orderId) {
        if (root != null) {
            if (root.data.orderId == orderId) {
                return root.data;
            } else {
                Order leftOrder = retrieveCurrentOrder(root.left, orderId);
                Order rightOrder;
                if (leftOrder == null) {
                    rightOrder = retrieveCurrentOrder(root.right, orderId);
                    if (rightOrder == null) {
                        return null;
                    } else {
                        return rightOrder;
                    }
                } else {
                    return leftOrder;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * This is to update the details item in View Order function
     * @param orderIndex - index when choosing JList
     */
    public void updateDetailsItem(int orderIndex) {
//        System.out.println("Clicked index: " + orderIndex);
        try {
            int orderId = this.model.getOrderIdList().get(orderIndex);

//            System.out.println("existed orderId?? " + orderId);
            GenericNode<Order> temp = this.model.getOrderList().getRoot();
            this.curOrder = retrieveCurrentOrder(temp, orderId);

            if (this.curOrder != null) {
                locationValue.setText(this.curOrder.destination.replace('-', ','));
                locationWrapper.setVisible(true);
            }

            OrderDetailService ods = new OrderDetailService();
            GenericDLinkedList<OrderDetail> odlist = ods.getAllOrderDetailList(orderId);
            GenericNode<OrderDetail> singleOD = odlist.getHead();

            this.userItemList.removeAll();

            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            while (singleOD != null) {
                OrderDetail eachOD = singleOD.data;

                // Display order detail
//                System.out.println(eachOD.displayOrderDetail());

                // Display good inside
                GenericNode<Goods> singleGood = this.model.getGoodsList().getHead();
                while (singleGood != null) {
                    if (singleGood.data.getGoodsId() == eachOD.goodsId) {
                        URL url = this.getClass().getResource("../../../resources/images/" + singleGood.data.getImgPath());

                        this.userItemList.add(new JPanelItemDisplay(url, 130, 150,
                                singleGood.data.getName() + " (" + formatter.format(singleGood.data.getPrice()) + ")",
                                eachOD.quantity + "", new Color(183, 190, 243)));

                        System.out.println(singleGood.data.displayGoods());
                        break;
                    }
                    singleGood = singleGood.next;
                }

                singleOD = singleOD.next;
            }
            this.userItemList.updateUI();
        } catch (IOException ioe) {
            System.out.println("Error in Updating Details Item");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(new Color(-4735245));
        listOrder.setPreferredSize(new Dimension(200, 0));
        mainPanel.add(listOrder, BorderLayout.WEST);
        userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout(0, 0));
        userPanel.setBackground(new Color(-12762020));
        userPanel.setOpaque(true);
        mainPanel.add(userPanel, BorderLayout.CENTER);
        userInfoList = new JPanel();
        userInfoList.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        userInfoList.setOpaque(false);
        userPanel.add(userInfoList, BorderLayout.NORTH);
        itemLabelArea = new JPanel();
        itemLabelArea.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        itemLabelArea.setOpaque(false);
        userInfoList.add(itemLabelArea, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        itemLabel = new JLabel();
        Font itemLabelFont = this.$$$getFont$$$("Anonymous Pro for Powerline", Font.BOLD, 18, itemLabel.getFont());
        if (itemLabelFont != null) itemLabel.setFont(itemLabelFont);
        itemLabel.setForeground(new Color(-270858));
        itemLabel.setOpaque(false);
        itemLabel.setText("GOODS ORDERED");
        itemLabelArea.add(itemLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        itemLabelArea.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 5), null, 0, false));
        final Spacer spacer2 = new Spacer();
        userInfoList.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 5), null, 0, false));
        userItemArea = new JPanel();
        userItemArea.setLayout(new BorderLayout(0, 0));
        userItemArea.setOpaque(false);
        userPanel.add(userItemArea, BorderLayout.CENTER);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setOpaque(false);
        userItemArea.add(panel1, BorderLayout.SOUTH);
        locationWrapper.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        locationWrapper.setOpaque(false);
        panel1.add(locationWrapper, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        locationLabel = new JLabel();
        Font locationLabelFont = this.$$$getFont$$$("Courier", -1, 14, locationLabel.getFont());
        if (locationLabelFont != null) locationLabel.setFont(locationLabelFont);
        locationLabel.setForeground(new Color(-270858));
        locationLabel.setOpaque(false);
        locationLabel.setText("Location:");
        locationWrapper.add(locationLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(17, 16), null, 0, false));
        Font locationValueFont = this.$$$getFont$$$("Courier", -1, 14, locationValue.getFont());
        if (locationValueFont != null) locationValue.setFont(locationValueFont);
        locationValue.setForeground(new Color(-270858));
        locationValue.setOpaque(false);
        locationValue.setText("(0,0)");
        locationWrapper.add(locationValue, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 5), null, 0, false));
        userItemList.setOpaque(false);
        userItemArea.add(userItemList, BorderLayout.CENTER);
        controlArea = new JPanel();
        controlArea.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        controlArea.setOpaque(false);
        mainPanel.add(controlArea, BorderLayout.SOUTH);
        backButton.setBackground(new Color(-12762020));
        backButton.setBorderPainted(false);
        backButton.setDefaultCapable(false);
        backButton.setFocusTraversalPolicyProvider(false);
        Font backButtonFont = this.$$$getFont$$$("Ayuthaya", -1, 16, backButton.getFont());
        if (backButtonFont != null) backButton.setFont(backButtonFont);
        backButton.setForeground(new Color(-593420));
        backButton.setOpaque(true);
        backButton.setText("Return to menu");
        controlArea.add(backButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final Spacer spacer4 = new Spacer();
        controlArea.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer5 = new Spacer();
        controlArea.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
