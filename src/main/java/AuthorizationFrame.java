import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthorizationFrame extends JFrame {
    private Twitter twitter;
    private AuthorizationCallback callback;
    private RequestToken requestToken;

    private JLabel lblInfo;
    private JButton btnGetPin;
    private JTextField txtPin;
    private JButton btnLogin;

    public AuthorizationFrame(Twitter twitter, final AuthorizationCallback callback) throws HeadlessException {
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
        this.btnGetPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getPin();
            }
        });

        this.txtPin = new JTextField();

        this.btnLogin = new JButton("Login");
        this.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                doLogin();
            }
        });

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

    private void getPin() {
        try {
            this.requestToken = this.twitter.getOAuthRequestToken();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(requestToken.getAuthorizationURL()));
            }
            else
            {
                // TODO: Bring the user to copy and paste the link
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doLogin() {
        try {
            AccessToken accessToken = this.twitter.getOAuthAccessToken(this.requestToken, this.txtPin.getText());
            this.callback.success(accessToken.getToken(), accessToken.getTokenSecret());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
