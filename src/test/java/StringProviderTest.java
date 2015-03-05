import junit.framework.TestCase;

import java.util.Locale;

public class StringProviderTest extends TestCase {

    public void testGet() throws Exception {
        StringProvider prov = new StringProvider();

        assertEquals("String \"test.first\" should be \"Erster Test\"", "Erster Test", prov.get("test.first"));
        assertEquals("String \"test.second\" should be \"Zweiter Test\"", "Zweiter Test", prov.get("test.second"));
    }

    public void testUpdateLocale() throws Exception {
        StringProvider prov = new StringProvider();
        ConfigStore cs = new ConfigStore();

        assertEquals("String \"test.first\" should be \"Erster Test\"", "Erster Test", prov.get("test.first"));
        assertEquals("String \"test.second\" should be \"Zweiter Test\"", "Zweiter Test", prov.get("test.second"));

        cs.set("locale.language", "en");
        cs.set("locale.country", "US");

        prov.updateLocale();

        assertEquals("String \"test.first\" should now be \"First Test\"", "First Test", prov.get("test.first"));
        assertEquals("String \"test.second\" should now be \"Second Test\"", "Second Test", prov.get("test.second"));
    }
}