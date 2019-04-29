package test.com.gms.xms.persistence.customersummary;

import com.gms.xms.persistence.dao.reports.customer.CustomerSummaryDao;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerSummaryDaoTest
 * <p>
 * Author dattrinh Feb 19, 2016 11:34:30 AM
 */
public class CustomerSummaryDaoTest {
    @Test
    public void test() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        CustomerSummaryDao summaryDao = new CustomerSummaryDao();
        CustomerSummaryFilter filter = new CustomerSummaryFilter();
        filter.setServiceList("1,122,123,124");
        Calendar calendar = new GregorianCalendar(2016, 0, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2016, 0, 7);
        filter.setEndDate(calendar.getTime());
        filter.setFranchiseList("100,101");
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setExcludeGst(true);
        filter.setExcludeDuty(false);
        filter.setCarriers(Arrays.asList(new Integer[]{1, 3, 15}));
        summaryDao.prepareDataForCustomerSummary(context, filter);
        System.out.println(summaryDao.selectByFilter(filter));
    }
}