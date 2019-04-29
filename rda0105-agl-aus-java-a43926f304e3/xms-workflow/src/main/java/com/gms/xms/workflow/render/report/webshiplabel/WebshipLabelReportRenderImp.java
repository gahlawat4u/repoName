package com.gms.xms.workflow.render.report.webshiplabel;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.txndb.model.reports.selfinsurance.WebshipLabelModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.WebshipLabelColumnFlagsVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebshipLabelReportRenderImp extends BaseRender implements IWebshipLabelReportRender {

    public WebshipLabelReportRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHTML(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String realPath, String htmlFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", webshipLabelModels);
        data.put("columnFlags", columnFlags);
        AppUtils.template2File(htmlFilePath, false, "templates/html/report/webship_label/webship_label.ftl", data);
    }

    @Override
    public void generatePDF(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String htmlFilePath, String pdfFilePath) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", webshipLabelModels);
        data.put("columnFlags", columnFlags);
        AppUtils.template2File(htmlFilePath, false, "templates/pdf/webship_label/webship_label.ftl", data);
        AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
    }

    @Override
    public void generateXLS(List<WebshipLabelModel> webshipLabelModels, WebshipLabelColumnFlagsVo columnFlags, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("listData", webshipLabelModels);
        data.put("columnFlags", columnFlags);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/report/webship_label/webship_label.xls", data);
    }
}
