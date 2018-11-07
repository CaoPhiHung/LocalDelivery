package main.java.component;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.net.URL;
import java.text.NumberFormat;

public class JPanelItem extends JPanel {

    private URL url;
    private Image picImg;
    private Image scaled;
    private JPanelBackground jpPic;
    private JPanel controlsWrapper;

    public JPanelItem(URL url, int w, int h)
    {
        this.url = url;
        jpPic = new JPanelBackground(url);
        controlsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setPreferredSize(new Dimension(w,h));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout(0,10));

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //Create button
        JButton jbp = new JButton("<<");
        JFormattedTextField jtf = new JFormattedTextField(formatter);
        jtf.setValue(0);
        JButton jbn = new JButton(">>");

        jbp.setPreferredSize(new Dimension(25,20));
        jtf.setPreferredSize(new Dimension(50,20));
        jbn.setPreferredSize(new Dimension(25,20));
        controlsWrapper.add(jbp);
        controlsWrapper.add(jtf);
        controlsWrapper.add(jbn);

        this.add(jpPic,BorderLayout.CENTER);
        this.add(controlsWrapper,BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
