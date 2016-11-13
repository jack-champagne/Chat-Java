import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Chat extends JFrame implements ActionListener, KeyListener {

    public Connection con;
    Graphics g;
    InputStream bingFile;
    AudioStream bingStream;
    private JTextField chatbox;
    // private Scanner output;
    private JButton send, quit;
    private ArrayList<String> messages = new ArrayList<String>();
    private JPanel messageArea;

    Chat(Socket s) throws IOException {

        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize((int) (0.615 * Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (0.615 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel panelM = new JPanel(new BorderLayout());
        JPanel chatPanel = new JPanel(new FlowLayout());

        //COMPONENTS AND BEHAVIOURS
        chatbox = new JTextField((int) (0.0476 * this.getWidth()));
        messageArea = new JPanel();
        //messageArea.setLayout(new GridLayout(10, 1));
        messageArea.setBackground(Color.WHITE);
        g = messageArea.getGraphics();

        send = new JButton("Send");
        quit = new JButton("Quit");

        send.addActionListener(this);
        quit.addActionListener(this);

        //ADDING OF COMPONENTS
        panelM.add(quit, BorderLayout.NORTH);
        panelM.add(chatPanel, BorderLayout.SOUTH);
        panelM.add(messageArea, BorderLayout.CENTER);
        chatPanel.add(chatbox);
        chatPanel.add(send);

        //ADDING OF PANEL AND START CONNECTION THREAD
        this.add(panelM);

        //pack();
        setVisible(true);

        this.con = new Connection(s);
        new Thread(con).start();
        System.out.println("Connection code reached");
        chatbox.addKeyListener(this);

        bingFile = new FileInputStream(new File("yuasdfasdfasdfasdj"));
        bingStream = new AudioStream(bingFile);
    }

    void updateMessages(String s) {
        messages.add(s);
        displayMessages();
    }

    private void displayMessages() {
        System.out.println("Displaying messages");
        Graphics g = messageArea.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, messageArea.getWidth(), messageArea.getHeight());
        g.setColor(Color.BLACK);
        int messageNumber = 0;
        for (int i = messages.size() - 1; i >= 0; i--) {
            messageNumber++;
            g.drawString(messages.get(i), 40, messageArea.getHeight() - 20 - (20 * messageNumber));
        }
    }

    public void playSound() {
        AudioPlayer.player.start(bingStream);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            con.sendMessage(con.getMessage());
        }

        if (e.getSource() == quit) {
            System.out.println();
            System.exit(0);
        }
    }

    /*
    Key Listener
     */
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            con.sendMessage(con.getMessage());
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    /*
    Key Listener
     */


    class Connection implements Runnable {

        private BufferedReader in;
        private PrintWriter out;

        Connection(Socket s) {
            try {
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Connection object created.");
        }

        String getMessage() {
            String message = chatbox.getText();
            chatbox.setText("");
            message = message.trim();

            if (message.length() <= 1000) {
                return message;
            } else if (message.length() > 1000) {
                messageLengthError();
                return "";
            } else {
                return "";
            }
        }

        void messageLengthError() {
            JOptionPane.showMessageDialog(messageArea, "Messages longer than 1000 characters may not be sent.");
        }

        void sendMessage(String s) {
            out.println(s);
            if (!s.isEmpty()) {
                System.out.println("Message length: " + s.length() + " characters");
                updateMessages("You: " + s + "\n\n");
            }
        }

        public void run() {
            try {
                checkMessages();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void checkMessages() throws IOException {
            String input;
            while ((input = in.readLine()) != null) {
                if (!input.isEmpty()) {
                    System.out.println("Message length: " + input.length() + " characters");
                    updateMessages("Them: " + input + "\n\n");
                    playSound();
                }
            }
        }

    }

}



