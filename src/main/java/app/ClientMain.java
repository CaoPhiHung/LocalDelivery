package main.java.app;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import main.java.model.EventAction;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;

import main.java.client.ClientFrameMain;
import main.java.service.ClientConnectService;

public class ClientMain {

	private User user;
	private GenericDLinkedList<Goods> goods_list;
	private GenericDLinkedList<Order> order_list;
	private GenericDLinkedList<OrderDetail> order_detail_list;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
    public static void main(String[] args) throws IOException {

        // Run client frame
        ClientFrameMain cfm = new ClientFrameMain();

//    	GenericDLinkedList<Goods> test = client.getAllGoodsList();
//    	GenericDLinkedList<Order> test2 = client.getAllOrderList(1);
//    	GenericDLinkedList<OrderDetail> test3 = client.getAllOrderDetailList(1);
    }
    
    private GenericDLinkedList<OrderDetail> getAllOrderDetailList(int id) throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
	    	EventAction event = new EventAction();
	    	event.eventType = 3;
	    	event.requireId = id;
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				this.order_detail_list = event.order_detail_list;
				socket.close();
				return this.order_detail_list;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		socket.close();
		return null;
	}
    
    public GenericDLinkedList<Goods> getAllGoodsList() throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
	    	EventAction event = new EventAction();
	    	event.eventType = 1;
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				this.goods_list = event.goods_list;
				socket.close();
				return this.goods_list;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
		return null;
    } 
    
    public GenericDLinkedList<Order> getAllOrderList(int userId) throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
	    	EventAction event = new EventAction();
	    	event.eventType = 2;
	    	event.requireId = userId;
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				this.order_list = event.order_list;
				socket.close();
				return this.order_list;
			}
  
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
		return null;
    } 
}
