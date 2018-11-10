package main.java.app;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import main.java.model.EventAction;
import main.java.model.GenericDLinkedList;
import main.java.model.Goods;
import main.java.model.Order;
import main.java.model.OrderDetail;
import main.java.model.User;

import main.java.client.ClientFrameMain;
import main.java.service.ClientConnectService;

public class ClientMain {

	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	
    public static void main(String[] args) throws IOException {
        // Run client frame
        ClientFrameMain cfm = new ClientFrameMain();
    }

}
