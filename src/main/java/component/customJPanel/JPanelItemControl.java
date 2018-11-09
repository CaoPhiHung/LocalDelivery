package main.java.component.customJPanel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;

public class JPanelItemControl extends JPanelWithImage implements ActionListener {

    private JPanel controlsWrapper;
    private JFormattedTextField jtf;
    private JButton jbp;
    private JButton jbn;

    public JPanelItemControl(URL url, int w, int h) {
        super(url, w, h);

        controlsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        //Create button
        jbp = new JButton("<<");
        jtf = new JFormattedTextField(formatter);
        jbn = new JButton(">>");

        jtf.setValue(0);

        jbp.setPreferredSize(new Dimension(25,20));
        jtf.setPreferredSize(new Dimension(50,20));
        jbn.setPreferredSize(new Dimension(25,20));

        jbp.addActionListener(this);
        jbn.addActionListener(this);

        controlsWrapper.add(jbp);
        controlsWrapper.add(jtf);
        controlsWrapper.add(jbn);
        this.add(controlsWrapper,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton)
        {
            JButton clickedBtn = (JButton)e.getSource();

            if(clickedBtn.getText().equals("<<"))
            {
                int cur = (int)jtf.getValue();
                jtf.setValue(cur - 1);
            }else if(clickedBtn.getText().equals(">>"))
            {
                int cur = (int)jtf.getValue();
                jtf.setValue(cur + 1);
            }
        }
    }
}
