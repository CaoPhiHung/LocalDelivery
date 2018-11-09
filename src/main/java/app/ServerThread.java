package main.java.app;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import main.java.model.EventAction;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.User;
import main.service.FileHandling;

public class ServerThread implements Runnable{

	Socket cSocket; 
	private GenericDLinkedList<User> user_list;
	private GenericDLinkedList<Goods> goods_list;
	private GenericDLinkedList<Order> orders_list;
	private FileHandling fh = new FileHandling();
	
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
				case 0:
					if((event.user = checkAuthenticate((User)event.user)) != null){
						out.writeObject(event);
					}	
					break;
				case 1:

					break;
				default:
					break;
				}
			}
	        
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public User checkAuthenticate(User checkedUser){
		User returnUser = (User)this.user_list.search(checkedUser).data;
			
		return returnUser;
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
	
	
	public GenericDLinkedList<Order> getAllOrders(){
		fh.setReadType(2);
		fh.readFile("Goods.txt");
		return fh.order_list;
	}
	
}
