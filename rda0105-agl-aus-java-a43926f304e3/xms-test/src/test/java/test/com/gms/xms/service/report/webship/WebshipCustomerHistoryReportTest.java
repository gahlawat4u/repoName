package test.com.gms.xms.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.workflow.service.report.webship.IWebshipCustomerHistoryService;
import com.gms.xms.workflow.service.report.webship.WebshipCustomerHistoryServiceImp;
import org.junit.Test;

import java.util.*;

/**
 * Posted from WebshipCustomerHistoryReportTest.java
 * <p>
 * Author dattrinh 2:36:54 PM
 */
public class WebshipCustomerHistoryReportTest {
    @Test
    public void getWebshipCustomerHistoryReportTest() throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        IWebshipCustomerHistoryService reportService = new WebshipCustomerHistoryServiceImp(addInfoMap);
        WebshipCustomerHistoryFilter filter = new WebshipCustomerHistoryFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setOrderField("customer_code");
        filter.setOrderType(1);
        Calendar calendar = new GregorianCalendar(2014, 6, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 6, 7);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        filter.setPeriodType(1);
        System.out.println(reportService.getWebshipCustomerHistoryReport(filter));
        System.out.println("Count: " + reportService.getWebshipCustomerHistoryCount(filter));
    }
}