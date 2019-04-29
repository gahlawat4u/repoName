package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerPaymentDao;
import com.gms.xms.txndb.vo.CustomerPaymentVo;
import org.junit.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Posted from CustomerPaymentDaoTest
 * <p>
 * Author DatTV Date Apr 9, 2015 9:34:49 AM
 */
public class CustomerPaymentDaoTest {
    private CustomerPaymentDao paymentDao;
    private static long cusPaymentId;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        paymentDao = new CustomerPaymentDao();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insertCustomerPaymentTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerPaymentVo paymentVo = new CustomerPaymentVo();
        paymentVo.setAmount(new BigDecimal(1000L));
        paymentVo.setBankId(0L);
        paymentVo.setCheque("Test from XMS.20 dev");
        paymentVo.setCustomerCode(10000002L);
        paymentVo.setPaymentDate(new Date());
        paymentVo.setCimPaymentTransactionId("01010101010101");
        long retValue = paymentDao.insert(context, paymentVo);
        cusPaymentId = paymentVo.getCusPaymentId();
        System.out.println("New customer payment id added: " + cusPaymentId);
        assertTrue(retValue > 0);
    }

    @Test
    public void deleteCustomerPaymentTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        long retValue = paymentDao.delete(context, cusPaymentId);
        assertTrue(retValue == 1);
        System.out.println("The number of rows deleted: " + retValue);
    }
}
