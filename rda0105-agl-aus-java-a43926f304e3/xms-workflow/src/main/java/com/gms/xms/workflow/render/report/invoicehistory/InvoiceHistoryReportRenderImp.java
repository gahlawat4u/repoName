package com.gms.xms.workflow.render.report.invoicehistory;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.txndb.model.reports.selfinsurance.InvoicedAirbillModel;
import com.gms.xms.txndb.model.reports.selfinsurance.SummaryInvoicedAirbillModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.InvoiceHistoryColumnFlagsVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceHistoryReportRenderImp extends BaseRender implements IInvoiceHistoryReportRender {

    public InvoiceHistoryReportRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHTML(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String realPath, String htmlFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", invoicedAirbillModels);
        data.put("columnFlags", columnFlags);
        data.put("total", summary);
        AppUtils.template2File(htmlFilePath, false, "templates/html/report/invoice_history_report/invoice_history_report.ftl", data);
    }

    @Override
    public void generatePDF(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String htmlFilePath, String pdfFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", invoicedAirbillModels);
        data.put("columnFlags", columnFlags);
        data.put("total", summary);
        AppUtils.template2File(htmlFilePath, false, "templates/pdf/invoice_history_report/invoice_history_report.ftl", data);
        AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
    }

    @Override
    public void generateXLS(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String outputFilePath) {
        for (InvoicedAirbillModel invoicedAirbillModel : invoicedAirbillModels) {
            String charges = invoicedAirbillModel.getCharges();
            Map<String, String> replaceMap = new HashMap<>();
            replaceMap.put("<br/>", "\n");
            charges = AppUtils.replaceStringByMap(replaceMap, charges);
            invoicedAirbillModel.setCharges(charges);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", invoicedAirbillModels);
        data.put("columnFlags", columnFlags);
        data.put("total", summary);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/report/invoice_history_report/invoice_history_report.xls", data);
    }
}