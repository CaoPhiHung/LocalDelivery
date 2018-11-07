package main.java.app;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;


import main.java.model.User;

public class ClientMain {

	private User user;
	
	public User getUser() {
		return this.user;
	}
	
    public static void main(String[] args) throws IOException {
    	new ClientMain().connectServer("phihung", "hello");
    }
    
    private void connectServer(String username, String password) throws IOException {
    	System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
				+ " , " + (new Date()).toString());

		try {
			Socket socket = new Socket(ServerMain.IP, ServerMain.PORT);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

			user = new User();
			user.username = username;
			user.password = password;
			
			while(true) {
				oos.writeObject(user);
				System.out.println("Test");
				if((user = (User)ois.readObject()) != null) {
//					System.out.println("This is my email: " + user.email);
				}
			}
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    

}
