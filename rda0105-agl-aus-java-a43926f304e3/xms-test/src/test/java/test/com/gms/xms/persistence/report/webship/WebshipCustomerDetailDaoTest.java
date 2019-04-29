package test.com.gms.xms.persistence.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipCustomerDetailDao;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Posted from WebshipCustomerDetailDaoTest
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailDaoTest {
    @Test
    public void getWebshipCustomerDetailTest() throws Exception {
        WebshipCustomerDetailDao dao = new WebshipCustomerDetailDao();
        WebshipCustomerDetailFilter filter = new WebshipCustomerDetailFilter();
        Calendar cal1 = new GregorianCalendar(2014, 10, 1);
        Calendar cal2 = new GregorianCalendar(2014, 10, 9);
        filter.setStartDate(cal1.getTime());
        filter.setEndDate(cal2.getTime());
        filter.setFranchiseList("101");
        List<WebshipCustomerDetailVo> result = dao.getWebshipCustomerDetailReport(filter);
        System.out.println(result);
        System.out.println("Count: " + dao.countWebshipCustomerDetailReport(filter));
    }
}
