package view;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import util.StringProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthorizationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Twitter twitter;
	private AuthorizationCallback callback;
	private RequestToken requestToken;

	private JLabel lblInfo;
	private JButton btnGetPin;
	private JTextField txtPin;
	private JButton btnLogin;

	public AuthorizationFrame(Twitter twitter,
			final AuthorizationCallback callback) throws HeadlessException {
		this.twitter = twitter;
		this.callback = callback;

		// Setup Frame
		setTitle(StringProvider.getString("authorization.windowTitle"));
		setSize(500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 1));

		// Setup Compontents
		this.lblInfo = new JLabel(
				"<html>" + StringProvider.getString("authorization.helpText") + "</html>");
		this.lblInfo.setMaximumSize(new Dimension(500, 50));

		this.btnGetPin = new JButton(StringProvider.getString("authorization.button.getPin"));
		this.btnGetPin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				getPin();
			}
		});

		this.txtPin = new JTextField();

		this.btnLogin = new JButton(StringProvider.getString("authorization.button.login"));
		this.btnLogin.addActionListener(new ActionListener() {
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
		public void success(String accessToken, String accessTokenSecret);

		public void error(Exception e);
	}

	private void getPin() {
		try {
			this.requestToken = this.twitter.getOAuthRequestToken();
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop
					.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(new URI(requestToken.getAuthorizationURL()));
			} else {
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
		if (!this.txtPin.getText().equals("")) {
			try {
				AccessToken accessToken = this.twitter.getOAuthAccessToken(
						this.requestToken, this.txtPin.getText());
				this.callback.success(accessToken.getToken(),
						accessToken.getTokenSecret());

				JOptionPane.showMessageDialog(null,
                        StringProvider.getString("authorization.messages.success.message"),
                        StringProvider.getString("authorization.messages.success.title"),
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (TwitterException e) {
				this.callback.error(e);
			}
		} else {
			JOptionPane.showMessageDialog(null,
                    StringProvider.getString("authorization.messages.error.noPIN.message"),
					StringProvider.getString("authorization.messages.error.noPIN.title"),
                    JOptionPane.ERROR_MESSAGE);
		}
	}
}
