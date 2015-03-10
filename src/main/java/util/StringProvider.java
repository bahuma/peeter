package util;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import model.ConfigStore;

public class StringProvider {
	File stringFile;
	Locale locale;
	ResourceBundle resourceBundle;
	ConfigStore cs = new ConfigStore();

	public StringProvider() {
		this.locale = new Locale(this.cs.get("locale.language"),
				this.cs.get("locale.country"));
		this.resourceBundle = ResourceBundle.getBundle("strings", this.locale);
	}

	public String get(String id) {
		return resourceBundle.getString(id);
	}

	public void updateLocale() {
		this.locale = new Locale(this.cs.get("locale.language"),
				this.cs.get("locale.country"));
		this.resourceBundle = ResourceBundle.getBundle("strings", this.locale);
	}
}
