package test.com.gms.xms.service.report.customer;

import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.workflow.service.report.customer.invoicedetail.CustomerInvoiceDetailServiceImp;
import com.gms.xms.workflow.service.report.customer.invoicedetail.ICustomerInvoiceDetailService;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerCreditNoteDetailServiceTest.java
 * <p>
 * Author dattrinh 4:13:31 PM
 */
public class CustomerCreditNoteDetailServiceTest {
    @Test
    public void getCustomerCreditNoteDetailReportTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        ICustomerInvoiceDetailService detailService = new CustomerInvoiceDetailServiceImp(context);
        CustomerCreditNoteDetailFilter filter = new CustomerCreditNoteDetailFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100");
        filter.setOrderField("credit_note_id");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 4, 1);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println("Count: " + detailService.getCreditNoteDetailCount(filter));
        System.out.println(detailService.getCreditNoteDetailReport(filter));
    }
}