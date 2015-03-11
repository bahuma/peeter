package util;
import java.util.Locale;
import java.util.ResourceBundle;

import model.ConfigStore;

public class StringProvider {

	public static String getString(String id) {
        // Create a new ConfigStore
        ConfigStore cs = new ConfigStore();

        // Create a Locale with values from the ConfigStore
        Locale locale = new Locale(cs.get("locale.language"), cs.get("locale.country"));

        // Get the resource bundle for the current language
        ResourceBundle resourceBundle = ResourceBundle.getBundle("strings", locale);

        // Get the String from the resourceBundle
        return resourceBundle.getString(id);
	}

}
