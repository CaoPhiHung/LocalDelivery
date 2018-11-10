package main.java.service;

import main.java.app.ServerMain;
import main.java.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class OrderDetailService {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
	private GenericDLinkedList<OrderDetail> order_detail_list;
	
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
}
