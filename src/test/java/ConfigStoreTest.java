import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Test
 */
public class ConfigStoreTest {
    ConfigStore conStore;

    @Before
    public void setUp() throws Exception {
        this.conStore = new ConfigStore();
    }

    @Test
    public void testGet() throws Exception {
        conStore.set("testValue", "123456789");

        Assert.assertTrue("\"testValue\" should be \"123456789\"", conStore.get("testValue").equals("123456789"));
    }

    @Test
    public void testUnset() throws Exception {
        conStore.set("testValue", "123456879");
        conStore.unset("testValue");

        Assert.assertNull("\"testValue\" should not be set", conStore.get("testValue"));
    }
}