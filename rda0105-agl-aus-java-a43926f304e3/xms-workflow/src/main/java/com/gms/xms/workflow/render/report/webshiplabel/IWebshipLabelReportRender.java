package com.gms.xms.workflow.render.report.webshiplabel;

import com.gms.xms.txndb.model.reports.selfinsurance.WebshipLabelModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.WebshipLabelColumnFlagsVo;

import java.util.List;

public interface IWebshipLabelReportRender {
    public void generateHTML(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String realPath, String htmlFilePath);

    public void generatePDF(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String htmlFilePath, String pdfFilePath) throws Exception;

    public void generateXLS(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String outputFilePath);
}
