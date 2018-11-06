package main.java.app;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TheServer {
	
	public static final int PORT = 8881;
	public final String IP = "192.168.0.14"; 
	
	public static void main(String[] args) {
		
		InetAddress ip;
		
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("IP address of the server: " + ip.getHostAddress());
            
            System.out.println("Creating a socket - " + (new Date()).toString());
            ServerSocket serverSocket = new ServerSocket(PORT);
            
            ServerWaitingClient swc = new ServerWaitingClient(serverSocket);
            Thread tr = new Thread(swc);
            tr.start();
            
            System.out.println("Main Server done");
          
        } catch (Exception e) {
 
            System.out.println(e);
        }


	}

}
