import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class StringProvider {
    File stringFile;
    Locale locale;
    ResourceBundle resourceBundle;

    public StringProvider(Locale locale) {
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle("strings", this.locale);
    }

    public String get(String id) {
        return resourceBundle.getString("test");
    }

    public void updateLocale(Locale locale) {
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle("strings", this.locale);
    }
}
