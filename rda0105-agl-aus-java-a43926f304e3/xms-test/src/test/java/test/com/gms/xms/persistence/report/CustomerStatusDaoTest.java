package test.com.gms.xms.persistence.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.persistence.dao.report.CustomerStatusDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from CustomerStatusDaoTest
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerStatusDaoTest {
    @Test
    public void prepareWeeklyReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerStatusDao dao = new CustomerStatusDao();
        CustomerStatusFilter filter = new CustomerStatusFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100, 101");
        filter.setServiceList(Arrays.asList(new Integer[]{1, 3, 15, 52}));
        filter.setOrderField("start_date");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setReportDate(calendar.getTime());
        dao.prepareWeeklyReport(context, filter);
        List<Map<String, String>> result = dao.getWeeklyReport(filter);
        System.out.println("The weekly customer status report:");
        for (Map<String, String> record : result) {
            System.out.print(String.valueOf(record.get("start_date")) + "\t");
            System.out.print(String.valueOf(record.get("end_date")) + "\t");
            System.out.print(String.valueOf(record.get("setups")) + "\t");
            System.out.print(String.valueOf(record.get("activations")) + "\t");
            for (Integer carrier : filter.getServiceList()) {
                System.out.print(String.valueOf(record.get("shipment_count_" + String.valueOf(carrier))) + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void prepareMonthlyReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        CustomerStatusDao dao = new CustomerStatusDao();
        CustomerStatusFilter filter = new CustomerStatusFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("100, 101");
        filter.setServiceList(Arrays.asList(new Integer[]{1, 3, 15, 52}));
        filter.setOrderField("start_date");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setReportDate(calendar.getTime());
        dao.prepareMonthlyReport(context, filter);
        List<Map<String, String>> result = dao.getMonthlyReport(filter);
        System.out.println("The monthly customer status report:");
        for (Map<String, String> record : result) {
            System.out.print(String.valueOf(record.get("start_date")) + "\t");
            System.out.print(String.valueOf(record.get("end_date")) + "\t");
            System.out.print(String.valueOf(record.get("setups")) + "\t");
            System.out.print(String.valueOf(record.get("activations")) + "\t");
            for (Integer carrier : filter.getServiceList()) {
                System.out.print(String.valueOf(record.get("shipment_count_" + String.valueOf(carrier))) + "\t");
            }
            System.out.println();
        }
    }
}
