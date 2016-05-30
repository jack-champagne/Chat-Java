package org.chawk.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Chat extends JFrame implements ActionListener {

    Socket s;
    private Connection con;
    // private Scanner output;
    private JButton send, quit;
    private boolean isRunning;

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
        JTextField chatbox = new JTextField((int) (0.0476 * this.getWidth()));

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
        this.isRunning = true;
        setVisible(true);


        try {
            Scanner in = new Scanner(s.getInputStream());
            PrintWriter out = new PrintWriter(s.getOutputStream());

            con = new Connection(in, out);
            con.run();

        } catch (IOException iOE) {
            iOE.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            con.sendMessage("Message sent to you");
        }
        if (e.getSource() == quit) {
            System.out.println("Quitting this chat session.");
            System.exit(0);

        }
    }

    private class Connection extends Thread {

        private Scanner in;
        private PrintWriter out;

        Connection(Scanner in, PrintWriter out) {
            this.in = in;
            this.out = out;
        }

        void sendMessage(String message) {
            out.println(message);
        }

        public void run() {
            while (in.hasNextLine()) {
                String message = in.nextLine();
                System.out.println(message);
            }
        }
    }

}

