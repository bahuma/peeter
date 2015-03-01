import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.swing.*;
import java.awt.*;

public class StreamFrame extends JFrame {
    private Twitter twitter;
    private GridLayout layout = new GridLayout(1,2);
    private JPanel pnlStream, pnlDetail;

    public StreamFrame(Twitter twitter) {
        this.twitter = twitter;

        // Prepare Window
        this.setTitle("Stream");
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
        this.setLayout(this.layout);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Setup Components
        this.pnlStream = new JPanel(new GridLayout(50, 1));
        this.pnlDetail = new JPanel(new GridBagLayout());

        // Add Panels
        this.add(this.pnlStream);
        this.add(this.pnlDetail);

        // Get Stream Items from API
        try {
            ResponseList<Status> statuses = this.twitter.getHomeTimeline();

            for(Status status : statuses) {
                addStreamStatus(status);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        // Show Frame
        this.setVisible(true);
    }

    private void addStreamStatus(Status status) {
        this.pnlStream.add(new JLabel(status.getUser().getScreenName() + ": " + status.getText()));
        this.pnlStream.revalidate();
        this.pnlStream.repaint();
        System.out.println(status.getUser().getScreenName() + ": " + status.getText());
    }
}
