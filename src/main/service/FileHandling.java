package main.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.User;

public class FileHandling {
	
	private int readType;
	public GenericDLinkedList<User> user_list = new GenericDLinkedList<User>();
	public GenericDLinkedList<Goods> goods_list = new GenericDLinkedList<Goods>();;
	public GenericDLinkedList<Order> order_list = new GenericDLinkedList<Order>();;
	
	public void setReadType(int type){
		this.readType = type;
	}
	
	/**
	 * 	
	 * @param <T>
	 * @param filename
	 */
	public <T> void readFile(String filename){
		
		String myCurrentDir = System.getProperty("user.dir") ;
        filename = myCurrentDir + "\\src\\main\\resources\\db\\" + filename;

		try {
			FileReader f = new FileReader(filename);
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String[] parts = s.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

				switch (readType) {
					case 0:
						int user_id = Integer.parseInt(parts[0]);
						User newUser = new User(user_id, parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
						user_list.add(newUser);
						break;
					case 1:
						int goods_id = Integer.parseInt(parts[0]);
						double price = Double.parseDouble(parts[2]);
						int quantity = Integer.parseInt(parts[3]);
						Goods newGoods = new Goods(goods_id, parts[1], price, quantity, parts[4]);
						goods_list.add(newGoods);
						break;
					case 2:
						Order newOrder = new Order();
						order_list.add(newOrder);
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
	
	public GenericDLinkedList<User> getUserList(){
		return this.user_list;
	}
	
	public GenericDLinkedList<Goods> getGoodsList(){
		return this.goods_list;
	}
	
	public GenericDLinkedList<Order> getOrderList(){
		return this.order_list;
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
