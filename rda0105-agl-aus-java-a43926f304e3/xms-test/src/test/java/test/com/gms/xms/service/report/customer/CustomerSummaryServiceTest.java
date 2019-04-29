package test.com.gms.xms.service.report.customer;

import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import com.gms.xms.workflow.service.customersummary.CustomerSummaryServiceImp;
import com.gms.xms.workflow.service.customersummary.ICustomerSummaryService;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerSummaryServiceTest.java
 * <p>
 * Author dattrinh 5:19:36 PM
 */
public class CustomerSummaryServiceTest {
    @Test
    public void getCustomerSummaryReportTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        ICustomerSummaryService summaryService = new CustomerSummaryServiceImp(context);
        CustomerSummaryFilter filter = new CustomerSummaryFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100,101");
        filter.setCarriers(Arrays.asList(new Integer[]{1, 3, 15}));
        filter.setServiceList("1,52,53,63,70");
        filter.setSaleRepId(26L);
        Calendar calendar = new GregorianCalendar(2014, 10, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 10, 11);
        filter.setEndDate(calendar.getTime());
        System.out.println(summaryService.selectByFilter(filter));
        System.out.println("Count: " + summaryService.countByFilter(filter));
    }
}