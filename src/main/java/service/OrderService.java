package main.java.service;

import main.java.app.ClientMain;
import main.java.app.ServerMain;
import main.java.client.ClientFrameMain;
import main.java.model.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class OrderService {
	private GenericAVLTree<Order> order_list;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * 
     * @param userId
     * @return
     * @throws IOException
     */
    public GenericAVLTree<Order> getAllOrderList(int userId) throws IOException {
    	System.out.println("Trying to connect server ");

		try {
			socket = new Socket(ClientMain.IP_address, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
	    	EventAction event = new EventAction();
	    	event.eventType = 2;
	    	event.requireId = userId;
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				this.order_list = event.order_list;
				System.out.println("order Size: " + this.order_list.size());
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
    
    /**
     * 
     * @param order
     * @param ods
     * @return
     * @throws IOException
     */
    public int createNewOrder(Order order, ArrayList<OrderDetail> ods) throws IOException {
    	System.out.println("Trying to connect server ");
		try {
			socket = new Socket(ClientMain.IP_address, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			
	    	EventAction event = new EventAction();
	    	event.eventType = 4;
	    	event.newOrder = order;
	    	event.newOrderDetail = ods;
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				socket.close();
				return event.statusCode;
			}
  
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
		return 0;
		
    }
}
