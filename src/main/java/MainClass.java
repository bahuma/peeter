import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.*;
import java.security.AccessControlContext;

public class MainClass {
    public static void main(String[] args) {
        // App Credentials
        final String OAUTH_CONSUMER_KEY = "0eebfhuBuMRIpJKcpyVHW0vqd";
        final String OAUTH_CONSUMER_SECRET = "jy7pEaRL7cz03rhVFGtLhAP5YaOC1lYay0wB1UVBtn3qtLGOxz";

        // Initialize a config store
        final ConfigStore configStore = new ConfigStore();

        // Initialize the twitter object
        final Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET);

        // Get User Access Token from the Store
        String oAuthAccessToken = configStore.get("OAuthAccessToken");
        String oAuthAccessTokenSecret = configStore.get("OAuthAccessTokenSecret");

        // Check if Access Token already exists
        if (oAuthAccessToken != null && oAuthAccessTokenSecret != null) {

            // Set the Access Tokens in the Twitter Client
            twitter.setOAuthAccessToken(new AccessToken(oAuthAccessToken, oAuthAccessTokenSecret));

        } else {

            // Prompt the User to login
            new AuthorizationFrame(twitter, new AuthorizationFrame.AuthorizationCallback() {
                @Override
                public void success(String accessToken, String accessTokenSecret) {
                    // Save the token and secret in configStore
                    configStore.set("OAuthAccessToken", accessToken);
                    configStore.set("OAuthAccessTokenSecret", accessToken);
                }

                @Override
                public void error(Exception e) {
                    // TODO: Handle Authorization Error
                    e.printStackTrace();
                }
            });
        }

//        new SendMessageFrame(twitter);

//        new StreamFrame(twitter);
    }
}
