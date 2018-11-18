package main.java.client.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import main.java.client.listeners.ClientMenuListener;
import main.java.model.ClientModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrameMenu extends JFrame {
    private int appWidth = 600;
    private int appHeight = 400;

    private JPanel mainPanel;
    private JButton viewOrdersButton;
    private JButton orderGoodsButton;
    private JButton logOutButton;

    private ClientMenuListener cml;

    public ClientFrameMenu(ClientMenuListener cml) {
        this.cml = cml;
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

    private void createUIComponents() {

        orderGoodsButton = new JButton();
        viewOrdersButton = new JButton();
        logOutButton = new JButton();

    }

    private void assignListeners() {
        orderGoodsButton.addActionListener(this.cml);
        viewOrdersButton.addActionListener(this.cml);
        logOutButton.addActionListener(this.cml);
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
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-4735245));
        mainPanel.setOpaque(true);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setOpaque(false);
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        viewOrdersButton.setAlignmentY(0.0f);
        viewOrdersButton.setAutoscrolls(false);
        viewOrdersButton.setBackground(new Color(-12762020));
        viewOrdersButton.setBorderPainted(false);
        viewOrdersButton.setDefaultCapable(false);
        Font viewOrdersButtonFont = this.$$$getFont$$$("Ayuthaya", -1, 16, viewOrdersButton.getFont());
        if (viewOrdersButtonFont != null) viewOrdersButton.setFont(viewOrdersButtonFont);
        viewOrdersButton.setForeground(new Color(-593420));
        viewOrdersButton.setMargin(new Insets(0, 0, 0, 0));
        viewOrdersButton.setOpaque(true);
        viewOrdersButton.setText("View Orders");
        panel1.add(viewOrdersButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 1, false));
        orderGoodsButton.setAlignmentY(0.0f);
        orderGoodsButton.setAutoscrolls(false);
        orderGoodsButton.setBackground(new Color(-12762020));
        orderGoodsButton.setBorderPainted(false);
        orderGoodsButton.setDefaultCapable(false);
        Font orderGoodsButtonFont = this.$$$getFont$$$("Ayuthaya", -1, 16, orderGoodsButton.getFont());
        if (orderGoodsButtonFont != null) orderGoodsButton.setFont(orderGoodsButtonFont);
        orderGoodsButton.setForeground(new Color(-593420));
        orderGoodsButton.setMargin(new Insets(0, 0, 0, 0));
        orderGoodsButton.setOpaque(true);
        orderGoodsButton.setText("Order goods");
        panel1.add(orderGoodsButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 1, false));
        logOutButton.setAlignmentY(0.0f);
        logOutButton.setAutoscrolls(false);
        logOutButton.setBackground(new Color(-12762020));
        logOutButton.setBorderPainted(false);
        logOutButton.setDefaultCapable(false);
        Font logOutButtonFont = this.$$$getFont$$$("Ayuthaya", -1, 16, logOutButton.getFont());
        if (logOutButtonFont != null) logOutButton.setFont(logOutButtonFont);
        logOutButton.setForeground(new Color(-593420));
        logOutButton.setMargin(new Insets(0, 0, 0, 0));
        logOutButton.setOpaque(true);
        logOutButton.setText("Log out");
        panel1.add(logOutButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 1, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Courier", Font.BOLD, 48, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-9998707));
        label1.setText("MENU");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 50), null, 0, false));
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
