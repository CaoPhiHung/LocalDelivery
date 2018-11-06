package main.java.app;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import main.java.model.User;

public class ServerThread implements Runnable{

	Socket cSocket; 
	public boolean listen = true;
	
	public ServerThread(Socket cSocket) {
		this.cSocket = cSocket;
	}
	
	@Override
	public void run() {

		try {
			System.out.println("Sending message to client - " + (new Date()).toString());

			ObjectInputStream in = new ObjectInputStream(this.cSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(this.cSocket.getOutputStream());

			User user = (User)in.readObject();
//			User user = new User();
			user.name = "Hung";			
//			out.writeObject(user);
//			out.flush();
			System.out.println("Username:  " + user.password);
	        in.close();
	        out.close();
	        cSocket.close();
	        
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	

}
