package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.security.auth.callback.Callback;
import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import view.AuthorizationFrame;
import view.StreamFrame;

public class AuthorizationController {

	RequestToken requestToken;
	Twitter twitter;
	private Callback callback;

	public AuthorizationController() {
		twitter = new Twitter();
		callback = new Callback();
	}

	/*
	 * twitter, new AuthorizationFrame.AuthorizationCallback() { public void
	 * success(String accessToken, String accessTokenSecret) { // Save the token
	 * and secret in configStore configStore.set("OAuthAccessToken",
	 * accessToken); configStore.set("OAuthAccessTokenSecret",
	 * accessTokenSecret); new StreamFrame(twitter); }
	 * 
	 * public void error(Exception e) { // TODO: Handle Authorization Error
	 * e.printStackTrace(); } }
	 */

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

	public void doLogin(String pin) throws TwitterException {

		AccessToken accessToken = this.twitter.getOAuthAccessToken(
				this.requestToken, pin);
		this.callback.success(accessToken.getToken(),
				accessToken.getTokenSecret());

	}
}
