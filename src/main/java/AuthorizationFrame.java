import twitter4j.Twitter;

import javax.swing.*;
import java.awt.*;

public class AuthorizationFrame extends JFrame {
    private Twitter twitter;
    private AuthorizationCallback callback;

    private JLabel lblInfo;
    private JButton btnGetPin;
    private JTextField txtPin;
    private JButton btnLogin;

    public AuthorizationFrame(Twitter twitter, AuthorizationCallback callback) throws HeadlessException {
        this.twitter = twitter;
        this.callback = callback;

        // Setup Frame
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        // Setup Compontents
        this.lblInfo = new JLabel("<html>Du musst dich einloggen, um diese Anwendung nutzen zu können. Fordern Sie eine PIN an, und geben Sie diese im Feld unten ein.</html>");
        this.lblInfo.setMaximumSize(new Dimension(500, 50));
        this.btnGetPin = new JButton("Get PIN");
        this.txtPin = new JTextField();
        this.btnLogin = new JButton("Login");

        // Add Components
        this.add(lblInfo);
        this.add(btnGetPin);
        this.add(txtPin);
        this.add(btnLogin);

        // Show Frame
        setVisible(true);
    }

    public interface AuthorizationCallback {
        public void success(String AccessToken, String AccessSecret);
        public void error(Exception e);
    }

}
