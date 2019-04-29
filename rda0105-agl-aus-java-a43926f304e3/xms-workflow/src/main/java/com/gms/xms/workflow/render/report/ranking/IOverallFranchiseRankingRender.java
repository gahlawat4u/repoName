package com.gms.xms.workflow.render.report.ranking;

import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;

/**
 * Posted from IOverallFranchiseRankingRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IOverallFranchiseRankingRender {
    public void renderOverallFranchiseRankingsHtmlFile(OverallFranchiseRankingFilter filter, String outputFilePath, String realPath, Boolean excludeGST) throws Exception;

    public void renderOverallFranchiseRankingsXlsFile(OverallFranchiseRankingFilter filter, String outPutFilePath, Boolean excludeGST) throws Exception;
}
