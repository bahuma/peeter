package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class AuthorizationController {
	
	RequestToken requestToken;
	Twitter twitter;
	public AuthorizationController(){
		twitter = new Twitter();
	}
	
	public void getPin() {
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
	
	public void doLogin() {
		if (!this.txtPin.getText().equals("")) {
			try {
				AccessToken accessToken = this.twitter.getOAuthAccessToken(
						this.requestToken, this.txtPin.getText());
				this.callback.success(accessToken.getToken(),
						accessToken.getTokenSecret());

				JOptionPane.showMessageDialog(null, this.stringProvider
						.get("authorization.messages.success.message"),
						this.stringProvider
								.get("authorization.messages.success.title"),
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (TwitterException e) {
				this.callback.error(e);
			}
		} else {
			JOptionPane.showMessageDialog(null, this.stringProvider
					.get("authorization.messages.error.noPIN.message"),
					this.stringProvider
							.get("authorization.messages.error.noPIN.title"),
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
