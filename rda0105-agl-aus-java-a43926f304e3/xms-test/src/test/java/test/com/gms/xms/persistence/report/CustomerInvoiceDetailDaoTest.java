package test.com.gms.xms.persistence.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerInvoiceDetailDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerInvoiceDetailDaoTest.java
 * <p>
 * Author dattrinh 3:00:37 PM
 */
public class CustomerInvoiceDetailDaoTest {
    @Test
    public void getInvoiceDetailReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerInvoiceDetailDao dao = new CustomerInvoiceDetailDao();
        CustomerInvoiceDetailFilter filter = new CustomerInvoiceDetailFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100");
        filter.setOrderField("invoiceid");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 4, 1);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        dao.prepareInvoiceDetailReport(context, filter);
        System.out.println(dao.getInvoiceDetailReport(filter));
        System.out.println("Count: " + dao.checkInvoiceDetailReport(filter));
    }
}
