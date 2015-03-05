import junit.framework.TestCase;

public class ConfigStoreTest extends TestCase {
    ConfigStore conStore;

    public void setUp() throws Exception {
        super.setUp();
        this.conStore = new ConfigStore();
    }

    public void testGet() throws Exception {
        conStore.set("testValue", "123456789");

        assertEquals("\"testValue\" should be \"123456789\"", "123456789", conStore.get("testValue"));
    }

    public void testUnset() throws Exception {
        conStore.set("testValue", "123456879");
        conStore.unset("testValue");

        assertEquals("\"testValue\" should not be set", null, conStore.get("testValue"));
    }
}