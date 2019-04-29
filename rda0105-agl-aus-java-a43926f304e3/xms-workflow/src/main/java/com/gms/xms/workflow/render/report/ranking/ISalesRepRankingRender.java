package com.gms.xms.workflow.render.report.ranking;

import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;

/**
 * Posted from ISalesRepRankingRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ISalesRepRankingRender {
    public void renderSaleRepsRankingHtmlFile(SalesRepRankingFilter filter, String outputFilePath, String realPath, Boolean excludeGST) throws Exception;

    public void renderSaleRepsRankingXlsFile(SalesRepRankingFilter filter, String outPutFilePath, Boolean excludeGST) throws Exception;
}
