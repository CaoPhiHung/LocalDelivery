package main.java.server.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import main.java.app.ServerThread;
import main.java.component.customJButton.JButtonColor;
import main.java.component.customJPanel.JPanelItemDisplay;
import main.java.model.*;
import main.java.server.listeners.MainServerListener;
import main.java.service.FileHandlingService;
import main.java.service.OrderDetailService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ServerFrameApp extends JFrame {

    private int appWidth = 800;
    private int appHeight = 600;

    public JPanel mainPanel;
    public JList listOrder;
    public JButton exitBtn;
    public JPanel rightPanel;
    public JComboBox userCombo;
    public JPanel leftPanel;
    public JButton viewMapButton;
    public JPanel detailsPanel;
    public JPanel viewMapPanel;
    public JPanel exitArea;
    public JPanel topArea;
    public JPanel exitWrapper;
    public JPanel listOrderWrapper;
    public JButton refreshBtn;
    public JPanel locationWrapper;
    public JLabel locationLabel;
    public JLabel locationValue;
    public JButton clearBtn;
    private ArrayList<User> userList;
    private DefaultListModel listModel;

    private MainServerListener msl;
    private FileHandlingService fh;
    private Order curOrder = null;

    ClientModel model;

    public ServerFrameApp(MainServerListener msl, ClientModel model) {
        this.msl = msl;
        this.model = model;
        fh = new FileHandlingService();

        $$$setupUI$$$();
        setContentPane(mainPanel);
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

    private void createUIComponents() {
        this.detailsPanel = new JPanel();
        this.detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //List order
        listModel = new DefaultListModel();
        this.listOrder = new JList(listModel);
        this.listOrder.addListSelectionListener(this.msl);

        //Location
        locationWrapper = new JPanel();
        locationValue = new JLabel("N/A");

        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(Color.BLUE);

        rightPanel = new JPanel();
        exitBtn = new JButtonColor("Exit", Color.RED, Color.WHITE);
        refreshBtn = new JButtonColor("Refresh", Color.RED, Color.WHITE);

        //Combo list
        userCombo = new JComboBox();
        userCombo.addItem("Select user");

        userList = new ArrayList<>();
        userList.add(null);

        fh.setReadType(0);
        fh.readFile("User.txt");
        GenericNode<User> nodeUser = fh.user_list.getHead();
        while (nodeUser != null) {
            userCombo.addItem(nodeUser.data.fullname);
            userList.add(nodeUser.data);
            nodeUser = nodeUser.next;
        }

        // View Map
        viewMapButton = new JButton("View Map");

        refreshBtn = new JButton();
        clearBtn = new JButton();
        refreshBtn.addActionListener(this.msl);
        clearBtn.addActionListener(this.msl);
    }

    private void assignListeners() {
        exitBtn.addActionListener(this.msl);
        userCombo.addActionListener(this.msl);
        viewMapButton.addActionListener(this.msl);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void updateListOrder() {
        this.listModel.clear();

        if (this.model.getOrderList().getRoot() != null) {
            GenericNode<Order> gnGood = this.model.getOrderList().getRoot();
            createOrderJFrame(gnGood, listModel);
        }
        this.listOrder.updateUI();
    }

    private void createOrderJFrame(GenericNode<Order> root, DefaultListModel listModel) {
        if (root != null) {
            createOrderJFrame(root.left, listModel);
            System.out.println("display Order: " + root.data.displayOrder());
            listModel.addElement(root.data.displayOrder());
            model.getOrderIdList().add(root.data.orderId);
            createOrderJFrame(root.right, listModel);
        }
    }

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

    public void updateDetailsItem(int orderIndex) {
        System.out.println("Clicked index: " + orderIndex);
        try {
            int orderId = this.model.getOrderIdList().get(orderIndex);

            System.out.println("existed orderId?? " + orderId);
            GenericNode<Order> temp = this.model.getOrderList().getRoot();
            this.curOrder = retrieveCurrentOrder(temp, orderId);

            if (this.curOrder != null) {
                locationValue.setText(this.curOrder.destination.replace('-', ','));
                locationWrapper.setVisible(true);
            }

            OrderDetailService ods = new OrderDetailService();
            GenericDLinkedList<OrderDetail> odlist = ods.getAllOrderDetailList(orderId);
            GenericNode<OrderDetail> singleOD = odlist.getHead();

//            this.userItemList = new JPanel(new FlowLayout(FlowLayout.LEFT));
            this.detailsPanel.removeAll();

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

                        this.detailsPanel.add(new JPanelItemDisplay(url, 130, 150,
                                singleGood.data.getName() + " (" + formatter.format(singleGood.data.getPrice()) + ")",
                                eachOD.quantity + "", new Color(183, 190, 243)));

                        System.out.println(singleGood.data.displayGoods());
                        break;
                    }
                    singleGood = singleGood.next;
                }

                singleOD = singleOD.next;
            }
            this.detailsPanel.updateUI();
        } catch (IOException ioe) {
            System.out.println("Error in Updating Details Item");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Order getCurOrder() {
        return curOrder;
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
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setAutoscrolls(false);
        mainPanel.setBackground(new Color(-4735245));
        mainPanel.setDoubleBuffered(true);
        mainPanel.setEnabled(true);
        mainPanel.setFocusCycleRoot(false);
        mainPanel.setFocusTraversalPolicyProvider(false);
        mainPanel.setFocusable(false);
        mainPanel.setInheritsPopupMenu(false);
        mainPanel.setOpaque(true);
        mainPanel.setRequestFocusEnabled(false);
        mainPanel.setVerifyInputWhenFocusTarget(false);
        mainPanel.setVisible(true);
        rightPanel.setLayout(new BorderLayout(0, 0));
        rightPanel.setBackground(new Color(-9998707));
        rightPanel.setOpaque(true);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        viewMapPanel = new JPanel();
        viewMapPanel.setLayout(new GridLayoutManager(2, 6, new Insets(0, 0, 0, 0), -1, -1));
        viewMapPanel.setDoubleBuffered(false);
        viewMapPanel.setEnabled(true);
        viewMapPanel.setFocusable(false);
        viewMapPanel.setOpaque(false);
        viewMapPanel.setRequestFocusEnabled(false);
        viewMapPanel.setVerifyInputWhenFocusTarget(false);
        viewMapPanel.setVisible(true);
        rightPanel.add(viewMapPanel, BorderLayout.SOUTH);
        viewMapButton.setBackground(new Color(-270858));
        viewMapButton.setBorderPainted(false);
        Font viewMapButtonFont = this.$$$getFont$$$("Ayuthaya", -1, 12, viewMapButton.getFont());
        if (viewMapButtonFont != null) viewMapButton.setFont(viewMapButtonFont);
        viewMapButton.setForeground(new Color(-12762020));
        viewMapButton.setOpaque(true);
        viewMapButton.setText("View Map");
        viewMapPanel.add(viewMapButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        viewMapPanel.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 10), null, 0, false));
        final Spacer spacer2 = new Spacer();
        viewMapPanel.add(spacer2, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        viewMapPanel.add(spacer3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        viewMapPanel.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        viewMapPanel.add(spacer5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        locationWrapper.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        locationWrapper.setOpaque(false);
        viewMapPanel.add(locationWrapper, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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
        locationWrapper.add(locationValue, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Font detailsPanelFont = this.$$$getFont$$$("Ayuthaya", -1, 12, detailsPanel.getFont());
        if (detailsPanelFont != null) detailsPanel.setFont(detailsPanelFont);
        detailsPanel.setForeground(new Color(-131073));
        detailsPanel.setOpaque(false);
        rightPanel.add(detailsPanel, BorderLayout.CENTER);
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(0, 0));
        leftPanel.setOpaque(false);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        topArea = new JPanel();
        topArea.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        topArea.setOpaque(false);
        leftPanel.add(topArea, BorderLayout.NORTH);
        Font userComboFont = this.$$$getFont$$$("Courier", -1, 12, userCombo.getFont());
        if (userComboFont != null) userCombo.setFont(userComboFont);
        userCombo.setForeground(new Color(-9998707));
        topArea.add(userCombo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-12762020));
        Font label1Font = this.$$$getFont$$$("Ayuthaya", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12762020));
        label1.setText("User List");
        topArea.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshBtn.setBackground(new Color(-12762020));
        refreshBtn.setBorderPainted(false);
        refreshBtn.setDefaultCapable(true);
        Font refreshBtnFont = this.$$$getFont$$$("Ayuthaya", Font.BOLD, 14, refreshBtn.getFont());
        if (refreshBtnFont != null) refreshBtn.setFont(refreshBtnFont);
        refreshBtn.setForeground(new Color(-593420));
        refreshBtn.setOpaque(true);
        refreshBtn.setText("Refresh");
        refreshBtn.setVerifyInputWhenFocusTarget(false);
        topArea.add(refreshBtn, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(10, 20), null, 0, false));
        final Spacer spacer6 = new Spacer();
        topArea.add(spacer6, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 5), null, 0, false));
        final Spacer spacer7 = new Spacer();
        topArea.add(spacer7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(5, -1), null, 0, false));
        exitWrapper = new JPanel();
        exitWrapper.setLayout(new GridLayoutManager(1, 4, new Insets(10, 0, 10, 0), -1, 100));
        exitWrapper.setOpaque(false);
        leftPanel.add(exitWrapper, BorderLayout.SOUTH);
        exitArea = new JPanel();
        exitArea.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        exitWrapper.add(exitArea, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        exitBtn.setBackground(new Color(-1294763));
        Font exitBtnFont = this.$$$getFont$$$("Ayuthaya", Font.BOLD, 14, exitBtn.getFont());
        if (exitBtnFont != null) exitBtn.setFont(exitBtnFont);
        exitBtn.setForeground(new Color(-131073));
        exitBtn.setText("Exit");
        exitBtn.setVerifyInputWhenFocusTarget(false);
        exitArea.add(exitBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        exitWrapper.add(spacer8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(5, -1), null, 0, false));
        final Spacer spacer9 = new Spacer();
        exitWrapper.add(spacer9, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(5, -1), null, 0, false));
        clearBtn.setBackground(new Color(-12762020));
        clearBtn.setBorderPainted(false);
        clearBtn.setDefaultCapable(true);
        Font clearBtnFont = this.$$$getFont$$$("Ayuthaya", Font.BOLD, 14, clearBtn.getFont());
        if (clearBtnFont != null) clearBtn.setFont(clearBtnFont);
        clearBtn.setForeground(new Color(-593420));
        clearBtn.setOpaque(true);
        clearBtn.setText("Clear");
        clearBtn.setVerifyInputWhenFocusTarget(false);
        exitWrapper.add(clearBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listOrderWrapper = new JPanel();
        listOrderWrapper.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        leftPanel.add(listOrderWrapper, BorderLayout.CENTER);
        listOrderWrapper.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        listOrder.setOpaque(true);
        listOrder.setPreferredSize(new Dimension(200, 0));
        listOrderWrapper.add(listOrder, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(150, 50), null, 0, false));
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
