import junit.framework.TestCase;

import java.util.Locale;

public class StringProviderTest extends TestCase {

    private StringProvider prov;

    public void setUp() throws Exception {
        super.setUp();
        this.prov = new StringProvider(new Locale("de", "DE"));
    }

    public void testGet() throws Exception {
        assertEquals("String \"test.first\" should be \"Erster Test\"", "Erster Test", this.prov.get("test.first"));
        assertEquals("String \"test.second\" should be \"Zweiter Test\"", "Zweiter Test", this.prov.get("test.second"));
    }

    public void testUpdateLocale() throws Exception {
        this.prov.updateLocale(new Locale("en", "US"));

        assertEquals("String \"test.first\" should be \"First Test\"", "First Test", this.prov.get("test.first"));
        assertEquals("String \"test.second\" should be \"Second Test\"", "Second Test", this.prov.get("test.second"));
    }
}