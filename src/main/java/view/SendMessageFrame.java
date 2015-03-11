package view;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.StringProvider;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendMessageFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Twitter twitter;

	private JLabel lblMessage, lblRecipient;
	private JTextField txtRecipient;
	private JTextArea txtaText;
	private JButton btnSend;
	private GridBagLayout layout = new GridBagLayout();

	public SendMessageFrame(Twitter twitter) {
		this.twitter = twitter;

		// Prepare Window
		this.setTitle(StringProvider.getString("sendmessage.windowTitle"));
		this.setLocationRelativeTo(null);
		this.setSize(500, 400);
		this.setLayout(this.layout);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Setup Components
		this.lblRecipient = new JLabel(StringProvider.getString("sendmessage.label.recipient"));
		this.txtRecipient = new JTextField();
		this.lblMessage = new JLabel(StringProvider.getString("sendmessage.label.message"));
		this.txtaText = new JTextArea();
		this.btnSend = new JButton(StringProvider.getString("sendmessage.button.send"));

		// Add components context container components x y w h ww wh
		gblAddComponent(this.getContentPane(), this.layout,
				lblRecipient, 0, 0, 1, 1, 0.5, 1.0);
		gblAddComponent(this.getContentPane(), this.layout,
				txtRecipient, 1, 0, 1, 1, 1.0, 1.0);
		gblAddComponent(this.getContentPane(), this.layout,
				lblMessage, 0, 1, 2, 1, 1.0, 1.0);
		gblAddComponent(this.getContentPane(), this.layout,
				txtaText, 0, 2, 2, 1, 1.0, 20.0);
		gblAddComponent(this.getContentPane(), this.layout,
				btnSend, 0, 3, 2, 1, 1.0, 1.0);

		// Send Message
		this.btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		this.setVisible(true);
	}

	private void sendMessage() {
		try {
			twitter.sendDirectMessage(this.txtRecipient.getText(),
					this.txtaText.getText());
		} catch (TwitterException e1) {
			e1.getMessage();
		}
	}

	public static void gblAddComponent(Container cont, GridBagLayout gbl,
			Component c, int x, int y, int width, int height, double weightx,
			double weighty) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		cont.add(c);
	}
}
