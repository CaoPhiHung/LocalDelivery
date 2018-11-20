package main.java.server.map;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class Point {
	JButton btnT;
	public int iX;
	public int iY;
	public Color coBg;
	public String sStatus;
	public int iStepToGoal;
	public boolean bReachToPath = false;
	
	public String toString() {
		String sResult = "";
		sResult = 
			  "X is: " + Integer.toString(iX) + "\n" + 
			  "Y is: " + Integer.toString(iY)+ "\n" +
			  "Reached to goal: " + Boolean.toString(bReachToPath) + "\n" +
			  "Step to goal: " + Integer.toString(iStepToGoal) + "\n" + 
				"Status is: "+ sStatus + "\n";
		return sResult;
	}
	
	
	public String sExport() {
		String sResult = "";
		sResult = Integer.toString(iX)+ "," + Integer.toString(iY) +","+ sStatus + "\n";
		return sResult;
	}
	
	public static Point pFindPoint (int x, int y, ArrayList<Point> alPoint) {

		Point pResult = new Point();
		for (int i = 0; i < alPoint.size() ; ++i) {
			if ( x ==  alPoint.get(i).iX && y == alPoint.get(i).iY ) {
				return alPoint.get(i);
			}
		}
		return pResult;
	}
	
	public static ArrayList<Point> pFindReachToGoal(ArrayList<Point> alp4Points) {
		ArrayList<Point> alpToGoal = new ArrayList<>();
		for (int i = 0; i <alp4Points.size(); ++i ) {
			if ( alp4Points.get(i).bReachToPath == true) {
				alp4Points.add(alp4Points.get(i));
			}
		}
		return alpToGoal;
	}
	
	public static Point pFindShortest(ArrayList<Point> alpToGoal) {
		Point pShortest = alpToGoal.get(0);
		if (alpToGoal.size() > 1) {
			for (int i = 0; i < alpToGoal.size(); ++i) {
				if (alpToGoal.get(i).iStepToGoal < pShortest.iStepToGoal) {
					pShortest = alpToGoal.get(i);
				}
			}
		}
		return pShortest;
	}
	public static void modifyPoint (Point pInput, ArrayList<String> alsMap) {
		for (int i = 0; i < alsMap.size(); ++i) {
			String[] asLine = alsMap.get(i).split(",");
			if (pInput.iX == Integer.parseInt(asLine[0])) {
				if (pInput.iY == Integer.parseInt(asLine[1])) {
					pInput.sStatus = asLine[2];
					if (pInput.sStatus.compareTo("barrier")==0) {
						pInput.coBg = Color.red;
						pInput.btnT.setBackground(pInput.coBg);
						return;
					}
					else if (pInput.sStatus.compareTo("path")==0) {
						pInput.coBg = Color.GREEN;
						pInput.btnT.setBackground(pInput.coBg);
						return;
					}
				}
			}
		}
	}
	
	public static void Target(Point p) {
		p.coBg = Color.BLACK;
		p.btnT.setBackground(p.coBg);
		p.sStatus = "path";
	}
	
	public static void Path(Point pInput) {
		pInput.coBg = Color.GREEN;
		pInput.btnT.setBackground(pInput.coBg);
		pInput.sStatus = "path";
	}
	
	public static void Normal(Point pInput) {
		pInput.coBg = Color.CYAN;
		pInput.btnT.setBackground(pInput.coBg);
		pInput.sStatus = "normal";
	}
	
	public static void Visited(Point pInput) {
		//For debug: try to hide the visited path
//		pInput.coBg = Color.ORANGE;
		
		pInput.coBg = Color.CYAN;
		pInput.btnT.setBackground(pInput.coBg);
		pInput.sStatus = "visited";
	}
	
	public static void Barrier(Point pInput) {
		pInput.coBg = Color.RED;
		pInput.btnT.setBackground(pInput.coBg);
		pInput.sStatus = "barrier";
	}
	
	public static void Start(Point pInput) {
		pInput.coBg = Color.WHITE;
		pInput.btnT.setBackground(pInput.coBg);
		pInput.sStatus = "path";
	}
	public static int iPathCount (ArrayList<Point> alpInput) {
		int iResult = 0;
		for (int i = 0; i < alpInput.size(); ++i) {
			if (alpInput.get(i).sStatus.compareTo("path") == 0) {
				iResult++;
			}
		}
		return iResult;
	}
	
	public static int iTotalPointBetween(Point pA, Point pB) {
		int iTotal = 0;
		int iEdgeX = Math.abs(pA.iX - pB.iX);
		int iEdgeY = Math.abs(pA.iY - pB.iY);
		iTotal = iEdgeX * iEdgeY;
		int iNormal = 0;
		return iTotal;
	}
	
	public static double dDistance(Point pA, Point pB) {
		double dResult = 0;
		int iEdgeX = Math.abs(pA.iX - pB.iX);
		int iEdgeY = Math.abs(pA.iY - pB.iY);
		dResult = Math.sqrt(Math.pow(iEdgeX, 2) + Math.pow(iEdgeY, 2));
		dResult = iEdgeX * iEdgeY;
		return dResult;
	}
	
	public static boolean bBarrierBetweenX(int iSame, int iPointStart, int iPointTarget) {
		if (iPointStart > iPointTarget) {
			int iTemp = iPointStart;
			iPointStart = iPointTarget;
			iPointTarget = iTemp;
		}
		for (int i = iPointStart + 1; i < iPointTarget; ++i) {
			Point pTemp = Point.pFindPoint(iSame, i, Maze.alpPoints);
			if (pTemp.sStatus.compareTo("barrier") == 0)
				return true;
		}
		return false;
	}
	
	public static boolean bBarrierBetweenY(int iSame, int iPointStart, int iPointTarget) {
		if (iPointStart > iPointTarget) {
			int iTemp = iPointStart;
			iPointStart = iPointTarget;
			iPointTarget = iTemp;
		}
		for (int i = iPointStart + 1; i < iPointTarget; ++i) {
			Point pTemp = Point.pFindPoint(i, iSame, Maze.alpPoints);
			if (pTemp.sStatus.compareTo("barrier") == 0)
				return true;
		}
		return false;
	}
	
	public static Point pNearest (ArrayList<Point> alpInput) {
		Point pTemp = alpInput.get(0);
		Point pTarget = new Point();
		pTarget.iX = Maze.iXTarget;
		pTarget.iY = Maze.iYTarget;
		if (alpInput.size() == 3) {
			if (dDistance(alpInput.get(0), pTarget) > dDistance(alpInput.get(1), pTarget)) {
				alpInput.remove(alpInput.get(0));
			}
			else if (dDistance(alpInput.get(1), pTarget) > dDistance(alpInput.get(2), pTarget)) {
				alpInput.remove(alpInput.get(1));
			}
			else {
				alpInput.remove(alpInput.get(2));
			}
		}
		if (alpInput.size() == 2) {
			boolean bX = bBarrierBetweenX(pTarget.iX, alpInput.get(0).iY,pTarget.iY);
			boolean bY = bBarrierBetweenY(pTarget.iY, alpInput.get(0).iX,pTarget.iX);
			if ((alpInput.get(0).iX == pTarget.iX && bBarrierBetweenX(pTarget.iX, alpInput.get(0).iY,pTarget.iY)) ||
					(alpInput.get(0).iY == pTarget.iY && bBarrierBetweenY(pTarget.iY, alpInput.get(0).iX,pTarget.iX ))	){
					return alpInput.get(1);
			}
			else if ((alpInput.get(1).iX == pTarget.iX && bBarrierBetweenX(pTarget.iX, alpInput.get(1).iY,pTarget.iY)) ||
					(alpInput.get(1).iY == pTarget.iY && bBarrierBetweenY(pTarget.iY, alpInput.get(1).iX,pTarget.iX ))	) {
				return alpInput.get(0);
			}
			else {
				if (alpInput.get(0).iX == 5 && alpInput.get(0).iY == 12) {
					System.out.println("");
				}
				for (int i= 1; i < alpInput.size(); ++i) {
					double a = dDistance(alpInput.get(i), pTarget);
					double b = dDistance(pTemp, pTarget);
					if (dDistance(alpInput.get(i), pTarget) <= dDistance(pTemp, pTarget)) {
						pTemp = alpInput.get(i);
					}
				}	
			}
		}
		return pTemp;
	}
}

