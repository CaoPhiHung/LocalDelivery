package main.java.app;

import main.java.server.ServerFrameMain;

import javax.swing.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Date;

public class ServerMain {
	public static final int PORT =8881;
	public static String IP;
	
    public static void main(String[] args) {
    	startServer();
    }
    
    private static void startServer() {
		InetAddress ip;
		
        try {
            ip = InetAddress.getLocalHost();
            IP = ip.getHostAddress();
            JOptionPane.showMessageDialog(null, "Server address: " + IP);
            System.out.println("IP address of the server: " + ip.getHostAddress());
            System.out.println("Creating a socket - " + (new Date()).toString());
            System.out.println("Main Server done");
            
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerWaitingClient swc = new ServerWaitingClient(serverSocket);
            Thread tr = new Thread(swc);
            tr.start();

            System.out.println("Log 4");

            ServerFrameMain sfm = new ServerFrameMain();
          
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
