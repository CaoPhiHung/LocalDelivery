package main.java.service;

import main.java.app.ServerMain;
import main.java.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class GoodsService {

	private GenericDLinkedList<Goods> goods_list;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

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
}
