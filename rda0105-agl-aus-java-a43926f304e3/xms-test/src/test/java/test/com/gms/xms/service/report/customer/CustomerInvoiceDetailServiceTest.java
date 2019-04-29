package test.com.gms.xms.service.report.customer;

import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.workflow.service.report.customer.invoicedetail.CustomerInvoiceDetailServiceImp;
import com.gms.xms.workflow.service.report.customer.invoicedetail.ICustomerInvoiceDetailService;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerInvoiceDetailServiceTest.java
 * <p>
 * Author dattrinh 4:13:08 PM
 */
public class CustomerInvoiceDetailServiceTest {
    @Test
    public void getCustomerInvoiceDetailReportTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        ICustomerInvoiceDetailService detailService = new CustomerInvoiceDetailServiceImp(context);
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
        System.out.println("Count: " + detailService.getInvoiceDetailCount(filter));
        System.out.println(detailService.getInvoiceDetailReport(filter));
    }
}