import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class StringProviderTest {

    @Test
    public void testGet() throws Exception {
        StringProvider prov = new StringProvider();
        ConfigStore cs = new ConfigStore();

        cs.set("locale.language", "de");
        cs.set("locale.country", "US");

        prov.updateLocale();

        Assert.assertTrue("String \"test.first\" should be \"Erster Test\"", prov.get("test.first").equals("Erster Test"));
        Assert.assertTrue("String \"test.second\" should be \"Zweiter Test\"", prov.get("test.second").equals("Zweiter Test"));

        cs.set("locale.language", "en");
        cs.set("locale.country", "US");

        prov.updateLocale();

        Assert.assertTrue("String \"test.first\" should now be \"First Test\"", prov.get("test.first").equals("First Test"));
        Assert.assertTrue("String \"test.second\" should now be \"Second Test\"", prov.get("test.second").equals("Second Test"));
    }
}