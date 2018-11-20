package main.java.server.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Draw {
	
	public static void drawPoints(int iRow, int iColumn, JPanel jpMap, ArrayList<String> alsResult,ArrayList<Point>
	alpInput) {
		Maze.alpPoints = new ArrayList<Point>();
		if (alsResult.size() > 0) {
			String[] asDimension = alsResult.get(0).split(",");
			Maze.ROW = Integer.parseInt(asDimension[0]);
			Maze.COLUMN = Integer.parseInt(asDimension[1]);
		}	
		for (int x = 0; x < iRow ; ++x) {
			for (int y = 0; y < iColumn; ++y) {
				JButton btn = new JButton(y + "," + x);
				//For clicking the point case, switch between Normal Barrier Target(Normal)
				btn.setName("1");
				Point pTemp = new Point();
				pTemp.btnT = btn;
				pTemp.iX = y;
				pTemp.iY = x;
				pTemp.sStatus = "normal";
				pTemp.coBg = Color.CYAN;
				pTemp.btnT.setBackground(pTemp.coBg);
				Point.modifyPoint(pTemp,alsResult);
				jpMap.add(pTemp.btnT);
				pTemp.btnT.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton jb = (JButton) e.getSource();
						int iCurrent =Integer.parseInt(jb.getName()) ;
						if (Maze.bHasTarget ) {
							if (iCurrent > 2)
								iCurrent = 1;
						}
						else {
							if (iCurrent > 3)
								iCurrent = 1;
						}
						switch (iCurrent ) {
						case 1 :
							if (pTemp.coBg == Color.BLACK) {
								Maze.bHasTarget = false;
							}
							if (pTemp.coBg == Color.WHITE) {
								Maze.bStartSet = false;
							}
							Point.Barrier(pTemp);
							break;
						
						case 2:
							if (pTemp.coBg == Color.BLACK) {
								Maze.bHasTarget = false;
							}
							if (pTemp.coBg == Color.WHITE) {
								Maze.bStartSet = false;
							}
							Point.Normal(pTemp);
							break;
						
						case 3:
							if (Maze.bHasTarget == false) {
								pTemp.coBg = Color.BLACK;
								Maze.iXTarget = pTemp.iX;
								Maze.iYTarget = pTemp.iY;
								pTemp.btnT.setBackground(pTemp.coBg);
								pTemp.sStatus = "normal";
								Maze.bHasTarget = true;
								break;
							}
							else {
								JOptionPane.showMessageDialog(null,"We can choose only 1 target at 1 time,"
										+ "please");
							}	
//						case 4:
//							if (Maze.bStartSet == false) {
//								pTemp.coBg = Color.WHITE;
//								Maze.iXStart = pTemp.iX;
//								Maze.iYStart = pTemp.iY;
//								pTemp.btnT.setBackground(pTemp.coBg);
//								pTemp.sStatus = "normal";
//								Maze.bStartSet = true;
//								break;
//							}
//							else {
//								JOptionPane.showMessageDialog(null,"We can choose only 1 start at 1 time,"
//										+ "please");
//							}
						default:				
							break;
						}	
						int iNext = iCurrent + 1;
						String sNext = Integer.toString(iNext);
						jb.setName(sNext);
					}
				});
				Maze.alpPoints.add(pTemp);
			}	
		}
	}
	
	public static ArrayList<Point> drawMaze( Boolean bImport) {
		ArrayList<String> alsResult = new ArrayList<String>();
		if (bImport) {
			alsResult = Import.alsImport();
			ArrayList<Integer> aliCheck = Import.aliCheckInputFile(alsResult);
			//If the input is good to go
			if (aliCheck.get(0) == 0) {
				if (alsResult.size() > 0) {
					String[] asDimension = alsResult.get(0).split(",");
					Maze.ROW = Integer.parseInt(asDimension[0]);
					Maze.COLUMN = Integer.parseInt(asDimension[1]);
				}
			}
			else {
				CheckInput.checkMapFile(aliCheck);
				System.exit(0);
			}
		}
		Maze.pnlPoint = new JPanel();
		Maze.pnlPoint.setLayout(new GridLayout(Maze.ROW,Maze.COLUMN));
		Maze.frame.add(Maze.pnlPoint,BorderLayout.CENTER);
		Draw.drawPoints(Maze.ROW, Maze.COLUMN, Maze.pnlPoint, alsResult, Maze.alpPoints);
		JButton btnSolve = new JButton("Solve");
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(3, 3));
		pnlButtons.add(btnSolve );
		Maze.frame.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		btnSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Maze.solveMaze(Maze.alpPoints);
			}
		});
		JButton btnReset = new JButton("Reset");
		pnlButtons.add(btnReset);
		JButton btnExport = new JButton("Export map to file");
		pnlButtons.add(btnExport);
		JButton btnImport = new JButton("Import file to map");
		//For debug: set default Enter to Reset
				Maze.frame.getRootPane().setDefaultButton(btnImport);
				
		btnImport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Import here
				Maze.importMaze();
			}
		});
		pnlButtons.add(btnImport);
		btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Export.exportToFile(Maze.alpPoints);	
			}
		});
		btnReset.addActionListener(new ActionListener() {
			@Override	
			public void actionPerformed(ActionEvent arg0) {
				//Reset here
				Maze.resetMaze(Maze.alpPoints);
			}
		});
		
		JButton btnExportToJPEG = new JButton("Export to jpeg file");
		btnExportToJPEG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Maze.exportMapToJPEG();
			}
		});
		pnlButtons.add(btnExportToJPEG);
		
		return Maze.alpPoints;	
		
		
		
	}
}
