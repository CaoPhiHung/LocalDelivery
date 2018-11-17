package src;

import java.awt.EventQueue;

public class useMaze {

	/**
	 * Launch the application.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
