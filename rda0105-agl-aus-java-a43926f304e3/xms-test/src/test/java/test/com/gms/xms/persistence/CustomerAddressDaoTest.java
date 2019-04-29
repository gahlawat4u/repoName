package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.txndb.vo.CustomerAddressFilter;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Posted from CustomerAddressDaoTest
 * <p>
 * Author DatTV Date Apr 9, 2015 9:35:54 AM
 */
public class CustomerAddressDaoTest {
    private CustomerAddressDao customerAddressDao;
    private CustomerAddressFilter filter;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        customerAddressDao = new CustomerAddressDao();
        filter = new CustomerAddressFilter();
        filter.setCustomerName("INX");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCustomerByName() throws DaoException {
        List<CustomerAddressVo> customerAddressVos = customerAddressDao.getByName(filter);
        assertTrue(customerAddressVos != null && customerAddressVos.size() > 0);
        System.out.println("Search customer by name: " + customerAddressVos.size());
    }
}
