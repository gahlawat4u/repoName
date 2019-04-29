package com.gms.xms.workflow.render.importtotals;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.model.invoicing.ImportTotalReportModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoicing.IImportTotalReportService;
import com.gms.xms.persistence.service.invoicing.ImportTotalReportServiceImp;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.apache.commons.lang.StringEscapeUtils;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportTotalsReportRenderImp extends BaseRender implements IImportTotalsReportRender {
    public ImportTotalsReportRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(ImportTotalReportFilter filter, String outputFilePath) throws Exception {
        IImportTotalReportService reportService = new ImportTotalReportServiceImp();
        List<ImportTotalReportVo> importTotalReportVos = reportService.getImportTotalReportByFilter(filter);
        List<ImportTotalReportModel> importTotalReportModels = ModelUtils.createListModelFromVo(importTotalReportVos, ImportTotalReportModel.class);
        ImportTotalReportVo summaryVo = reportService.sumImportTotalReportByFilter(filter);
        ImportTotalReportModel summaryModel = ModelUtils.createModelFromVo(summaryVo, ImportTotalReportModel.class);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("listData", importTotalReportModels);
        data.put("total", summaryModel);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/import_total_report/import_total_report.ftl", data);
    }

    @Override
    public void generateXlsFile(ImportTotalReportFilter filter, String outputFilePath) throws Exception {
        IImportTotalReportService reportService = new ImportTotalReportServiceImp();
        List<ImportTotalReportVo> importTotalReportVos = reportService.getImportTotalReportByFilter(filter);
        List<ImportTotalReportModel> importTotalReportModels = ModelUtils.createListModelFromVo(importTotalReportVos, ImportTotalReportModel.class);
        ImportTotalReportVo summaryVo = reportService.sumImportTotalReportByFilter(filter);
        ImportTotalReportModel summaryModel = ModelUtils.createModelFromVo(summaryVo, ImportTotalReportModel.class);
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(getAddInfo()));
        data.putVar("listData", importTotalReportModels);
        data.putVar("total", summaryModel);
        data.putVar("currencySymbol", StringEscapeUtils.unescapeHtml(SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL)));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/import_totals_report/import_totals_report.xls", data);
    }
}
