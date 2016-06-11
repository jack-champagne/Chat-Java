import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */

class Client {

    Client() {

            /*
            Tries to make a connection
             */

        String attemptIP = JOptionPane.showInputDialog(null, "Host's IP:", "Connect");

        try {

            Socket s = new Socket(InetAddress.getByName(attemptIP), 10064);

            /*
            Established connection gets logged
             */
            String client;
            client = s.getInetAddress().toString();
            String ip = client.replace('/', ' ');

            System.out.println("Connected to " + ip);

            /*
            Create a chatroom with the established connection
             */
            new Chat(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
