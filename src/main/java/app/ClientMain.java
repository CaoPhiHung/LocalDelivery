package main.java.app;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;


import main.java.model.User;

public class ClientMain {

    public static void main(String[] args) {
		try {

			System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT 
					+ " , " + (new Date()).toString());
			
			Socket socket = new Socket(ServerMain.IP, ServerMain.PORT);
		    
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	        
			User user = new User();
			user.password = "password";
			
			
			oos.writeObject(user);
//			oos.flush();
			
//			User checkUser = (User)ois.readObject();
//			System.out.println("This is my name: " + checkUser.name);
			
			socket.close();
			ois.close();
			oos.close();
	        System.out.println("Client gone");
			
		} catch (Exception e) {
			System.out.println(e);
		}
    }

}
