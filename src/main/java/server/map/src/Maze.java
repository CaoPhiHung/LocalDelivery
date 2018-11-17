package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Maze {
	public boolean bFounded;
	public static int ROW ;
	public static int COLUMN;
	public static int iXTarget ;
	public static int iYTarget;
	public static int iXStart;
	public static int iYStart;
	public static JFrame frame;
	public static boolean bHasTarget = false;
	public static boolean bStartSet = false;
	public static JPanel pnlPoint;
	public static ArrayList<Point> alpPoints;
	public static int iStep = 0;
	/**
	 * Create the application.
	 */
	public Maze() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(800, 800, 652, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckInput.loadingMap(frame, ROW, COLUMN); 
	}
	
	public static ArrayList<Integer> findMazePath( Point pInput, ArrayList<Integer> aliPathCount) {
		int x =  pInput.iX;
		int y = pInput.iY;
		String sStatus = pInput.sStatus;
		if (x > Maze.COLUMN || y > Maze.ROW || x < 0 || y < 0) {
			aliPathCount.set(0, 0);
			return aliPathCount;
		}
		else if ( sStatus.compareTo("visited") == 0 || sStatus.compareTo("barrier") == 0 ) {
			aliPathCount.set(0, 0);
			return aliPathCount;
		}
		else if (x == iXTarget && y == iYTarget  ) {
			Point.Target(pInput);
			aliPathCount.set(0,1);
			iStep = aliPathCount.get(1);
			iStep++;
			aliPathCount.set(1, iStep);
			pInput.bReachToPath = true;
			System.out.println(Point.iPathCount(Maze.alpPoints));
			return aliPathCount;
		}
		else {
			Point.Path(pInput);
			aliPathCount.set(0,1);
			iStep = aliPathCount.get(1);
			iStep++;
			aliPathCount.set(1, iStep);
			ArrayList<Point> alp4Points = new ArrayList<>();
			//Right
			if (x < (Maze.COLUMN - 1)) {
				Point pRight = Point.pFindPoint(x+1, y, Maze.alpPoints);
				if (pRight.sStatus.compareTo("normal") == 0) {
					alp4Points.add(pRight);
				}		
			}
			//Left
			if (x > 0  ) {
				Point pLeft = Point.pFindPoint(x -1, y, Maze.alpPoints);
				if (pLeft.sStatus.compareTo("normal") == 0) {
					alp4Points.add(pLeft);
				}	
			}
			//Up
			if ( y > 0) {
				Point pDown = Point.pFindPoint(x, y-1, Maze.alpPoints);
				if ( pDown.sStatus.compareTo("normal") == 0 ) {
					alp4Points.add(pDown);	
				}			
			}
			//Down
			if (y < (Maze.ROW - 1)) {	
				Point pUp = Point.pFindPoint(x, y+1, Maze.alpPoints);
				if (pUp.sStatus.compareTo("normal") == 0 ) {
					alp4Points.add(pUp);
				}			
			}		
			if (alp4Points.size() == 1) {
				if( findMazePath(alp4Points.get(0), aliPathCount).get(0) == 1) {
					aliPathCount.set(0,1);
					iStep = aliPathCount.get(1);
					iStep++;
					aliPathCount.set(1, iStep);
					return aliPathCount;
				}
				else {
					Point.Visited(alp4Points.get(0));
					return findMazePath(pInput,aliPathCount);
				}		
			}
			else if (alp4Points.size() > 1) {
				Point pNearest = Point.pNearest(alp4Points);
				if (findMazePath(pNearest, aliPathCount).get(0) == 1) {	
					aliPathCount.set(0,1);
					iStep = aliPathCount.get(1);
					iStep++;
					aliPathCount.set(1, iStep);
					return aliPathCount;
				}	
				else {
					Point.Visited(pNearest);
					return findMazePath(pInput, aliPathCount);
				}
			}		
		}
		Point.Visited(pInput);
		aliPathCount.set(0,0);
		return aliPathCount;	
	}
		
	public static ArrayList<Point> alpPointsToGoal ( ArrayList<Point> aliInput, ArrayList<Integer> aliPath ){
		ArrayList<Point> alpToGoal = new ArrayList<>();
		for (int i = 0; i < aliInput.size(); ++i) {
			if (findMazePath(aliInput.get(1), aliPath).get(1) == 1) {
				alpToGoal.add(aliInput.get(i));
			}
		}
		return alpToGoal;
	}
	
	public static void solveMaze(ArrayList<Point> alpToReset) {
		Maze.resetMaze(Maze.alpPoints);
		ArrayList<Integer> aliInt = new ArrayList<Integer>();
		aliInt.add(0);
		aliInt.add(0);
		 ArrayList<Integer> iAResult =  Maze.findMazePath( Maze.alpPoints.get(0),aliInt);
		 if ( iAResult.get(0) == 0) {
			 JOptionPane.showMessageDialog(null,"There is no pathway to the target"
			 		+ ", please select another target!");
		 }
	}
	
	public static void resetMaze(ArrayList<Point> alpToReset) {
		Maze.bHasTarget = false;
		Maze.iStep = 0;
		for (int i = 0; i < alpToReset.size(); ++i) {
			if (alpToReset.get(i).sStatus.compareTo("path") == 0 ||
					alpToReset.get(i).sStatus.compareTo("visited") == 0 ||
					alpToReset.get(i).coBg == Color.BLACK) {
				Point.Normal(alpToReset.get(i));
			}			
		}
	}
	
	public static void resetToReload(ArrayList<Point> alpToReset) {
		for (int i = 0; i < alpToReset.size(); ++i) {
			alpToReset.get(i).sStatus = "";
			alpToReset.get(i).coBg = Color.CYAN;
		}
	}
	
	public static void importMaze() {
		Maze.frame.getContentPane().remove(Maze.pnlPoint);;
		Maze.alpPoints.clear();
		ArrayList<String> alsNewResult = Import.alsImport();
		String[] asDimension = alsNewResult.get(0).split(",");
		Maze.ROW = Integer.parseInt(asDimension[0]);
		Maze.COLUMN = Integer.parseInt(asDimension[1]);
		Maze.pnlPoint = new JPanel();
		Maze.pnlPoint.setLayout(new GridLayout(Maze.ROW,Maze.COLUMN));
		Maze.frame.add(Maze.pnlPoint,BorderLayout.CENTER);		
		Draw.drawPoints(Maze.ROW, Maze.COLUMN, Maze.pnlPoint, alsNewResult, Maze.alpPoints);
		Maze.frame.validate();
		Maze.frame.repaint();
	}
	
	public static void exportMapToJPEG() {
		 BufferedImage image = new BufferedImage(Maze.pnlPoint.getWidth(), 
				 Maze.pnlPoint.getHeight(), BufferedImage.TYPE_INT_RGB);
		 Graphics2D g = image.createGraphics();
		 Maze.pnlPoint.printAll(g);
		 g.dispose();
		 try {
			 String sPath = System.getProperty("user.dir") + 
		 "\\main\\java\\server\\map\\mapImage\\";
		 File fPath = new File(sPath);
		 if (!fPath.isDirectory()) {
			sPath = System.getProperty("user.dir");
		}
		long lMilSec = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd-hh-mm-S");
		Date resultdate = new Date(lMilSec);
		sPath += "\\mapImage" + sdf.format(resultdate) + ".jpeg" ; 
		 ImageIO.write(image, "jpeg", new File(sPath));
		 JOptionPane.showMessageDialog(null,"Succesfully export image to "+ sPath);
		 } catch (IOException exp) {
		     exp.printStackTrace();
		 }
	 }
}
