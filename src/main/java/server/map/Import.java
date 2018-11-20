package main.java.server.map;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Import {
	
	public static ArrayList<String> alsImport() {
		//For testing reset button
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);


		File[] f = fd.getFiles();
		
		if(f.length > 0){
		    System.out.println(fd.getFiles()[0].getAbsolutePath());
		}
		
//		File[] f = new File[1];
//		f[0] =	new File("T:/src/main/java/server/map/mapDB/map2018-11-05-12-36-833.txt");
		
		String sFileContent = "";
		try {
			FileReader frMap = new FileReader(f[0]);
			int iRaw = frMap.read();
			while (iRaw != -1) {
				char cRaw = (char)(iRaw);
				sFileContent += String.valueOf(cRaw);
				iRaw = frMap.read();
			}
			frMap.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] asFileContent = sFileContent.split("\n");
		ArrayList<String> alsResult = new ArrayList<String>();
		for (int i = 0; i < asFileContent.length; ++i) {
			alsResult.add(asFileContent[i]);
		}
		return alsResult;
	}
	
	public static ArrayList<Integer> aliCheckInputFile(ArrayList<String> alsMapFile) {
		ArrayList<Integer> aliResult = new ArrayList<Integer>();
		if (alsMapFile.size() > 2) {
			String[] asRowCol = alsMapFile.get(0).split(",");
			if (asRowCol.length == 2) {
				if (CheckInput.bIsPositiveInteger(asRowCol[0]) && CheckInput.bIsPositiveInteger(asRowCol[1])){
					boolean bCheckLine = true;
					for (int i = 1; i < alsMapFile.size(); ++i) {
						String[] asLine = alsMapFile.get(i).split(",");
						if (asLine.length == 3) {
							if (CheckInput.bIsPositiveInteger(asLine[0]) && 
									CheckInput.bIsPositiveInteger(asLine[1])) {
								continue;
							}
							else {
								aliResult.add(i);
								bCheckLine = false;
								continue;
							}
						}
						else {
							aliResult.add(i);
							bCheckLine = false;
							continue;
						}
					}
					if (bCheckLine) {
						aliResult.add(0);
						return aliResult;
					}
					else {
						return aliResult;
					}
				}
			}
		}
		aliResult.add(-1);
		return aliResult;
	}
}
