package main.java.server.map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Export {
	public static void exportToFile(ArrayList<Point> alpInput) {
		long lMilSec = System.currentTimeMillis();
	    SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd-hh-mm-S");
	    Date resultdate = new Date(lMilSec);
		String sPath = System.getProperty("user.dir") + 
				"\\main\\java\\server\\map\\mapDB\\" ;
		File fTestPath = new File(sPath);
		if (!fTestPath.isDirectory()) {
			sPath = System.getProperty("user.dir");
		}
		sPath += "map" + sdf.format(resultdate)  + ".txt";
		File fOutput = new File(sPath );
		try {
			FileWriter fw = new FileWriter(fOutput);
			fw.write( Integer.toString(Maze.ROW) + "," +Integer.toString(Maze.COLUMN) + "\n" );
			for (int i = 0; i < alpInput.size(); ++i) {
				fw.write(alpInput.get(i).sExport());
			}
			JOptionPane.showMessageDialog(null,"Succefully export file to " + sPath);
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
