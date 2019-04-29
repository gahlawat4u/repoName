package test.com.gms.xms.persistence.report.ranking;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.persistence.dao.report.ranking.OverallFranchiseRankingDao;
import org.junit.Test;

import java.util.*;

/**
 * Posted from OverallFranchiseRankingDaoTest.java
 * <p>
 * Author dattrinh 5:08:10 PM
 */
public class OverallFranchiseRankingDaoTest {
    @Test
    public void getOverallFranchiseRankingReportTest() throws DaoException {
        Map<String, String> context = new HashMap<String, String>();
        OverallFranchiseRankingDao dao = new OverallFranchiseRankingDao();
        OverallFranchiseRankingFilter filter = new OverallFranchiseRankingFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        Calendar calendar = new GregorianCalendar(2014, 11, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 11, 31);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        filter.setOrderField("franchise_code");
        filter.setOrderType(0);
        dao.prepareOverallFranchiseRankingReport(context, filter);
        System.out.println(dao.getOverallFranchiseRankingReport(filter));
        System.out.println("Count: " + dao.checkOverallFranchiseRankingReport(filter));
    }
}
