package main.java.app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class TheClient {

	public static void main(String[] args) {
		String hostName = "192.168.0.14"; // replace this with the IP address of the server
		final int PORT = 8881;

		try {

			System.out.println("Trying to connect - " + hostName + ":" + PORT + " , " + (new Date()).toString());
			Socket eSocket = new Socket(hostName, PORT);
		    
			 BufferedReader in = new BufferedReader(new InputStreamReader(eSocket.getInputStream()));
			 System.out.println("Message from server: " + (new Date()).toString());
			 System.out.println(in.readLine());
			   
			 
			 PrintWriter out = new PrintWriter(eSocket.getOutputStream(), true);
		
			 Scanner s = new Scanner (System.in);
			 String ans;
			 System.out.print("Enter your name: ");
			 String name = s.nextLine();
			 
			 while(true) {
				 System.out.println("Press A to send");
				 System.out.println("else bye");
				 ans = s.nextLine();
				 
				 if (ans.equalsIgnoreCase("A")) {
					 System.out.print("Your message: ");
					 ans = s.nextLine();
					 out.println("Name: " + name + ", Message: " + ans + " [time sent: " + (new Date()).toString() + "] ");
				 } else {
					 break;
				 }
				
			 }
			 in.close();  
			 out.close();
	         eSocket.close();
	         System.out.println("Client gone");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}

}
