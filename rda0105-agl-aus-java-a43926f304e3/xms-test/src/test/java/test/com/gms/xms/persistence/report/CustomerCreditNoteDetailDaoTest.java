package test.com.gms.xms.persistence.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerCreditNoteDetailDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerCreditNoteDetailDaoTest.java
 * <p>
 * Author dattrinh 3:13:35 PM
 */
public class CustomerCreditNoteDetailDaoTest {
    @Test
    public void getCreditNoteDetailReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerCreditNoteDetailDao dao = new CustomerCreditNoteDetailDao();
        CustomerCreditNoteDetailFilter filter = new CustomerCreditNoteDetailFilter();
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
        dao.prepareCreditNoteDetailReport(context, filter);
        System.out.println(dao.getCreditNoteDetailReport(filter));
        System.out.println("Count: " + dao.checkCreditNoteDetailReport(filter));
    }
}
