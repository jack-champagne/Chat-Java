import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Host {

    Host() {

            /*
            Tries to make a connection
             */

        try {

            ServerSocket connector = new ServerSocket(10064);
            System.out.println("Attempting to catch connection.");
            /*
            Established connection gets logged
             */


            /*
            Create a chatroom with the established connection
             */

            Socket s = connector.accept();

            String client;
            client = s.getInetAddress().toString();
            String ip = client.replace('/', ' ');

            System.out.println("Connected to " + ip);
            new Chat(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


