import model.ConfigStore;

import org.junit.Assert;
import org.junit.Test;

import util.StringProvider;

public class StringProviderTest {

	@Test
	public void testGet() throws Exception {
		ConfigStore cs = new ConfigStore();

		cs.set("locale.language", "de");
		cs.set("locale.country", "US");

		Assert.assertTrue("String \"test.first\" should be \"Erster Test\"",
                StringProvider.getString("test.first").equals("Erster Test"));
		Assert.assertTrue("String \"test.second\" should be \"Zweiter Test\"",
				StringProvider.getString("test.second").equals("Zweiter Test"));

		cs.set("locale.language", "en");
		cs.set("locale.country", "US");

		Assert.assertTrue("String \"test.first\" should now be \"First Test\"",
				StringProvider.getString("test.first").equals("First Test"));
		Assert.assertTrue("String \"test.second\" should now be \"Second Test\"",
                StringProvider.getString("test.second").equals("Second Test"));
	}
}