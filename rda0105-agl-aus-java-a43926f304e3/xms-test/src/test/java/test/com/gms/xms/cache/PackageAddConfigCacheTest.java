package test.com.gms.xms.cache;

import com.gms.xms.cache.PackageAddConfigCache;
import org.junit.*;

public class PackageAddConfigCacheTest {
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
    public void getValue() throws Exception {
        System.out.println(PackageAddConfigCache.getInstance().getValueByKey(1, 1, "CHECK_BOX", "INSURANCE"));
    }
}
