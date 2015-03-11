package view;

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
	private JTextField txtPin;
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
				authorizationController.getPin();
			}
		});

		this.txtPin = new JTextField();

		this.btnLogin = new JButton(
				this.stringProvider.get("authorization.login"));
		this.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				authorizationController.doLogin();
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
}
