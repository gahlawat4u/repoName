package test.com.gms.xms.service.report.customer;

import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.workflow.service.report.customer.status.CustomerStatusServiceImp;
import com.gms.xms.workflow.service.report.customer.status.ICustomerStatusService;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerStatusReportTest
 * <p>
 * Author DatTV Dec 16, 2015
 */
public class CustomerStatusReportTest {
    @Test
    public void getCustomerStatusReportTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        ICustomerStatusService reportService = new CustomerStatusServiceImp(context);
        CustomerStatusFilter filter = new CustomerStatusFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100, 101");
        filter.setServiceList(Arrays.asList(new Integer[]{1, 3, 15, 52}));
        filter.setOrderField("start_date");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setReportDate(calendar.getTime());
        System.out.println(reportService.getWeeklyReport(filter));
        System.out.println(reportService.getMonthlyReport(filter));
    }
}