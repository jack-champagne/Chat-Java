package org.chawk.Chat;

import java.io.IOException;
import java.net.ServerSocket;

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

            Chat chat = new Chat(connector.accept());

            String client;
            client = chat.s.getInetAddress().toString();
            String ip = client.replace('/', ' ');

            System.out.println("Connected to " + ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


