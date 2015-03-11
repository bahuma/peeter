package view;

import twitter4j.TwitterException;
import util.StringProvider;

import javax.swing.*;

import controller.AuthorizationController;

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

	private AuthorizationController authorizationController;
	private AuthorizationCallback callback;

	private JLabel lblInfo;
	private JButton btnGetPin;
	private final JTextField txtPin;
	private JButton btnLogin;

	private StringProvider stringProvider = new StringProvider();

	public AuthorizationFrame() throws HeadlessException {

		this.authorizationController = new AuthorizationController();

		// Setup Frame
		setTitle(this.stringProvider.get("authorization.windowTitle"));
		setSize(500, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 1));

		// Setup Compontents
		this.lblInfo = new JLabel("<html>"
				+ this.stringProvider.get("authorization.helpText") + "</html>");
		this.lblInfo.setMaximumSize(new Dimension(500, 50));

		this.btnGetPin = new JButton(
				this.stringProvider.get("authorization.getPin"));
		this.btnGetPin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				if (!txtPin.getText().equals("")) {
					authorizationController.getPin();
				} else {
					JOptionPane.showMessageDialog(
							null,
							stringProvider
									.get("authorization.messages.error.noPIN.message"),
							stringProvider
									.get("authorization.messages.error.noPIN.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.txtPin = new JTextField();

		this.btnLogin = new JButton(
				this.stringProvider.get("authorization.login"));
		this.btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				try {
				authorizationController.doLogin(txtPin.getText());
				dispose();
				JOptionPane
				.showMessageDialog(null, stringProvider
						.get("authorization.messages.success.message"),
						stringProvider
								.get("authorization.messages.success.title"),
						JOptionPane.INFORMATION_MESSAGE);
			} catch (TwitterException e) {
				callback.error(e);
			}
			}});
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
}
