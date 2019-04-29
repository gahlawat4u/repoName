package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.webship.invoices.AirbillDetailVo;
import com.gms.xms.txndb.vo.webship.invoices.GSTSummaryVo;
import com.gms.xms.txndb.vo.webship.invoices.TaxInvoiceVo;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Posted from InvoiceDaoServiceTest
 * <p>
 * Author DatTV Date Apr 9, 2015 9:36:12 AM
 */
public class InvoiceDaoServiceTest {
    private InvoiceDao invoiceDaoService;
    private InvoiceFilter filter;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        invoiceDaoService = new InvoiceDao();
        filter = new InvoiceFilter();
        filter.setCustomerCode(10000002L);
        filter.setInvoiceCode("10000002OL17");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getOutstandingInvoicesCountByCustCodeTest() throws DaoException {
        long count = invoiceDaoService.getOutstandingInvoicesCountByCustCode(filter);
        System.out.println(count);
        assertTrue(count > 0);
    }

    @Test
    public void getOutstandingInvoicesByCustOrInvoiceCodeTest() throws DaoException {
        List<InvoiceVo> invoiceVos = invoiceDaoService.getOutstandingInvoicesByCustOrInvoiceCode(filter);
        assertTrue(invoiceVos != null && invoiceVos.size() > 0);
        System.out.println("Outstanding invoices: " + invoiceVos.size());
    }

    @Test
    public void getTaxInvoiceByCodeTest() throws DaoException {
        TaxInvoiceVo invoiceVo = invoiceDaoService.getTaxInvoiceByCode("10000001OL24");
        assertTrue(invoiceVo != null);
        System.out.println(invoiceVo);
    }

    @Test
    public void getGSTSummaryByInvoiceIdTest() throws DaoException {
        GSTSummaryVo gstSummaryVo = invoiceDaoService.getGSTSummaryByInvoiceId(81609L);
        assertTrue(gstSummaryVo != null);
        System.out.println(gstSummaryVo);
    }

    @Test
    public void getAirbillListByInvoiceCodeTest() throws DaoException {
        List<AirbillDetailVo> airbillDetailVos = invoiceDaoService.getAirbillListByInvoiceCode("10000001OL24");
        assertTrue(airbillDetailVos != null && airbillDetailVos.size() > 0);
        System.out.println(airbillDetailVos);
    }
}
