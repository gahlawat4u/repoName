package com.gms.xms.workflow.service.report.ranking;

import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;

import java.util.List;

/**
 * Posted from IOverallFranchiseRankingService.java
 * <p>
 * Author dattrinh 9:05:40 AM
 */
public interface IOverallFranchiseRankingService {
    public List<OverallFranchiseRankingVo> getOverallFranchiseRankingReport(OverallFranchiseRankingFilter filter) throws Exception;

    public long getOverallFranchiseRankingCount(OverallFranchiseRankingFilter filter) throws Exception;
}
