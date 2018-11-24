package main.java.service;

import main.java.app.ClientMain;
import main.java.app.ServerMain;
import main.java.client.ClientFrameMain;
import main.java.model.*;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class ClientConnectService {

    private User user;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public User connectServer(String username, String password) throws IOException {
        System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT
                + " , " + (new Date()).toString());

        try {
            // Pop ip address
            String inputServer = JOptionPane.
                    showInputDialog(null,
                            "Please enter server IP address",
                            "Server Input",JOptionPane.INFORMATION_MESSAGE);
            ClientMain.IP_address = inputServer;

            socket = new Socket();
            socket.connect(new InetSocketAddress(ClientMain.IP_address, ServerMain.PORT),3000);

//            socket.setSoTimeout(3000);
            if(socket != null)
            {
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
                    this.user = event.user;
                    socket.close();
                    return this.user;
                }else
                {
                    socket.close();
                }
            }
        }catch (UnknownHostException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

//        socket.close();
        return null;

    }
}
