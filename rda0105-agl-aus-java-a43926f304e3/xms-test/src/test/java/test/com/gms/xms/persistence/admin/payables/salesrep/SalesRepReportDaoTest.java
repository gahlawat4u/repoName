package test.com.gms.xms.persistence.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepReportDao;

import java.util.Calendar;

/**
 * Posted from SalesRepReportDaoTest
 * <p>
 * Author dattrinh Mar 23, 2016 4:39:48 PM
 */
public class SalesRepReportDaoTest {
    public static void main(String[] args) throws DaoException {
        SalesRepReportDao dao = new SalesRepReportDao();
        SalesRepReportFilter filter = new SalesRepReportFilter();
        filter.setFranchiseList("101,102");
        filter.setUserId(214L);
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 0, 1);
        filter.setStartDate(cal.getTime());
        cal.set(2014, 2, 1);
        filter.setEndDate(cal.getTime());
        System.out.println(dao.getSalesRepOverview(filter));
        System.out.println(dao.getSalesRepServiceStats(filter));
        System.out.println("Airbill count: " + dao.countSalesRepAirbillStats(filter));
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println("Airbill list: " + dao.getSalesRepAirbillStats(filter));
    }
}
