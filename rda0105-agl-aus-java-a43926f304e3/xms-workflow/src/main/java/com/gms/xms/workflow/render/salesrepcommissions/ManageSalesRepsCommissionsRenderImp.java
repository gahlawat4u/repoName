package com.gms.xms.workflow.render.salesrepcommissions;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.model.payables.salesrep.SalesRepAirbillStatModel;
import com.gms.xms.model.payables.salesrep.SalesRepOverviewModel;
import com.gms.xms.model.payables.salesrep.SalesRepServiceStatModel;
import com.gms.xms.model.payables.salesrep.SalesRepSettingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.payables.salesrep.ISalesRepService;
import com.gms.xms.persistence.service.admin.payables.salesrep.SalesRepServiceImp;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepAirbillStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepOverviewVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepServiceStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepSettingVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageSalesRepsCommissionsRenderImp extends BaseRender implements IManageSalesRepsComissionsRender {

    private ISalesRepService salesRepService = new SalesRepServiceImp();

    public ManageSalesRepsCommissionsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(SalesRepReportFilter overviewFilter, SalesRepReportFilter serviceFilter, SalesRepReportFilter invoiceFilter, String outputFilePath, String realPath) throws Exception {
        SalesRepOverviewVo overviewVo = salesRepService.getSalesRepOverview(overviewFilter);
        SalesRepOverviewModel overviewModel = ModelUtils.createModelFromVo(overviewVo, SalesRepOverviewModel.class);

        Double totalMargin = 0D;
        Double totalPayout = 0D;
        List<SalesRepServiceStatVo> serviceStatVos = salesRepService.getSalesRepServiceStats(serviceFilter);
        for (SalesRepServiceStatVo salesRepServiceStatVo : serviceStatVos) {
            totalMargin += salesRepServiceStatVo.getActualMargin();
        }
        List<SalesRepServiceStatModel> serviceStatModels = ModelUtils.createListModelFromVo(serviceStatVos, SalesRepServiceStatModel.class);

        List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(invoiceFilter);
        List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);

        SalesRepSettingVo repSettingVo = salesRepService.getSalesRepSettingsBySaleRepId(overviewVo.getSalesRepId());
        SalesRepSettingModel repSettingModel = ModelUtils.createModelFromVo(repSettingVo, SalesRepSettingModel.class);
        totalPayout = totalMargin * (repSettingVo.getPercentPayout() / 100);
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        String startDate = DateUtils.convertDateToString(overviewFilter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        String endDate = DateUtils.convertDateToString(overviewFilter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Map<String, Object> data = new HashMap<>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("overview", overviewModel);
        data.put("service", serviceStatModels);
        data.put("invoice", airbillStatModels);
        data.put("startDate", startDate);
        data.put("endDate", endDate);
        data.put("totalMargin", formatter.format(totalMargin));
        data.put("totalPayout", formatter.format(totalPayout));
        data.put("saleReps", repSettingModel);
        data.put("realPath", realPath);
        AppUtils.template2File(outputFilePath, false, "templates/html/manage_sale_reps_commissions/manage_sale_reps_commissions.ftl", data);
    }

    @Override
    public void generateHtmlViewPdfFile(SalesRepReportFilter overviewFilter, SalesRepReportFilter serviceFilter, SalesRepReportFilter invoiceFilter, String outputFilePath) throws Exception {
        SalesRepOverviewVo overviewVo = salesRepService.getSalesRepOverview(overviewFilter);
        SalesRepOverviewModel overviewModel = ModelUtils.createModelFromVo(overviewVo, SalesRepOverviewModel.class);

        Double totalMargin = 0D;
        Double totalPayout = 0D;
        List<SalesRepServiceStatVo> serviceStatVos = salesRepService.getSalesRepServiceStats(serviceFilter);
        for (SalesRepServiceStatVo salesRepServiceStatVo : serviceStatVos) {
            totalMargin += salesRepServiceStatVo.getActualMargin();
        }
        List<SalesRepServiceStatModel> serviceStatModels = ModelUtils.createListModelFromVo(serviceStatVos, SalesRepServiceStatModel.class);

        List<SalesRepAirbillStatVo> airbillStatVos = salesRepService.getSalesRepAirbillStats(invoiceFilter);
        List<SalesRepAirbillStatModel> airbillStatModels = ModelUtils.createListModelFromVo(airbillStatVos, SalesRepAirbillStatModel.class);

        SalesRepSettingVo repSettingVo = salesRepService.getSalesRepSettingsBySaleRepId(overviewVo.getSalesRepId());
        SalesRepSettingModel repSettingModel = ModelUtils.createModelFromVo(repSettingVo, SalesRepSettingModel.class);
        totalPayout = totalMargin * (repSettingVo.getPercentPayout() / 100);
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
        String startDate = DateUtils.convertDateToString(overviewFilter.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        String endDate = DateUtils.convertDateToString(overviewFilter.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Map<String, Object> data = new HashMap<>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.put("overview", overviewModel);
        data.put("service", serviceStatModels);
        data.put("invoice", airbillStatModels);
        data.put("startDate", startDate);
        data.put("endDate", endDate);
        data.put("totalMargin", formatter.format(totalMargin));
        data.put("totalPayout", formatter.format(totalPayout));
        data.put("saleReps", repSettingModel);
        AppUtils.template2File(outputFilePath, false, "templates/pdf/manage_sale_reps_commissions/manage_sale_reps_commissions.ftl", data);
    }

    @Override
    public void generateDetailsHtml(List<SalesRepAirbillStatModel> airbillStatModels, String outputFilePath, String realPath) {
        Map<String, Object> data = new HashMap<>();
        data.put("listData", airbillStatModels);
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/manage_sale_reps_commissions/manage_sale_reps_commissions_details.ftl", data);
    }

    @Override
    public void generateDetailsXls(List<SalesRepAirbillStatModel> airbillStatModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        data.putVar("listData", airbillStatModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/manage_sale_reps_commissions/manage_sale_reps_commissions_details.xls", data);
    }

}
