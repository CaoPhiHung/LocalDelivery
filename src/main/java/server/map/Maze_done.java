import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Maze {
	public boolean bFounded;
	public int ROW = 12;
	public int COLUMN = 20;
	private JFrame frame;

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
//					window.frame.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static JButton btnFindBtn (int x, int y, JButton[] aBtn) {
		JButton btn = new JButton("");
		for (int i = 0; i < aBtn.length ; ++i) {
			if ( x == (int) aBtn[i].getClientProperty("x") && y == (int) aBtn[i].getClientProperty("y") ) {
				return aBtn[i];
			}
		}
		return btn;
	}
	
	public int[] findMazePath(int x, int y, int iRow, int iColumn, JButton btn, JButton[] aBtn) {
		
		int[] iAResult = new int[2];
		x = (int) btn.getClientProperty("x");
		y = (int)btn.getClientProperty("y");
		String sStatus = (String) btn.getClientProperty("status");
		if (x > iColumn || y > iRow || x < 0 || y < 0) {
			iAResult[0] = 0;
			return iAResult;
		}
		else if (btn.getClientProperty("status").equals("visited") || btn.getClientProperty("status").equals("barrier")) {
			iAResult[0] = 0;
			return iAResult;
		}
		else if (x == iColumn - 1 && sStatus.equals("normal") ) {
			btn.setBackground(Color.green);
			btn.putClientProperty("status", "path");
			iAResult[0] = 1;
			iAResult[1]++;
			return iAResult;
		}
		else {
			btn.setBackground(Color.GREEN);
			
			btn.putClientProperty("status", "path");
			iAResult[0] = 1;
			iAResult[1]++;
			
			if ( y > 0) {
				JButton btnDown = btnFindBtn(x, y-1, aBtn);
				if (btnDown.getClientProperty("status").equals("normal")) {
					int[] iABtnResuls = findMazePath(x, y + 1, iRow, iColumn, btnDown, aBtn);
					if (iABtnResuls[0] == 1) {
						iAResult[1]++;
						return iAResult;
					}
					else {
						btnDown.setBackground(Color.orange);
						btnDown.putClientProperty("status", "visited");
						return findMazePath(x, y, iRow, iColumn, btn, aBtn);
					}	
				}
				
			}
			if (y < (iRow - 1)) {	
				JButton btnUp = btnFindBtn(x, y+1, aBtn);
				if (btnUp.getClientProperty("status").equals("normal")) {
					int[] iABtnResuls = findMazePath(x, y + 1, iRow, iColumn, btnUp, aBtn);
					if (iABtnResuls[0] == 1) {
						iAResult[1]++;
						return iAResult;
					}
					else {
						btnUp.setBackground(Color.orange);
						btnUp.putClientProperty("status", "visited");
						return findMazePath(x, y, iRow, iColumn, btn, aBtn);
					}
				}
				
			}
		
			
		
			if (x > 0  ) {
				JButton btnLeft = btnFindBtn(x - 1, y,aBtn);
				if (btnLeft.getClientProperty("status").equals("normal")) {
					int[] iABtnResuls = findMazePath(x, y + 1, iRow, iColumn, btnLeft, aBtn);
					if (iABtnResuls[0] == 1) {
						iAResult[1]++;
						return iAResult;
					}
					else {
						btnLeft.setBackground(Color.orange);
						btnLeft.putClientProperty("status", "visited");
						return findMazePath(x, y, iRow, iColumn, btn, aBtn);
					}
				}
				
			}
			
			
			if (x < (iColumn - 1)) {
				JButton btnRight = btnFindBtn(x+1, y, aBtn);
				if (btnRight.getClientProperty("status").equals("normal")) {
					int[] iABtnResuls = findMazePath(x, y + 1, iRow, iColumn, btnRight, aBtn);
					if (iABtnResuls[0] == 1) {
						iAResult[1]++;
						return iAResult;
					}
					else {
						btnRight.setBackground(Color.orange);
						btnRight.putClientProperty("status", "visited");
						return findMazePath(x, y, iRow, iColumn, btn, aBtn);
					}
				}
				
			}
		
		}
		
			btn.setBackground(Color.ORANGE);
			btn.putClientProperty("status", "visited");
			iAResult[0] = 0;
			return iAResult;
		
		

		
	}

	/**
	 * Create the application.
	 */
	public Maze() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(800, 800, 652, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Maze mz = new Maze();
		
		JButton[] buttons = drawMaze(frame, ROW, COLUMN);
	}
	
	

	public JButton[] drawMaze(JFrame frame, int iRow, int iColumn) {
		iRow = ROW;
		iColumn = COLUMN;
		JButton[] buttons = new JButton[iRow*iColumn];
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(iRow,iColumn));
		frame.add(pnl,BorderLayout.CENTER);
		int z = 0;
		for (int x = 0; x < iRow ; ++x) {
			for (int y = 0; y < iColumn; ++y) {
				JButton btn = new JButton(y + "," + x);
				buttons[z] = btn;
				z++;
				btn.setName("1");;
				btn.putClientProperty("x", y);
				btn.putClientProperty("y", x);
				btn.putClientProperty("status", "normal");
				btn.setBackground(Color.CYAN);
//				TestCase_1exit(btn);
//				TestCase_2exit(btn);
				TestCase_4exit(btn);
				pnl.add(btn);
				btn.addActionListener(new ActionListener() {
						
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton jb = (JButton) e.getSource();
						
						int iCurrent =Integer.parseInt(jb.getName()) ;
						if (iCurrent > 2)
							iCurrent = 1;
						
						switch (iCurrent ) {
						case 1 :
							btn.setBackground(Color.RED);
							btn.putClientProperty("status", "barrier");;
							break;
						
						case 2:
							btn.setBackground(Color.cyan);
							btn.putClientProperty("status", "normal");
							break;
						default:
							
							break;
						}
						
						int iNext = iCurrent + 1;
						String sNext = Integer.toString(iNext);
						jb.setName(sNext);
					}
				});
			}
			
		}
		
		JButton btnSolve = new JButton("Solve");
		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnSolve, BorderLayout.NORTH );
		frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		btnSolve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				 Maze mz = new Maze();
					 
					 int[] iAResult =  mz.findMazePath((int)buttons[0].getClientProperty("x"),(int)buttons[0].getClientProperty("y"), ROW, COLUMN, buttons[0],buttons);

			}
		});
		JButton btnReset = new JButton("Reset");
		pnlButtons.add(btnReset,BorderLayout.CENTER);
		btnReset.addActionListener(new ActionListener() {
			
			@Override	
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			pnl.removeAll();
			Maze maze = new Maze();
			maze.initialize();		
			}
		});
		return buttons;	
	}
	public static void TestCase_1exit (JButton btn) {
		int[][] i2AXY = new int[][] {
			{1,0},
			{1,1},
			{1,2},
			{1,3},
			{1,4},
			{2,4},
			{3,4},
			{4,4},
			{5,4},
			{6,4},
			{7,4},
			{8,4},
			{9,4}
		};
		
		for (int i = 0 ; i < i2AXY.length ; ++i) {
			if ((int)btn.getClientProperty("x") == i2AXY[i][0] && (int)btn.getClientProperty("y") == i2AXY[i][1]) {
				btn.setBackground(Color.red);
				btn.putClientProperty("status", "barrier");
			}
		}
	}
	
	public static void TestCase_2exit (JButton btn) {
		int[][] i2AXY = new int[][] {
			{1,0},
			{1,1},
			{1,2},
			{1,3},
			{1,4},
			
			{2,1},
			
			{3,3},
			{3,4},
			{3,5},
			
			{4,1},
			{4,3},
			{4,4},
			
			{5,0},
			{5,1},
			{5,3},
			{5,4},
			{6,1},
			{6,3},
			{6,4},
			
			{7,1},
			{7,3},
			{7,4},
			{8,1},
			{8,3},
			{8,4},
			
			{9,1},
			{9,3},
			{9,4},
			
		};
		
		for (int i = 0 ; i < i2AXY.length ; ++i) {
			if ((int)btn.getClientProperty("x") == i2AXY[i][0] && (int)btn.getClientProperty("y") == i2AXY[i][1]) {
				btn.setBackground(Color.red);
				btn.putClientProperty("status", "barrier");
			}
		}
	}
	

	public static void TestCase_4exit (JButton btn) {
		int[][] i2AXY = new int[][] {
			{1,0},
			{1,1},
			{1,2},
			{1,3},
//			{1,4}, remove this 1 to test 4-direction test case
			
			{2,1},
			
			{3,3},
			{3,4},
			{3,5},
			
			{4,1},
			{4,3},
			{4,4},
			
			{5,0},
			
			{6,2},
			{6,3},
			{6,4},
			
			{7,1},
			
			{8,1},
			{8,3},
			{8,4},
			
			{9,0},
			{9,1},
			{9,2},
			{9,3},
			{9,4},
			
		};
		
		for (int i = 0 ; i < i2AXY.length ; ++i) {
			if ((int)btn.getClientProperty("x") == i2AXY[i][0] && (int)btn.getClientProperty("y") == i2AXY[i][1]) {
				btn.setBackground(Color.red);
				btn.putClientProperty("status", "barrier");
			}
		}
	}
	


}
