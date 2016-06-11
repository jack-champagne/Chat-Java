import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */
class Chat extends JFrame implements ActionListener {

    private JTextField chatbox;
    private Connection con;
    // private Scanner output;
    private JButton send, quit;

    Chat(Socket s) {

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

        //ADDING OF PANEL AND START CONNECTION THREAD
        this.add(panelM);
        setVisible(true);

        this.con = new Connection(s);
        new Thread(con).start();
        System.out.println("Connection code reached");
    }

    /*
    public void playSound() {

    }
*/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            con.sendMessage(getMessage());
        }
        if (e.getSource() == quit) {
            System.out.println();
            System.exit(0);
        }
    }

    private String getMessage() {
        String message = chatbox.getText();
        chatbox.setText("");
        return message;
    }

    private class Connection implements Runnable {

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

        void sendMessage(String s) {
            out.println(s);
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
                System.out.println("Them: " + input);
            }
        }

    }

}



