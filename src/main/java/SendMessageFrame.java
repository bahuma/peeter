import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageFrame extends JFrame {
    private Twitter twitter;

    private JLabel lblMessage;
    private JTextArea txtaText;
    private JButton btnSend;
    private GridBagLayout layout = new GridBagLayout();

    public SendMessageFrame(Twitter twitter) {
        this.twitter = twitter;

        // Prepare Window
        this.setTitle("Twitter Client");
        this.setLocationRelativeTo(null);
        this.setSize(500, 400);
        this.setLayout(this.layout);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Setup Components
        this.lblMessage = new JLabel("Nachricht:");
        this.txtaText = new JTextArea();
        this.btnSend = new JButton("Senden");

        // Add components
        this.add(lblMessage, BorderLayout.PAGE_START);
        this.add(txtaText, BorderLayout.CENTER);
        this.add(btnSend, BorderLayout.PAGE_END);

        // Send Message
        this.btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        this.setVisible(true);
    }

    private void sendMessage() {
        try {
            twitter.sendDirectMessage("bahuma20", this.txtaText.getText());
        } catch (TwitterException e1) {
            e1.getMessage();
        }
    }
}
