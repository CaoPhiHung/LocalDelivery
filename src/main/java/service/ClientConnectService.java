package main.java.service;

import main.java.app.ServerMain;
import main.java.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class ClientConnectService {

    private User user;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public User connectServer(String username, String password) throws IOException {
        System.out.println("Trying to connect - " + ServerMain.IP + ":" + ServerMain.PORT
                + " , " + (new Date()).toString());

        try {
            socket = new Socket(ServerMain.IP, ServerMain.PORT);
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

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex)
        {
            ex.getMessage();
        }

//        socket.close();
        return null;

    }
}
