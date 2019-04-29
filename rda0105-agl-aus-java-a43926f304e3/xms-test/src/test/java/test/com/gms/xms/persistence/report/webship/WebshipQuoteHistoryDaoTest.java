package test.com.gms.xms.persistence.report.webship;

import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipQuoteHistoryDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Posted from WebshipQuoteHistoryDaoTest.java
 * <p>
 * Author dattrinh 11:18:18 AM
 */
public class WebshipQuoteHistoryDaoTest {
    @Test
    public void getWebshipQuoteHistoryTest() throws Exception {
        WebshipQuoteHistoryDao dao = new WebshipQuoteHistoryDao();
        WebshipQuoteHistoryFilter filter = new WebshipQuoteHistoryFilter();
        Calendar cal1 = new GregorianCalendar(2014, 7, 1);
        Calendar cal2 = new GregorianCalendar(2014, 9, 31);
        filter.setStartDate(cal1.getTime());
        filter.setEndDate(cal2.getTime());
        filter.setFranchiseList("101");
        filter.setCustomerCode("10100001");
        List<WebshipQuoteHistoryVo> result = dao.getWebshipQuoteHistoryReport(filter);
        System.out.println(result);
        System.out.println("Count: " + dao.countWebshipQuoteHistoryReport(filter));
    }
}
