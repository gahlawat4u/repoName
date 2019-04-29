package com.gms.xms.workflow.service.report.ranking;

import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;

import java.util.List;

/**
 * Posted from ISalesRepRankingService.java
 * <p>
 * Author dattrinh 11:10:44 AM
 */
public interface ISalesRepRankingService {
    public List<SalesRepRankingVo> getSalesRepRankingReport(SalesRepRankingFilter filter) throws Exception;

    public long getSalesRepRankingCount(SalesRepRankingFilter filter) throws Exception;
}
