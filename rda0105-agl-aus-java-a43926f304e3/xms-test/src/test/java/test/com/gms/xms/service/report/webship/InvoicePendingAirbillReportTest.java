package test.com.gms.xms.service.report.webship;

import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.persistence.service.report.webship.IInvoicePendingAirbillService;
import com.gms.xms.persistence.service.report.webship.InvoicePendingAirbillServiceImp;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Posted from InvoicePendingAirbillReportTest
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class InvoicePendingAirbillReportTest {
    @Test
    public void getInvoicePendingAirbillReportTest() throws Exception {
        IInvoicePendingAirbillService reportService = new InvoicePendingAirbillServiceImp();
        InvoicePendingAirbillFilter filter = new InvoicePendingAirbillFilter();
        Calendar calendar = new GregorianCalendar(2015, 4, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2015, 4, 31);
        filter.setFranchiseList("101");
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(reportService.getInvoicePendingAirbillReport(filter));
        System.out.println("Count: " + reportService.countInvoicePendingAirbillReport(filter));
    }
}