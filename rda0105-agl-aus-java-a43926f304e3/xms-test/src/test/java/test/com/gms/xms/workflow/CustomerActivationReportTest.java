package test.com.gms.xms.workflow;

import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.workflow.service.report.customer.activation.CustomerActivationServiceImp;
import com.gms.xms.workflow.service.report.customer.activation.ICustomerActivationService;
import org.junit.Test;

import java.util.*;

/**
 * Posted from SCOverviewReportTest
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class CustomerActivationReportTest {
    @Test
    public void getActivationCountTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        ICustomerActivationService activationService = new CustomerActivationServiceImp(context);
        CustomerActivationFilter filter = new CustomerActivationFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        Calendar calendar = new GregorianCalendar(2014, 1, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 11, 1);
        filter.setEndDate(calendar.getTime());
        filter.setFranchiseList("100,101");
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(activationService.getActivationReport(filter));
    }
}
