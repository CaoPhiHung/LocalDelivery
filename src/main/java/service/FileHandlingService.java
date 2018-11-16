package main.java.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.GenericBinarySearchTree;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;

public class FileHandlingService {
	
	private int readType;
	private int checkUserId;
	private int checkOrderId;
	private int statusCode = 0;
	public Order newOrder;
	public ArrayList<OrderDetail> new_order_detail_list;

	
	public GenericDLinkedList<User> user_list = new GenericDLinkedList<User>();
	public GenericDLinkedList<Goods> goods_list = new GenericDLinkedList<Goods>();
	public GenericBinarySearchTree<Order> order_list = new GenericBinarySearchTree<Order>();
	public GenericDLinkedList<OrderDetail> order_detail_list = new GenericDLinkedList<OrderDetail>();

	public int getStatuscCode(){
		return this.statusCode;
	}
	
	public void setNewOrder(Order newOrder){
		this.newOrder = newOrder;
	}
	
	public void setNewOrderDetail(ArrayList<OrderDetail> newOrderDetail){
		this.new_order_detail_list = newOrderDetail;
	}
	
	public void setReadType(int type){
		this.readType = type;
	}
	
	public void setCheckedUserId(int id){
		this.checkUserId = id;
	}
	
	public void setCheckedOrderId(int orderId) {
		// TODO Auto-generated method stub
		this.checkOrderId = orderId;
	}
	
	/**
	 * 	
	 * @param <T>
	 * @param filename
	 */
	public <T> void readFile(String filename){
		
		String myCurrentDir = System.getProperty("user.dir") ;
        filename = myCurrentDir + "/src/main/resources/db/" + filename;

		try {
			FileReader f = new FileReader(filename);
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String[] parts = s.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

				switch (readType) {
					case 0:
						int user_id = Integer.parseInt(parts[0]);
						User newUser = new User(user_id, parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
						user_list.addAtTheEnd(newUser);
						break;
					case 1:
						int goods_id = Integer.parseInt(parts[0]);
						double price = Double.parseDouble(parts[2]);
						int quantity = Integer.parseInt(parts[3]);
						Goods newGoods = new Goods(goods_id, parts[1], price, quantity, parts[4]);
						goods_list.addAtTheEnd(newGoods);
						break;
					case 2:
						int userid = Integer.parseInt(parts[1]);
                        System.out.println("Userid: " + userid);
                        System.out.println("Check user id : " + this.checkUserId);

						if(userid == this.checkUserId) {
                            System.out.println("here");
							int id = Integer.parseInt(parts[0]);

							Date date = new SimpleDateFormat("yyyy-mm-dd").parse(parts[2]);

							double price1 = Double.parseDouble(parts[3]);
							Order newOrder = new Order(id, userid, date , price1, parts[4]);
							order_list.insert(newOrder);
						}
						break;
					case 3:
						int orderId = Integer.parseInt(parts[1]);
						if(orderId == this.checkOrderId) {
							int id = Integer.parseInt(parts[0]);
							int goodsid = Integer.parseInt(parts[2]);
							int quantity1 = Integer.parseInt(parts[3]);
							OrderDetail newOrderDetail = new OrderDetail(id, orderId, goodsid, quantity1);
							order_detail_list.addAtTheEnd(newOrderDetail);
						}

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
	
	public GenericBinarySearchTree<Order> getOrderList(){
		return this.order_list;
	}
	
	/**
	 * 
	 * @param filename
	 */
	public void writeFile(String filename){
		String myCurrentDir = System.getProperty("user.dir") ;
        filename = myCurrentDir + "/src/main/resources/db/" + filename;
        String lastLine = "", sCurrentLine;
        int id = 0;
        try {

        	BufferedReader br = new BufferedReader(new FileReader(filename));
        	while ((sCurrentLine = br.readLine()) != null) 
            {
                lastLine = sCurrentLine;
            }
        	
        	if(lastLine != null){
		    	String[] parts = lastLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		    	id = Integer.parseInt(parts[0]);
		    	br.close();
        	}
        	
        	BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
    	    switch (readType) {
			case 0: // user
				break;
			case 1: //order
				String currentDate = this.newOrder.getStringCurrentDate(this.newOrder.date);
				String newOrder = (id + 1) + ","+ this.newOrder.userId + "," + currentDate + "," 
									+ this.newOrder.totalPrice + "," + this.newOrder.destination ;
				writer.append(newOrder + "\n");
				this.statusCode = 1;
				break;
			case 2: // goods
				
				break;
			case 3: // order detail
//				1,1,1,33
					for (int i = 0; i < new_order_detail_list.size(); i++) {
						id++;
						OrderDetail od = new_order_detail_list.get(i);
						String newOrderDetail = id + "," + od.orderId + "," + od.goodsId
												+ "," + od.quantity;
						writer.append(newOrderDetail + "\n");
					}
					this.statusCode = 1;
				break;
			default:
				break;
		} 
    	    writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args){
		FileHandlingService fh = new FileHandlingService();
//		fh.setReadType(1);
//		fh.setNewOrder(new Order(1, 46, "hello"));
//		fh.writeFile("Order.txt");
		
//		fh.setReadType(3);
//		OrderDetail od1 = new OrderDetail(0, 1, 100, 101);
//		OrderDetail od2 = new OrderDetail(0, 1, 100, 102);
//		OrderDetail od3 = new OrderDetail(0, 1, 100, 103);
//		ArrayList<OrderDetail> odl = new ArrayList<>();
//		odl.add(od1);
//		odl.add(od2);
//		odl.add(od3);
//		fh.setNewOrderDetail(odl);
//		fh.writeFile("OrderDetail.txt");
	}
	
}
