package main.java.server.map;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CheckInput {
	
	public static boolean bIsPositiveInteger (String s) {
		try {
			int iInput = Integer.parseInt(s);
			if (iInput >= 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static int iGetInt (String sMessage) {
		int iResult = 0;
		boolean bValid = false;
		while (!bValid) {
			String sInput = JOptionPane.showInputDialog(sMessage);
			if (bIsPositiveInteger(sInput)) {
				iResult = Integer.parseInt(sInput);
				bValid = true;
			}
			else {
				JOptionPane.showMessageDialog(null,"Input should be a positive integer, please!");
			}
		}
		return iResult;
	}
	
	public static void checkMapFile( ArrayList<Integer> aliErrorLines) {
		String sError = "";
		if (aliErrorLines.size() == 1) {
			sError = "1";
		}
		else {
			for (int i = 0; i < aliErrorLines.size(); i++ ) {
				sError += Integer.toString(aliErrorLines.get(i) + 1) + "\n";
			}
		}
		JOptionPane.showMessageDialog(null,"Input file is corrupted, please check following line(s): \n"
				+ sError );
	}
	
	public static ArrayList<Integer> aliGetRowCol () {
		ArrayList<Integer> aliRowCol = new ArrayList<Integer>();
		aliRowCol.add(CheckInput.iGetInt("Please specify the row of maze: "));
		aliRowCol.add(CheckInput.iGetInt("Please specify the column of maze: "));
		return aliRowCol;
	}
	
	public static void loadingMap(JFrame frame, int ROW, int COLUMN) {
		//For testing Reset button
		int iInputMaze = JOptionPane.showConfirmDialog(null, "Do you want to load map file?\n Otherwise please"
				+ " put input for the maze!");
		if (iInputMaze == JOptionPane.YES_OPTION) {
			Draw.drawMaze(true);
		}
		else if (iInputMaze == JOptionPane.NO_OPTION) {
			ArrayList<Integer> aliRowCol = aliGetRowCol();
			Maze.ROW = aliRowCol.get(0);
			Maze.COLUMN = aliRowCol.get(1);
			Draw.drawMaze(false);
		}
		else if (iInputMaze == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
		
//		Draw.drawMaze(true);
	}
}
