package test.com.gms.xms.persistence.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.persistence.dao.report.CustomerInactivityDao;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Posted from CustomerInactivityDaoTest
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerInactivityDaoTest {
    @Test
    public void getInactivityReportTest() throws DaoException {
        CustomerInactivityDao dao = new CustomerInactivityDao();
        CustomerInactivityFilter filter = new CustomerInactivityFilter();
        filter.setFranchiseList("100");
        filter.setShowCustomerOption(1);
        filter.setOrderField("customer_code");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 0, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 4, 1);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(dao.getInactivityReport(filter));
    }
}
