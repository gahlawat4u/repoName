package test.com.gms.xms.cache;

import com.gms.xms.cache.SystemSettingCache;
import org.junit.*;

public class SystemSettingCacheTest {
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getValueTest() throws Exception {
        System.out.println(SystemSettingCache.getInstance().getObjectByKey("Shipment Days Limit"));
        System.out.println(SystemSettingCache.getInstance().getValueByKey("Shipment Days Limit"));
    }
}
