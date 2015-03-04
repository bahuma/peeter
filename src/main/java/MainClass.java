import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainClass {
    public static void main(String[] args) {
        // Setup Twitter Client
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("0eebfhuBuMRIpJKcpyVHW0vqd");
        cb.setOAuthConsumerSecret("jy7pEaRL7cz03rhVFGtLhAP5YaOC1lYay0wB1UVBtn3qtLGOxz");
        cb.setOAuthAccessToken("2397473258-CdEacWH3PWF2GW5KWOUE02Jh6Tdvj8kAOWR2bdk");
        cb.setOAuthAccessTokenSecret("AZRmze08tJWlSHGBzUpdxOYZVkobYw4JyZu4DxXNgvRxN");

        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();

        ConfigStore cs = new ConfigStore();

//        new SendMessageFrame(twitter);

//        new StreamFrame(twitter);
    }
}
