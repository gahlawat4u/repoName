package test.com.gms.xms.persistence.report;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.persistence.dao.report.CustomerWeekActivityDao;
import org.junit.Test;

/**
 * Posted from CustomerWeekActivityDaoTest.java
 * <p>
 * Author dattrinh 4:31:44 PM
 */
public class CustomerWeekActivityDaoTest {
    @Test
    public void getWeekActivityReportTest() throws DaoException {
        CustomerWeekActivityDao dao = new CustomerWeekActivityDao();
        CustomerWeekActivityFilter filter = new CustomerWeekActivityFilter();
        filter.setFranchiseList("100,101");
        filter.setOrderField("customer_code");
        filter.setOrderType(0);
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(dao.getWeekActivityReport(filter));
    }

    @Test
    public void getWeekActivityCountTest() throws DaoException {
        CustomerWeekActivityDao dao = new CustomerWeekActivityDao();
        CustomerWeekActivityFilter filter = new CustomerWeekActivityFilter();
        filter.setFranchiseList("100,101");
        System.out.println("Count: " + dao.getWeekActivityCount(filter));
    }
}
