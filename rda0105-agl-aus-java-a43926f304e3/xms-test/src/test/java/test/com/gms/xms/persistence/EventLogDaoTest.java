package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.persistence.dao.admin.EventLogDao;
import org.junit.Test;

/**
 * Posted from EventLogDaoTest
 * <p>
 * Author DatTV Oct 5, 2015
 */
public class EventLogDaoTest {

    @Test
    public void selectByCustCodeTest() throws DaoException {
        EventLogDao logDao = new EventLogDao();
        CustomerEventLogFilter filter = new CustomerEventLogFilter();
        filter.setCustomerCode("10000002");
        filter.setPage(2);
        filter.setPageSize(10);
        System.out.println(logDao.selectByCustCode(filter));
    }

    @Test
    public void countByCustCodeTest() throws DaoException {
        EventLogDao logDao = new EventLogDao();
        CustomerEventLogFilter filter = new CustomerEventLogFilter();
        filter.setCustomerCode("10000002");
        System.out.println("Count: " + logDao.countByCustCode(filter));
    }
}