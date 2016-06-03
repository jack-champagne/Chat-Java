package org.chawk.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Chat extends JFrame implements ActionListener {

    private Connection con;
    // private Scanner output;
    private JButton send, quit;
    private boolean isRunning;

    Chat(Socket s) {

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
            con = new Connection(s);
        } catch (IOException e1) {
            e1.printStackTrace();
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

    private class Connection {

        private BufferedReader in;
        private PrintWriter out;

        Connection(Socket s) throws IOException {
            this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
        }

        void sendMessage(String s) {
            out.write(s);
        }

    }

}



