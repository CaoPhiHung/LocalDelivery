package main.java.server.listeners;

import main.java.server.ServerFrameMain;
//import main.java.server.map.Maze;
import main/java/server/map/Maze.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        }else if(e.getSource() instanceof JCheckBox)
        {
            JComboBox cbox = (JComboBox)e.getSource();

            System.out.println(cbox.getSelectedIndex());
        }
    }

}
