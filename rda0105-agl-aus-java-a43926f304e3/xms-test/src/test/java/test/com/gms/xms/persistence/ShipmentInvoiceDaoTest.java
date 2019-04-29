package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.ShipmentInvoiceDao;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Posted from ShipmentInvoiceDaoTest
 * <p>
 * Author DatTV Date Apr 9, 2015 9:59:46 AM
 */
public class ShipmentInvoiceDaoTest {
    private ShipmentInvoiceDao shipmentInvoiceDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        shipmentInvoiceDao = new ShipmentInvoiceDao();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectShipmentInvoiceByInvoiceCodeTest() throws DaoException {
        List<ShipmentInvoiceVo> shipmentInvoices = shipmentInvoiceDao.selectByInvoiceCode("10000002OH25");
        assertTrue(shipmentInvoices != null && shipmentInvoices.size() > 0);
        for (ShipmentInvoiceVo shipmentInvoice : shipmentInvoices) {
            System.out.println(shipmentInvoice.toString());
        }
    }
}
