package main.java.app;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import main.java.model.EventAction;
import main.java.model.Order;
import main.java.model.User;

public class ClientMain {

	private User user;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static boolean isConnected;
	
	
	public User getUser() {
		return this.user;
	}
	
    public static void main(String[] args) throws IOException {
    	ClientMain client = new ClientMain();
    	isConnected = client.connectServer("kmcgenn0", "iLBM2H");

    }
    
    public boolean connectServer(String username, String password) throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			user = new User();
			user.username = username;
			user.password = password;
			
	    	EventAction event = new EventAction();
	    	event.eventType = 0;
	    	event.user = user;
	    	
			oos.writeObject(event);

			if((event = (EventAction)ois.readObject()) != null) {
				System.out.println("This is my email: " + event.user.email);
				socket.close();
				return true;
			}

			
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
		return false;
		
    }
    
    public boolean getGoodsList() throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

//			user = new Users();
//			user.username = username;
//			user.password = password;
			
			oos.writeObject(user);

			if((user = (User)ois.readObject()) != null) {
				System.out.println("This is my email: " + user.email);
				socket.close();
				return true;
			}

			
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.close();
		return false;
		
    } 
    
}
