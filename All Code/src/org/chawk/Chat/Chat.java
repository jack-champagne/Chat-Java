package org.chawk.Chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Chat extends JFrame implements Runnable, ActionListener {

    Socket s;
    // private Scanner output;
    private PrintWriter input;
    private JButton send, quit;

    Chat(Socket s) {

        this.s = s;
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 200);
        setLocationRelativeTo(null);

        JPanel panelM = new JPanel();

        //COMPONENTS AND BEHAVIOURS
        send = new JButton("Send");
        quit = new JButton("Quit");

        send.addActionListener(this);
        quit.addActionListener(this);

        //ADDING OF COMPONENTS
        panelM.add(send);
        panelM.add(quit);

        //ADDING OF PANEL
        this.add(panelM);

        setVisible(true);

        try {
            // this.output = new Scanner(s.getInputStream());
            this.input = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {

            System.out.println("Message sent");
            input.println("Message recieved");

        }
        if (e.getSource() == quit) {
            System.out.println("Quitting this chat session.");

        }
    }

}
