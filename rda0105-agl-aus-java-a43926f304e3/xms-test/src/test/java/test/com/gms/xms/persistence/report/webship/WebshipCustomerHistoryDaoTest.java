package test.com.gms.xms.persistence.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipCustomerHistoryDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from WebshipCustomerHistoryDaoTest.java
 * <p>
 * Author dattrinh 11:32:44 AM
 */
public class WebshipCustomerHistoryDaoTest {
    @Test
    public void prepareWebshipCustomerHistoryPeriodTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        WebshipCustomerHistoryDao dao = new WebshipCustomerHistoryDao();
        WebshipCustomerHistoryFilter filter = new WebshipCustomerHistoryFilter();
        filter.setPeriodType(2);
        filter.setRptTxnId(UUID.randomUUID().toString());
        // Calendar cal = new GregorianCalendar(2014, 11, 1);
        Calendar cal1 = new GregorianCalendar(2014, 6, 1);
        Calendar cal2 = new GregorianCalendar(2014, 6, 8);
        filter.setStartDate(cal1.getTime());
        filter.setEndDate(cal2.getTime());
        filter.setOrderField("d_1");
        filter.setOrderType(1);
        dao.prepareWebshipCustomerHistoryPeriod(context, filter);
        dao.prepareWebshipCustomerHistoryReport(context, filter);
        List<Map<String, String>> result = dao.getWebshipCustomerHistoryReport(filter);
        List<String> columns = new ArrayList<String>();
        columns.add("customer_code");
        columns.add("customer_name");
        columns.add("sale_rep_name");
        int i = 1;
        List<Date> periodList = filter.getPeriodList();
        for (@SuppressWarnings("unused")
        Date period : periodList) {
            columns.add("d_" + String.valueOf(i));
            i++;
        }
        for (Map<String, String> map : result) {
            for (String column : columns) {
                System.out.print(String.valueOf(map.get(column)) + "\t");
            }
            System.out.println();
        }
    }
}
