import javax.swing.*;

/**
 * Created by jackchampagne on 4/15/16.
 * Uploaded to GitHub on 5/28/16
 */

public class SimpleChatMain {

    private SimpleChatMain() {
        this.startChat();
    }

    public static void main(String[] args) {
        new SimpleChatMain();
    }

    private void startChat() {

        String s[] = new String[3];
        s[0] = "Connect";
        s[1] = "Host";
        s[2] = "Cancel";


        int selected = JOptionPane.showOptionDialog(null, "Welcome to chatterer, new an improved graphics\n will be added soon... (jk, I don't have time)",
                "Chatterer", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, s, null);

        switch (selected) {
            case 0:
                new Client();
                break;
            case 1:
                new Host();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
