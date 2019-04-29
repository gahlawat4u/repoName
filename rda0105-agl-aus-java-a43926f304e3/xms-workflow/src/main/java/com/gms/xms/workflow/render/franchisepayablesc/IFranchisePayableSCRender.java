package com.gms.xms.workflow.render.franchisepayablesc;

import com.gms.xms.txndb.vo.FranchisePayableFilter;

public interface IFranchisePayableSCRender {
    public void renderPayableHtmlReport(FranchisePayableFilter filter, String outputFilePath) throws Exception;

    public void renderPayableHtmlReportForPdf(FranchisePayableFilter filter, String outputFilePath) throws Exception;
}
