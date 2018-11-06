package main.java.app;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerWaitingClient implements Runnable {

	private ServerSocket sSocket;
	public ServerWaitingClient(ServerSocket sSocket) {
		this.sSocket = sSocket;
	}
	
	@Override
	public void run() {
		Socket clientSocket = null;
		ServerThread st;
		Thread t;
	
		try {
			while (true) {
				System.out.println("Waiting for client - " + (new Date()).toString());
				clientSocket = sSocket.accept();
				
				System.out.println("Got a client - " + (new Date()).toString());
				    
				st = new ServerThread(clientSocket) ;
				t = new Thread(st);
				t.start();  // let the thread listen to what the client wants to say
			}
			
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			System.out.println("ServerWaitingClient done");
		}
	}		


}
