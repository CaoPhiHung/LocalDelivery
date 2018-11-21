package main.java.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.model.GenericBinarySearchTree;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Message;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;

public class FileHandlingService {
	
	private int readType;
	private int checkUserId;
	private int checkOrderId;
	private int statusCode = 0;
	private ArrayList<Message> messagelist;
	public Order newOrder;
	public int newOrderID;
	public ArrayList<OrderDetail> new_order_detail_list;

	
	public GenericDLinkedList<User> user_list = new GenericDLinkedList<User>();
	public GenericDLinkedList<Goods> goods_list = new GenericDLinkedList<Goods>();
	public GenericBinarySearchTree<Order> order_list = new GenericBinarySearchTree<Order>();
	public GenericDLinkedList<OrderDetail> order_detail_list = new GenericDLinkedList<OrderDetail>();

	public ArrayList<Message> getMessage(){
		return this.messagelist;
	}
	
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

		try {
			FileReader f = new FileReader(getFilePath(filename));
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
//                        System.out.println("Userid: " + userid);
//                        System.out.println("Check user id : " + this.checkUserId);

						if(userid == this.checkUserId) {
//                            System.out.println("here");
							int id = Integer.parseInt(parts[0]);

							Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parts[2]);

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

        String lastLine = "", sCurrentLine;
        int id = 0;
        try {

        	BufferedReader br = new BufferedReader(new FileReader(getFilePath(filename)));
        	while ((sCurrentLine = br.readLine()) != null) 
            {
                lastLine = sCurrentLine;
            }
        	
        	if(!lastLine.isEmpty() && lastLine.trim() != ""){
		    	String[] parts = lastLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		    	id = Integer.parseInt(parts[0]);
		    	br.close();
        	}
        	
        	BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(filename), true));
    	    switch (readType) {
			case 0: // user
				break;
			case 1: //order
				String currentDate = this.newOrder.getStringCurrentDate(this.newOrder.date);
				this.newOrderID = id + 1;
				String newOrder = this.newOrderID + ","+ this.newOrder.userId + "," + currentDate + "," 
									+ this.newOrder.totalPrice + "," + this.newOrder.destination ;
				writer.append(newOrder + "\n");
//				this.statusCode = 1;
				break;
			case 2: // goods
				
				break;
			case 3: // order detail
//				1,1,1,33
					for (int i = 0; i < new_order_detail_list.size(); i++) {
						id++;
						OrderDetail od = new_order_detail_list.get(i);
						String newOrderDetail = id + "," + this.newOrderID + "," + od.goodsId
												+ "," + od.quantity;
						if(updateGoods(od.goodsId, od.quantity, "Goods.txt") > 0) {
							
							writer.append(newOrderDetail + "\n");
						}
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
	
	private String getFilePath(String filename){
		String myCurrentDir = System.getProperty("user.dir") ;
        filename = myCurrentDir + "/src/main/resources/db/" + filename;
        return filename;
	}
	
	private int update(String toUpdate, String updated, String filename){
		try {
			BufferedReader file = new BufferedReader(new FileReader(getFilePath(filename)));
		    String line;
		    String input = "";

		    while ((line = file.readLine()) != null)
		    	input += line + System.lineSeparator();

		    input = input.replace(toUpdate, updated);

		    FileOutputStream os = new FileOutputStream(getFilePath(filename));
		    os.write(input.getBytes());

		    file.close();
		    os.close();
		    
		    return 1;
		} catch (IOException e) {
			// TODO: handle exception
			return -1;
		}
		
	}

	private int updateGoods(int goodsId, int orderQuantity , String filename){
		try {
			FileReader f = new FileReader(getFilePath(filename));
			Scanner s = new Scanner(f);
			boolean found = false;
			int quantity = 0;
			String toUpdate = "";
			String updated = "";
			while (s.hasNext() && !found) {
				toUpdate = s.nextLine();
				String[] parts = toUpdate.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if(goodsId == Integer.parseInt(parts[0])){
					quantity = Integer.parseInt(parts[3]) -  orderQuantity;
					if(quantity < 0) {
						messagelist.add(new Message(-1, goodsId, "Goods quantity is not enough!!!"));
						return -1;
					}
					updated = parts[0] + "," + parts[1] + "," + parts[2] + ","
							+ quantity + "," + parts[4];
					found = true;
					if(update(toUpdate, updated, filename) < 0) {
						messagelist.add(new Message(0, goodsId, "Can not update is goods quantity!!!"));
						return - 1;
					}
				}
			}
			s.close();
			
			
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return -1;
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
