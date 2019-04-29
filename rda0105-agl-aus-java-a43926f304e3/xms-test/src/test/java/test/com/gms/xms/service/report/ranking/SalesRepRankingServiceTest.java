package test.com.gms.xms.service.report.ranking;

import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.workflow.service.report.ranking.ISalesRepRankingService;
import com.gms.xms.workflow.service.report.ranking.SalesRepRankingServiceImp;
import org.junit.Test;

import java.util.*;

/**
 * Posted from SalesRepRankingServiceTest.java
 * <p>
 * Author dattrinh 11:15:59 AM
 */
public class SalesRepRankingServiceTest {
    @Test
    public void getSalesRepRankingReportTest() throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ISalesRepRankingService rankingService = new SalesRepRankingServiceImp(addInfoMap);
        SalesRepRankingFilter filter = new SalesRepRankingFilter();
        filter.setRptTxnId(UUID.randomUUID().toString());
        filter.setFranchiseList("101");
        filter.setOrderField("rank");
        filter.setOrderType(0);
        Calendar calendar = new GregorianCalendar(2014, 11, 1);
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 11, 31);
        filter.setEndDate(calendar.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println(rankingService.getSalesRepRankingReport(filter));
    }
}