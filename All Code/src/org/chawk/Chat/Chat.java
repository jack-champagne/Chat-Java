package org.chawk.Chat;

import javax.swing.*;
import java.awt.*;
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
    private JTextField chatbox;
    private boolean running;

    Chat(Socket s) {

        this.s = s;
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize((int) (0.615 * Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (0.615 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelM = new JPanel(new BorderLayout());
        JPanel chatPanel = new JPanel(new FlowLayout());

        //COMPONENTS AND BEHAVIOURS
        chatbox = new JTextField((int) (0.0476 * this.getWidth()));

        send = new JButton("Send");
        quit = new JButton("Quit");

        send.addActionListener(this);
        quit.addActionListener(this);

        //ADDING OF COMPONENTS
        panelM.add(quit, BorderLayout.NORTH);
        panelM.add(chatPanel, BorderLayout.SOUTH);
        chatPanel.add(chatbox);
        chatPanel.add(send);

        //ADDING OF PANEL AND START OF THREAD
        this.add(panelM);
        this.running = true;
        new Thread(this).start();
        setVisible(true);


        try {
            // this.output = new Scanner(s.getInputStream());
            this.input = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {

        }
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
