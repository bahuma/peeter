import java.io.File;
import java.io.IOException;

public class ConfigStore {
    private File configFile;

    public ConfigStore() {

        try {

            this.configFile = new File(System.getProperty("user.home") + "/.eazytweet/config.properties");

            if (!this.configFile.isFile()) {
                // Create File if not exists
                this.configFile.getParentFile().mkdirs();
                this.configFile.createNewFile();
            }

        } catch (IOException e) {
            // TODO: Handle exception
            e.printStackTrace();
        }
    }

    public String get(String name) {
        return "";
    }

    public String set(String name, String value) {
        return "";
    }
}
