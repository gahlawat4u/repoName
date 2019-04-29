package test.com.gms.xms.persistence.report.webship;

import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.persistence.dao.report.webship.InvoicePendingAirbillDao;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Posted from InvoicePendingAirbillDaoTest
 * <p>
 * Author DatTV Dec 4, 2015
 */
public class InvoicePendingAirbillDaoTest {
    @Test
    public void getInvoicePendingAirbillTest() throws Exception {
        InvoicePendingAirbillDao dao = new InvoicePendingAirbillDao();
        InvoicePendingAirbillFilter filter = new InvoicePendingAirbillFilter();
        Calendar cal1 = new GregorianCalendar(2015, 4, 1);
        Calendar cal2 = new GregorianCalendar(2015, 4, 31);
        filter.setStartDate(cal1.getTime());
        filter.setEndDate(cal2.getTime());
        filter.setFranchiseList("101");
        List<InvoicePendingAirbillVo> result = dao.getInvoicePendingAirbillReport(filter);
        System.out.println(result);
        System.out.println("Count: " + dao.countInvoicePendingAirbillReport(filter));
    }
}
