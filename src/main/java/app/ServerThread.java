package main.java.app;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import main.java.model.EventAction;
import main.java.model.GenericBinarySearchTree;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;
import main.java.service.FileHandlingService;

public class ServerThread implements Runnable{

	Socket cSocket; 
	private GenericDLinkedList<User> user_list;
	private FileHandlingService fh = new FileHandlingService();
	
	public ServerThread(Socket cSocket) {
		this.cSocket = cSocket;
		this.user_list = this.getAllUsers();
	}
	
	@Override
	public void run() {
		EventAction event = null;
		try {
			System.out.println("Sending message to client - " + (new Date()).toString());
			ObjectOutputStream out = new ObjectOutputStream(this.cSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(this.cSocket.getInputStream());
			
			if((event = (EventAction)in.readObject()) != null){
				switch (event.eventType) {
				case 0: // check login
                    System.out.println("Username : " + event.user.username);
                    event.user = checkAuthenticate((User)event.user);
					if(event.user  != null){
                        System.out.println("Username 2 : " + event.user.username);
					    out.writeObject(event);
					}else{
					    event.user = null;
                        out.writeObject(event);
                    }
					break;
				case 1: // get goods
					if ((event.goods_list = getAllGoods()) != null) {
						out.writeObject(event);
					}else {
						event.goods_list = null;
                        out.writeObject(event);
					}
					break;
				case 2:// get order list
					if ((event.order_list = getAllOrders(event.requireId)) != null) {
						event.order_list.display(event.order_list.getRoot());
						out.writeObject(event);
					}else {
						event.order_list = null;
                        out.writeObject(event);
					}
					break;
				case 3: // get order detail
					if ((event.order_detail_list = getAllOrderDetails(event.requireId)) != null) {
						out.writeObject(event);
					}else {
						event.order_detail_list = null;
                        out.writeObject(event);
					}
					break;
				case 4: // create new order
					event.statusCode = createNewOrder(event.newOrder);
					if(event.statusCode == 1){
						event.statusCode = createNewOrderDetail(event.newOrderDetail);
					}
					out.writeObject(event);
					break;
//				case 5: // create new order detail
//					event.statusCode = createNewOrderDetail(event.newOrderDetail);
//					out.writeObject(event);
//					break;
				default:
					break;
				}
			}
	        
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public User checkAuthenticate(User checkedUser){
        System.out.println("Before authenticate user");
        if(this.user_list.search(checkedUser) != null){
            User returnUser = (User)this.user_list.search(checkedUser).data;
            System.out.println("checkauthenticate returnUser: " + returnUser);
            return returnUser;
        }

		return null;
	}
	
	public GenericDLinkedList<User> getAllUsers(){
		fh.setReadType(0);
		fh.readFile("User.txt");
		return fh.user_list;
	}
	
	
	public GenericDLinkedList<Goods>  getAllGoods(){
		fh.setReadType(1);
		fh.readFile("Goods.txt");
		return fh.goods_list;
	}
	
	public GenericBinarySearchTree<Order> getAllOrders(int userId){
		fh.setReadType(2);
		fh.setCheckedUserId(userId);
		fh.readFile("Order.txt");
		return fh.order_list;
	}
	
	public GenericDLinkedList<OrderDetail> getAllOrderDetails(int orderId){
		fh.setReadType(3);
		fh.setCheckedOrderId(orderId);
		fh.readFile("OrderDetail.txt");
		return fh.order_detail_list;
	}

	public int createNewOrder(Order order){
		fh.setReadType(1);
		fh.setNewOrder(order);
		fh.writeFile("Order.txt");
		return fh.getStatuscCode();
	}
	
	public int createNewOrderDetail(ArrayList<OrderDetail> order_details){
		fh.setReadType(3);
		fh.setNewOrderDetail(order_details);
		fh.writeFile("OrderDetail.txt");
		return fh.getStatuscCode();
	}
}
