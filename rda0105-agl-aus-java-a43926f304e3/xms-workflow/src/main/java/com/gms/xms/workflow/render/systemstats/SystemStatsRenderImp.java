package com.gms.xms.workflow.render.systemstats;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.admin.systemstats.SysStatsAdjustmentLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsWebshipLogFilter;
import com.gms.xms.model.CarrierPostCodeModel;
import com.gms.xms.model.CarrierSuburbModel;
import com.gms.xms.model.CarrierZoneModel;
import com.gms.xms.model.MultiZoneModel;
import com.gms.xms.model.admin.LoginLogModel;
import com.gms.xms.model.admin.WebshipLogModel;
import com.gms.xms.model.admin.systemstats.SysStatAdjustmentLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.DhlCountryModel;
import com.gms.xms.model.webship.TntInternationalAuZoneModel;
import com.gms.xms.model.webship.TntRemoteAreaModel;
import com.gms.xms.model.webship.TollIpecZoneModel;
import com.gms.xms.model.webship.toll.TollPriorityRateSheetModel;
import com.gms.xms.model.webship.toll.TollPrioritySuburbModel;
import com.gms.xms.model.webship.toll.TollRemoteAreaModel;
import com.gms.xms.persistence.dao.admin.SystemStatsDao;
import com.gms.xms.persistence.dao.admin.WebshipLogDao;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;
import com.gms.xms.txndb.vo.admin.systemstats.SysStatAdjustmentLogVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemStatsRenderImp extends BaseRender implements ISystemStatsRender {

    public SystemStatsRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlDhlCountryZone(List<DhlCountryModel> dhlCountryModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", dhlCountryModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/dhl_country_zone.ftl", data);
    }

    @Override
    public void generateXLSDhlCountryZone(List<DhlCountryModel> dhlCountryModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", dhlCountryModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/dhl_country_zone.xls", data);
    }

    @Override
    public void generateHtmlLoginLogs(List<LoginLogModel> loginLogModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", loginLogModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/login_log.ftl", data);
    }

    @Override
    public void generateXLSLoginLog(List<LoginLogModel> loginLogModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", loginLogModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/login_log.xls", data);
    }

    @Override
    public void generateHtmlTNTZone(List<CarrierZoneModel> carrierZoneModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", carrierZoneModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/tnt_zone.ftl", data);
    }

    @Override
    public void generateXLSTNTZone(List<CarrierZoneModel> carrierZoneModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", carrierZoneModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/tnt_zone.xls", data);
    }

    @Override
    public void generateHtmlTNTPostCode(List<CarrierPostCodeModel> carrierPostCodeModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", carrierPostCodeModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/tnt_postcode.ftl", data);
    }

    @Override
    public void generateXLSTNTPostCode(List<CarrierPostCodeModel> carrierPostCodeModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", carrierPostCodeModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/tnt_postcode.xls", data);
    }

    @Override
    public void generateHtmlTNTSuburb(List<CarrierSuburbModel> suburbModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", suburbModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/tnt_suburb.ftl", data);
    }

    @Override
    public void generateXLSTNTSuburb(List<CarrierSuburbModel> suburbModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", suburbModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/tnt_suburb.xls", data);
    }

    @Override
    public void generateHtmlTNTDomRemoteAreas(List<TntRemoteAreaModel> remoteAreaModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", remoteAreaModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/tnt_domestic_remote_area.ftl", data);
    }

    @Override
    public void generateXLSTNTDomRemoteAreas(List<TntRemoteAreaModel> remoteAreaModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", remoteAreaModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/tnt_dom_remote_area.xls", data);
    }

    @Override
    public void generateHtmlWebshipLogs(SysStatsWebshipLogFilter filter, String realPath, String outputFilePath) throws Exception {
        WebshipLogDao dao = new WebshipLogDao();
        filter.setPage(null);
        filter.setPageSize(null);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/webship_log/webship_log_head.ftl", data);

        int page = 1;
        int pageSize = AppConstants.APP_SETTINGS.getDefaultProcessRecordSize();
        List<WebshipLogModel> logModels;
        do {
            filter.setPage(page);
            filter.setPageSize(pageSize);
            List<WebshipLogVo> logVos = dao.getWebshipLogs(filter);
            for (WebshipLogVo webshipLogVo : logVos) {
                webshipLogVo.setChangesMode(webshipLogVo.getChangesMode().replace("@,@", "<br/>"));
            }
            logModels = ModelUtils.createListModelFromVo(logVos, WebshipLogModel.class);
            data = new HashMap<String, Object>();
            data.put("datas", logModels);
            AppUtils.template2File(outputFilePath, true, "templates/html/system_stats/webship_log/webship_log_body.ftl", data);
            page++;
        } while (logModels != null && logModels.size() > 0);

        AppUtils.template2File(outputFilePath, true, "templates/html/system_stats/webship_log/webship_log_foot.ftl", data);
    }

    @Override
    public void generateHtmlAdjustmentLogs(SysStatsAdjustmentLogFilter filter, String realPath, String outputFilePath) throws Exception {
        filter.setPage(null);
        filter.setPageSize(null);

        SystemStatsDao dao = new SystemStatsDao();

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/adjustment_log/adjustment_log_head.ftl", data);

        int page = 1;
        int pageSize = AppConstants.APP_SETTINGS.getDefaultProcessRecordSize();
        List<SysStatAdjustmentLogModel> logModels;
        do {
            filter.setPage(page);
            filter.setPageSize(pageSize);
            List<SysStatAdjustmentLogVo> logVos = dao.getAdjustmentLogs(filter);
            logModels = ModelUtils.createListModelFromVo(logVos, SysStatAdjustmentLogModel.class);
            data = new HashMap<String, Object>();
            data.put("datas", logModels);
            AppUtils.template2File(outputFilePath, true, "templates/html/system_stats/adjustment_log/adjustment_log_body.ftl", data);
            page++;
        } while (logModels != null && logModels.size() > 0);

        AppUtils.template2File(outputFilePath, true, "templates/html/system_stats/adjustment_log/adjustment_log_foot.ftl", data);
    }

    @Override
    public void generateXLSAdjustmentLog(List<SysStatAdjustmentLogModel> logModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", logModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/adjustment_log.xls", data);
    }

    public void generateHtmlTollPriorityRemoteArea(List<TollRemoteAreaModel> tollRemoteAreaModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", tollRemoteAreaModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/toll_priority_remote_area.ftl", data);
    }

    public void generateXLSTollPriorityRemoteArea(List<TollRemoteAreaModel> tollRemoteAreaModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", tollRemoteAreaModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/toll_remote_area.xls", data);
    }

    public void generateHtmlTNTInternationalZone(List<TntInternationalAuZoneModel> tntInternationalModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", tntInternationalModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/tnt_intl_zone.ftl", data);
    }

    public void generateXLSTNTInternationalZone(List<TntInternationalAuZoneModel> tntInternationalModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", tntInternationalModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/tnt_international_zone.xls", data);
    }

    public void generateHtmlTollIPECTownPostcode(List<TollIpecZoneModel> ipecZoneModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", ipecZoneModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/toll_ipec_town_postcode.ftl", data);
    }

    public void generateXLSTollIPECTownPostcode(List<TollIpecZoneModel> ipecZoneModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", ipecZoneModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/toll_ipec_town_postcode.xls", data);
    }

    public void generateHtmlTollPriorityRateSheet(List<TollPriorityRateSheetModel> tollRateSheetModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", tollRateSheetModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/toll_priority_rate_sheet.ftl", data);
    }

    public void generateXLSTollPriorityRateSheet(List<TollPriorityRateSheetModel> tollRateSheetModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", tollRateSheetModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/toll_priority_rate_sheet.xls", data);
    }

    public void generateHtmlTollPrioritySuburb(List<TollPrioritySuburbModel> tollSuburbModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", tollSuburbModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/toll_priority_suburb.ftl", data);
    }

    public void generateXLSTollPrioritySuburb(List<TollPrioritySuburbModel> tollSuburbModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", tollSuburbModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/toll_priority_suburb.xls", data);
    }

    public void generateHtmlDhlDomesticZone(List<MultiZoneModel> multiZoneModels, String realPath, String outputFilePath) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.put("realPath", realPath);
        data.put("datas", multiZoneModels);
        AppUtils.template2File(outputFilePath, false, "templates/html/system_stats/dhl_domestic_zone.ftl", data);
    }

    public void generateXLSDhlDomesticZone(List<MultiZoneModel> multiZoneModels, String outputFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("datas", multiZoneModels);
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/system_stats/dhl_domestic_zone.xls", data);
    }
}
