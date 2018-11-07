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
		User user = null;
		try {
			System.out.println("Sending message to client - " + (new Date()).toString());
			ObjectOutputStream out = new ObjectOutputStream(this.cSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(this.cSocket.getInputStream());
			
			while ((user = (User)in.readObject()) != null) {
				user.email = "Hung@gmail.com";	
				out.writeObject(user);
				System.out.println("This is my email - Server : " + user.email);
			}
	        cSocket.close();
	        
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	

}
