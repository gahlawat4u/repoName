package test.com.gms.xms.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.persistence.service.report.webship.IWebshipCustomerDetailService;
import com.gms.xms.persistence.service.report.webship.WebshipCustomerDetailServiceImp;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Posted from WebshipCustomerDetailReportTest
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailReportTest {
    @Test
    public void getWebshipCustomerDetailReportTest() throws Exception {
        IWebshipCustomerDetailService reportService = new WebshipCustomerDetailServiceImp();
        WebshipCustomerDetailFilter filter = new WebshipCustomerDetailFilter();
        Calendar calendar = new GregorianCalendar(2014, 10, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 10, 9);
        filter.setFranchiseList("101");
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(reportService.getWebshipCustomerDetailReport(filter));
        System.out.println("Count: " + reportService.countWebshipCustomerDetailReport(filter));
    }
}