package main.java.server.map;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class useMaze {

	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
				}
				try {
					Maze window = new Maze();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
//					System.exit(0);
				}
			}
		});
	}	
}
