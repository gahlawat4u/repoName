package test.com.gms.xms.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.persistence.service.report.webship.IWebshipQuoteHistoryService;
import com.gms.xms.persistence.service.report.webship.WebshipQuoteHistoryServiceImp;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

;

/**
 * Posted from WebshipQuoteHistoryReportTest.java
 * <p>
 * Author dattrinh 11:34:17 AM
 */
public class WebshipQuoteHistoryReportTest {
    @Test
    public void getWebshipQuoteHistoryReportTest() throws Exception {
        IWebshipQuoteHistoryService reportService = new WebshipQuoteHistoryServiceImp();
        WebshipQuoteHistoryFilter filter = new WebshipQuoteHistoryFilter();
        Calendar calendar = new GregorianCalendar(2014, 7, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 9, 31);
        filter.setFranchiseList("101");
        filter.setCustomerCode("10100001");
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(reportService.getWebshipQuoteHistoryReport(filter));
        System.out.println("Count: " + reportService.countWebshipQuoteHistoryReport(filter));
    }
}