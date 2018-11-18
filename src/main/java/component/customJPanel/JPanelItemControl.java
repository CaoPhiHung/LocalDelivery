package main.java.component.customJPanel;

import main.java.model.Goods;

import javax.swing.*;
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
    private JLabel itemTitle;

    private Goods goods;

    public JPanelItemControl(URL url, int w, int h, Goods goods) {
        super(url, w, h);
        this.goods = goods;

        this.setOpaque(true);
        this.setBackground(new Color(183,190,243));

        controlsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlsWrapper.setOpaque(true);
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
        jtf.setOpaque(true);
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
        cboxWrapper.setOpaque(true);
        cboxWrapper.setBackground(new Color(183,190,243));

        jcbox = new JCheckBox();
        jcbox.setHorizontalAlignment(JCheckBox.CENTER);
        jcbox.setOpaque(false);
        jcbox.addActionListener(this);
//        jcbox.setForeground(new Color(61,68,92));
        cboxWrapper.add(jcbox);

        //Display title
        itemTitle = new JLabel(goods.getName());
        itemTitle.setOpaque(false);
//        itemTitle.setPreferredSize(new Dimension(120,20));
        itemTitle.setHorizontalAlignment(JLabel.CENTER);
        itemTitle.setVerticalAlignment(JLabel.TOP);
        itemTitle.setFont(new Font("Courier", Font.BOLD,14));

        JPanel tempoSouth = new JPanel(new BorderLayout());
        tempoSouth.setOpaque(false);
        tempoSouth.add(controlsWrapper, BorderLayout.SOUTH);
        tempoSouth.add(cboxWrapper, BorderLayout.CENTER);
        tempoSouth.add(itemTitle, BorderLayout.NORTH);

        this.add(tempoSouth,BorderLayout.SOUTH);
    }

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
        }else if(e.getSource() instanceof JCheckBox)
        {

        }
    }

    public JCheckBox getJcbox() {
        return jcbox;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JPanelBackground)
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
}
