import sun.reflect.Reflection;

import java.io.File;
import java.util.Properties;

public class StringProvider {
    File stringFile;
    String language;
    Properties strings;

    public StringProvider(String language) {
        this.language = language;
    }

    public String get(String id) {
        return "";
    }
}
