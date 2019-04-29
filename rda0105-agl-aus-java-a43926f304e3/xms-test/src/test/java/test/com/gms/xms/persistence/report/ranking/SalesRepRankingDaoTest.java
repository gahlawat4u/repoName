package test.com.gms.xms.persistence.report.ranking;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.SalesRepRankingDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from SalesRepRankingDaoTest.java
 * <p>
 * Author dattrinh 10:48:43 AM
 */
public class SalesRepRankingDaoTest {
    @Test
    public void getSalesRepRankingReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        SalesRepRankingDao dao = new SalesRepRankingDao();
        SalesRepRankingFilter filter = new SalesRepRankingFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        Calendar calendar = new GregorianCalendar(2014, 11, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 11, 31);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        filter.setOrderField("rank");
        filter.setOrderType(0);
        filter.setFranchiseList("101");
        dao.prepareSalesRepRankingReport(context, filter);
        System.out.println(dao.getSalesRepRankingReport(filter));
        System.out.println("Count: " + dao.checkSalesRepRankingReport(filter));
    }
}
