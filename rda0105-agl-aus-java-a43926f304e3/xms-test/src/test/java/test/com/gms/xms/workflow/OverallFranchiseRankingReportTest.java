package test.com.gms.xms.workflow;

import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.workflow.service.report.ranking.IOverallFranchiseRankingService;
import com.gms.xms.workflow.service.report.ranking.OverallFranchiseRankingServiceImp;
import org.junit.Test;

import java.util.*;

/**
 * Posted from OverallFranchiseRankingReportTest.java
 * <p>
 * Author dattrinh 9:45:59 AM
 */
public class OverallFranchiseRankingReportTest {
    @Test
    public void getOverallFranchiseRankingReportTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
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
        IOverallFranchiseRankingService service = new OverallFranchiseRankingServiceImp(context);
        System.out.println(service.getOverallFranchiseRankingReport(filter));
    }
}
