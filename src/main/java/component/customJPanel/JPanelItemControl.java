package main.java.component.customJPanel;

import main.java.client.listeners.ClientAppListener;
import main.java.client.ui.ClientFrameApp;
import main.java.model.Goods;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.NumberFormat;

public class JPanelItemControl extends JPanelWithImage implements ActionListener, MouseListener {

    private JPanel controlsWrapper;
    private JFormattedTextField jtf;
    private JButton jbp;
    private JButton jbn;
    private JCheckBox jcbox;
    private JCheckBox hiddenbox;
    private JLabel itemTitle;
    private JLabel itemQuantity;
    private JLabel itemPrice;

    private Goods goods;
    private double totalPrice = 0;

    private ClientAppListener cal;

    public JPanelItemControl(URL url , int w, int h, Goods goods, ClientAppListener cal)
    {
        this(url,w,h,goods);
        this.cal = cal;
        this.jtf.getDocument().putProperty("itemControl",this);
        this.jtf.getDocument().addDocumentListener(this.cal);
        this.jtf.addActionListener(this.cal);

        hiddenbox = new JCheckBox();
        hiddenbox.addActionListener(this.cal);
        hiddenbox.setVisible(false);

    }
    public JPanelItemControl(URL url, int w, int h, Goods goods) {
        super(url, w, h);
        this.goods = goods;

        NumberFormat curFormat = NumberFormat.getCurrencyInstance();

        this.setOpaque(true);
        this.setBackground(new Color(183,190,243));

        controlsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlsWrapper.setOpaque(false);
        controlsWrapper.setBackground(new Color	(183,190,243));

        // Create formatter for textfield
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //Create button
        jbp = new JButton("<");
        jbn = new JButton(">");
        setButtonStyle(jbp);
        setButtonStyle(jbn);
        jbp.setText("<");

        // Create textfield
        jtf = new JFormattedTextField(formatter);
        jtf.setValue(0);
        jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.setOpaque(false);
        jtf.setForeground(new Color(61,68,92));
        jtf.setFont(new Font("Courier", Font.PLAIN,12));

        jbp.setPreferredSize(new Dimension(25,20));
        jtf.setPreferredSize(new Dimension(50,20));
        jbn.setPreferredSize(new Dimension(25,20));

        jbp.addActionListener(this);
        jbn.addActionListener(this);
        jpPic.addMouseListener(this);

        controlsWrapper.add(jbp);
        controlsWrapper.add(jtf);
        controlsWrapper.add(jbn);

        //Add checkbox
        JPanel cboxWrapper = new JPanel(new BorderLayout());
        cboxWrapper.setOpaque(false);

        jcbox = new JCheckBox();
        jcbox.setHorizontalAlignment(JCheckBox.CENTER);
        jcbox.setOpaque(false);
        jcbox.addActionListener(this);

        itemQuantity = new JLabel("Stock: " + goods.getQuantity());
        itemQuantity.setHorizontalAlignment(JCheckBox.CENTER);
        itemQuantity.setOpaque(false);
        itemQuantity.addMouseListener(this);

        itemPrice = new JLabel(" (" + curFormat.format(goods.getPrice()) + ")");
        itemPrice.setHorizontalAlignment(JCheckBox.CENTER);
        itemPrice.setOpaque(false);
        itemPrice.setFont(new Font("Courier", Font.ITALIC,14));
        itemPrice.addMouseListener(this);

        cboxWrapper.add(jcbox, BorderLayout.SOUTH);
        cboxWrapper.add(itemQuantity, BorderLayout.CENTER);
        cboxWrapper.add(itemPrice, BorderLayout.NORTH);

        //Display title
        itemTitle = new JLabel(goods.getName());
        itemTitle.setOpaque(false);
//        itemTitle.setPreferredSize(new Dimension(120,20));
        itemTitle.setHorizontalAlignment(JLabel.CENTER);
        itemTitle.setVerticalAlignment(JLabel.TOP);
        itemTitle.setFont(new Font("Courier", Font.BOLD,14));
        itemTitle.addMouseListener(this);

        JPanel tempoSouth = new JPanel(new BorderLayout());
        tempoSouth.setOpaque(false);
        tempoSouth.add(controlsWrapper, BorderLayout.SOUTH);
        tempoSouth.add(cboxWrapper, BorderLayout.CENTER);
        tempoSouth.add(itemTitle, BorderLayout.NORTH);

        this.add(tempoSouth,BorderLayout.SOUTH);
    }

    public JCheckBox getJcbox() {
        return jcbox;
    }

    public JFormattedTextField getJtf() {
        return jtf;
    }

    public Goods getGoods() {
        return goods;
    }

    private void setButtonStyle(JButton jb)
    {
        jb.setBorder(null);
        jb.setOpaque(true);
        jb.setBorderPainted(false);
        jb.setContentAreaFilled(true);
        jb.setBackground(new Color(61,68,92));
        jb.setForeground(new Color(246,241,244));
        jb.repaint();
    }

    /**
     * ACTION LISTENER
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("<"))
            {
                int cur = (int)jtf.getValue();
                if(cur > 0)
                {
                    jtf.setValue(cur - 1);
                }
            }else if(clickedBtn.getText().equals(">"))
            {
                int cur = (int)jtf.getValue();
                jtf.setValue(cur + 1);
            }

            this.cal.updateTotalPrice();
        }else if(e.getSource() instanceof JCheckBox)
        {
            JCheckBox cbChosen = (JCheckBox)e.getSource();
            this.hiddenbox.doClick();

            if(cbChosen.isSelected())
            {
                this.setBackground(new Color(251,221,246));
            }else
            {
                this.setBackground(new Color(183,190,243));
            }
        }
    }

    /**
     * MOUSE LISTENER
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JPanelBackground)
        {
            this.jcbox.doClick();
        }else if(e.getSource() instanceof JLabel)
        {
            this.jcbox.doClick();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
