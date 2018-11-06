package main.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.User;

public class FileHandling {
	
	private int readType;
	
	private void setReadType(int type){
		this.readType = type;
	}
	
	/**
	 * 	
	 * @param <T>
	 * @param filename
	 */
	public <T> void readFile(String filename){
		
		String myCurrentDir = System.getProperty("user.dir") ;
        filename = myCurrentDir + "\\" + filename + ".txt";

		try {
			FileReader f = new FileReader(filename);
			Scanner s = new Scanner(f);
			s.nextLine();
			while (s.hasNext()) {
				String[] parts = s.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

				switch (readType) {
					case 0:
						User newUser = new User();
						break;
					case 1:
						Goods newGoods = new Goods();
						break;
					case 2:
						Order newOrder = new Order();
						break;
		
					default:
						break;
				}
				
			}
			s.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param filename
	 */
	public void writeFile(String filename){
		
	}
	
	
	public static void main (String [] args){
		
	}
	
}
