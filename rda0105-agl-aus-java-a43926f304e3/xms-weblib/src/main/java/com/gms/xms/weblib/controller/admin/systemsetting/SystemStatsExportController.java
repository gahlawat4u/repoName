package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.admin.systemstats.SysStatsAdjustmentLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsLoginLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsWebshipLogFilter;
import com.gms.xms.model.CarrierPostCodeModel;
import com.gms.xms.model.CarrierSuburbModel;
import com.gms.xms.model.CarrierZoneModel;
import com.gms.xms.model.MultiZoneModel;
import com.gms.xms.model.admin.LoginLogModel;
import com.gms.xms.model.admin.systemstats.SysStatAdjustmentLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.DhlCountryModel;
import com.gms.xms.model.webship.TntInternationalAuZoneModel;
import com.gms.xms.model.webship.TntRemoteAreaModel;
import com.gms.xms.model.webship.TollIpecZoneModel;
import com.gms.xms.model.webship.toll.TollPriorityRateSheetModel;
import com.gms.xms.model.webship.toll.TollPrioritySuburbModel;
import com.gms.xms.model.webship.toll.TollRemoteAreaModel;
import com.gms.xms.persistence.dao.*;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.persistence.dao.admin.LoginLogDao;
import com.gms.xms.persistence.dao.admin.SystemStatsDao;
import com.gms.xms.persistence.dao.admin.WebshipLogDao;
import com.gms.xms.persistence.dao.webship.*;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.LoginLogVo;
import com.gms.xms.txndb.vo.admin.systemstats.SysStatAdjustmentLogVo;
import com.gms.xms.txndb.vo.webship.TntInternationalAuZoneVo;
import com.gms.xms.txndb.vo.webship.TntRemoteAreaVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetVo;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbVo;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaVo;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.systemstats.ISystemStatsRender;
import com.gms.xms.workflow.render.systemstats.SystemStatsRenderImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class SystemStatsExportController extends SystemStatsController {
    private static final long serialVersionUID = -4752418372548801913L;
    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String doPrintDhlCountryZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("dhl_ap_code");
            DhlCountryDao dao = new DhlCountryDao();
            filter.setPage(null);
            filter.setPageSize(null);
            List<DhlCountryVo> dhlCountryVos = dao.getDhlCountryZones(filter);
            List<DhlCountryModel> dhlCountryModels = ModelUtils.createListModelFromVo(dhlCountryVos, DhlCountryModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_dhl_country_zone_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlDhlCountryZone(dhlCountryModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportDhlCountryZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("dhl_ap_code");
            DhlCountryDao dao = new DhlCountryDao();
            filter.setPage(null);
            filter.setPageSize(null);
            List<DhlCountryVo> dhlCountryVos = dao.getDhlCountryZones(filter);
            List<DhlCountryModel> dhlCountryModels = ModelUtils.createListModelFromVo(dhlCountryVos, DhlCountryModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_dhl_country_zone_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSDhlCountryZone(dhlCountryModels, outputFilePath);
            this.setFileName("dhl_country_zone.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintLoginLog() {
        try {
            SysStatsLoginLogFilter filter = this.buildLoginLogFilter("franchise_code");
            LoginLogDao dao = new LoginLogDao();
            long recordCount = dao.countLoginLogs(filter);
            this.validateTotalRecords(recordCount);
            filter.setPage(null);
            filter.setPageSize(null);
            // Get list of records of login logs.
            List<LoginLogVo> loginLogVos = dao.getLoginLogs(filter);
            List<LoginLogModel> loginLogModels = ModelUtils.createListModelFromVo(loginLogVos, LoginLogModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_login_log_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlLoginLogs(loginLogModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportLoginLog() {
        try {
            SysStatsLoginLogFilter filter = this.buildLoginLogFilter("franchise_code");
            LoginLogDao dao = new LoginLogDao();
            long recordCount = dao.countLoginLogs(filter);
            this.validateTotalRecords(recordCount);
            filter.setPage(null);
            filter.setPageSize(null);
            // Get list of records of login logs.
            List<LoginLogVo> loginLogVos = dao.getLoginLogs(filter);
            List<LoginLogModel> loginLogModels = ModelUtils.createListModelFromVo(loginLogVos, LoginLogModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_login_log_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSLoginLog(loginLogModels, outputFilePath);
            this.setFileName("login_log.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintWebshipLog() {
        try {

            SysStatsWebshipLogFilter filter = this.buildWebshipLogFilter("action_date");
            WebshipLogDao dao = new WebshipLogDao();
            Long recordCount = dao.countWebshipLogs(filter); // Check max
            // records can be
            // exported
            this.validateTotalRecords(recordCount);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_webship_log_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlWebshipLogs(filter, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));

            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintAdjustmentLog() {
        try {
            SysStatsAdjustmentLogFilter filter = this.buildAdjustmentLogFilter("adjustmentid");
            SystemStatsDao dao = new SystemStatsDao();
            Long recordCount = dao.countAdjustmentLogs(filter);
            this.validateTotalRecords(recordCount);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_webship_log_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlAdjustmentLogs(filter, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportAdjustmentLog() {
        try {
            // Get filter.
            SysStatsAdjustmentLogFilter filter = this.buildAdjustmentLogFilter("adjustmentid");
            filter.setPage(null);
            filter.setPageSize(null);
            SystemStatsDao dao = new SystemStatsDao();
            long recordCount = dao.countAdjustmentLogs(filter);
            this.validateTotalRecords(recordCount);
            List<SysStatAdjustmentLogVo> logVos = dao.getAdjustmentLogs(filter);
            List<SysStatAdjustmentLogModel> logModels = ModelUtils.createListModelFromVo(logVos, SysStatAdjustmentLogModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_adjustment_log_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSAdjustmentLog(logModels, outputFilePath);
            this.setFileName("adjustment_log.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTNTZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("zone_code");
            filter.setPage(null);
            filter.setPageSize(null);
            CarrierZoneDao dao = new CarrierZoneDao();
            long recordCount = dao.countTntZones(filter);
            this.validateTotalRecords(recordCount);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_zone_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            List<CarrierZoneVo> carrierZoneVos = dao.getTntZones(filter);
            List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
            render.generateHtmlTNTZone(carrierZoneModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTNTZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("zone_code");
            filter.setPage(null);
            filter.setPageSize(null);
            CarrierZoneDao dao = new CarrierZoneDao();
            long recordCount = dao.countTntZones(filter);
            this.validateTotalRecords(recordCount);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_zone_" + AppUtils.createMessageReference() + ".xls";
            List<CarrierZoneVo> carrierZoneVos = dao.getTntZones(filter);
            List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
            render.generateXLSTNTZone(carrierZoneModels, outputFilePath);
            this.setFileName("tnt_zone.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTNTPostCode() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("zone_code");
            filter.setPage(null);
            filter.setPageSize(null);
            CarrierPostCodeDao dao = new CarrierPostCodeDao();
            long recordCount = dao.countTntPostCodes(filter);
            this.validateTotalRecords(recordCount);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_postcode_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            List<CarrierPostCodeVo> carrierPostCodeVos = dao.getTntPostCodes(filter);
            List<CarrierPostCodeModel> carrierPostCodeModels = ModelUtils.createListModelFromVo(carrierPostCodeVos, CarrierPostCodeModel.class);
            render.generateHtmlTNTPostCode(carrierPostCodeModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTNTPostCode() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("zone_code");
            filter.setPage(null);
            filter.setPageSize(null);
            CarrierPostCodeDao dao = new CarrierPostCodeDao();
            long recordCount = dao.countTntPostCodes(filter);
            this.validateTotalRecords(recordCount);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_postcode_" + AppUtils.createMessageReference() + ".xls";
            List<CarrierPostCodeVo> carrierPostCodeVos = dao.getTntPostCodes(filter);
            List<CarrierPostCodeModel> carrierPostCodeModels = ModelUtils.createListModelFromVo(carrierPostCodeVos, CarrierPostCodeModel.class);
            render.generateXLSTNTPostCode(carrierPostCodeModels, outputFilePath);
            this.setFileName("tnt_postcode.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTNTSuburb() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("suburb_name");
            CarrierSuburbDao dao = new CarrierSuburbDao();
            long recordCount = dao.countTntSuburbs(filter);
            this.validateTotalRecords(recordCount);
            filter.setPage(null);
            filter.setPageSize(null);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_suburb_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            List<CarrierSuburbVo> suburbVos = dao.getTntSuburbs(filter);
            List<CarrierSuburbModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, CarrierSuburbModel.class);
            render.generateHtmlTNTSuburb(suburbModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTNTSuburb() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("suburb_name");
            filter.setPage(null);
            filter.setPageSize(null);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_suburb_" + AppUtils.createMessageReference() + ".xls";
            CarrierSuburbDao dao = new CarrierSuburbDao();
            // long recordCount = dao.countTntSuburbs(filter);
            // this.validateTotalRecords(recordCount);
            List<CarrierSuburbVo> suburbVos = dao.getTntSuburbs(filter);
            List<CarrierSuburbModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, CarrierSuburbModel.class);
            render.generateXLSTNTSuburb(suburbModels, outputFilePath);
            this.setFileName("tnt_suburb.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTNTDomRemoteAreas() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("postcode");
            TntRemoteAreaDao dao = new TntRemoteAreaDao();
            long recordCount = dao.countTntRemoteAreas(filter);
            this.validateTotalRecords(recordCount);
            filter.setPage(null);
            filter.setPageSize(null);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_dom_remote_area_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            List<TntRemoteAreaVo> tntRemoteAreaVos = dao.getTntRemoteAreas(filter);
            List<TntRemoteAreaModel> tntRemoteAreaModels = ModelUtils.createListModelFromVo(tntRemoteAreaVos, TntRemoteAreaModel.class);
            render.generateHtmlTNTDomRemoteAreas(tntRemoteAreaModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTNTDomRemoteAreas() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("postcode");
            filter.setPage(null);
            filter.setPageSize(null);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_dom_remote_area_" + AppUtils.createMessageReference() + ".xls";
            TntRemoteAreaDao dao = new TntRemoteAreaDao();
            long recordCount = dao.countTntRemoteAreas(filter);
            this.validateTotalRecords(recordCount);
            List<TntRemoteAreaVo> remoteAreaVos = dao.getTntRemoteAreas(filter);
            List<TntRemoteAreaModel> remoteAreaModels = ModelUtils.createListModelFromVo(remoteAreaVos, TntRemoteAreaModel.class);
            render.generateXLSTNTDomRemoteAreas(remoteAreaModels, outputFilePath);
            this.setFileName("tnt_dom_remote_area.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTollPriorityRemoteArea() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("postcode");
            filter.setPage(null);
            filter.setPageSize(null);
            TollRemoteAreaDao dao = new TollRemoteAreaDao();
            long recordCount = dao.countTollRemoteAreas(filter);
            this.validateTotalRecords(recordCount);
            List<TollRemoteAreaVo> remoteAreaVos = dao.getTollRemoteAreas(filter);
            List<TollRemoteAreaModel> remoteAreaModels = ModelUtils.createListModelFromVo(remoteAreaVos, TollRemoteAreaModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_remote_area_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSTollPriorityRemoteArea(remoteAreaModels, outputFilePath);
            this.setFileName("toll_remote_area.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTollPriorityRemoteArea() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("postcode");
            filter.setPage(null);
            filter.setPageSize(null);
            TollRemoteAreaDao dao = new TollRemoteAreaDao();
            long recordCount = dao.countTollRemoteAreas(filter);
            this.validateTotalRecords(recordCount);
            List<TollRemoteAreaVo> remoteAreaVos = dao.getTollRemoteAreas(filter);
            List<TollRemoteAreaModel> remoteAreaModels = ModelUtils.createListModelFromVo(remoteAreaVos, TollRemoteAreaModel.class);
            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_remote_area_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlTollPriorityRemoteArea(remoteAreaModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTNTInternationalZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("original_file_countryname");
            filter.setPage(null);
            filter.setPageSize(null);
            TntInternationalAuZoneDao dao = new TntInternationalAuZoneDao();
            long recordCount = dao.countTntIntlZones(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TntInternationalAuZoneVo> auZoneVos = dao.getTntIntlZones(filter);
            List<TntInternationalAuZoneModel> auZoneModels = ModelUtils.createListModelFromVo(auZoneVos, TntInternationalAuZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_international_zone_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlTNTInternationalZone(auZoneModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTNTInternationalZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("original_file_countryname");
            filter.setPage(null);
            filter.setPageSize(null);
            TntInternationalAuZoneDao dao = new TntInternationalAuZoneDao();
            long recordCount = dao.countTntIntlZones(filter);
            this.validateTotalRecords(recordCount);

            List<TntInternationalAuZoneVo> auZoneVos = dao.getTntIntlZones(filter);
            List<TntInternationalAuZoneModel> auZoneModels = ModelUtils.createListModelFromVo(auZoneVos, TntInternationalAuZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_tnt_international_zone_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSTNTInternationalZone(auZoneModels, outputFilePath);
            this.setFileName("tnt_international_zone.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTollIPECTownPostcode() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("business");
            filter.setPage(null);
            filter.setPageSize(null);

            TollIpecZoneDao dao = new TollIpecZoneDao();
            long recordCount = dao.countTollIpecZones(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollIpecZoneVo> ipecZoneVos = dao.getTollIpecZones(filter);
            List<TollIpecZoneModel> ipecZoneModels = ModelUtils.createListModelFromVo(ipecZoneVos, TollIpecZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_ipec_town_postcode_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlTollIPECTownPostcode(ipecZoneModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTollIPECTownPostcode() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("business");
            filter.setPage(null);
            filter.setPageSize(null);

            TollIpecZoneDao dao = new TollIpecZoneDao();
            long recordCount = dao.countTollIpecZones(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollIpecZoneVo> ipecZoneVos = dao.getTollIpecZones(filter);
            List<TollIpecZoneModel> ipecZoneModels = ModelUtils.createListModelFromVo(ipecZoneVos, TollIpecZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_ipec_town_postcode_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSTollIPECTownPostcode(ipecZoneModels, outputFilePath);
            this.setFileName("toll_ipec_town_postcode.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTollPrioritySuburb() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("business_unit");
            filter.setPage(null);
            filter.setPageSize(null);

            TollPrioritySuburbDao dao = new TollPrioritySuburbDao();
            long recordCount = dao.countTollPrioritySuburbs(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollPrioritySuburbVo> suburbVos = dao.getTollPrioritySuburbs(filter);
            List<TollPrioritySuburbModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, TollPrioritySuburbModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_priority_suburb_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlTollPrioritySuburb(suburbModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTollPrioritySuburb() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("business_unit");
            filter.setPage(null);
            filter.setPageSize(null);

            TollPrioritySuburbDao dao = new TollPrioritySuburbDao();
            long recordCount = dao.countTollPrioritySuburbs(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollPrioritySuburbVo> tollSuburbVos = dao.getTollPrioritySuburbs(filter);
            List<TollPrioritySuburbModel> tollSuburbModels = ModelUtils.createListModelFromVo(tollSuburbVos, TollPrioritySuburbModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_priority_suburb_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSTollPrioritySuburb(tollSuburbModels, outputFilePath);
            this.setFileName("toll_priority_suburb.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintTollPriorityRateSheet() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("customer");
            filter.setPage(null);
            filter.setPageSize(null);

            TollPriorityRateSheetDao dao = new TollPriorityRateSheetDao();
            long recordCount = dao.countTollPriorityRateSheets(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollPriorityRateSheetVo> tollRateSheetVos = dao.getTollPriorityRateSheets(filter);
            List<TollPriorityRateSheetModel> tollRateSheetModels = ModelUtils.createListModelFromVo(tollRateSheetVos, TollPriorityRateSheetModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_priority_rate_sheet_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlTollPriorityRateSheet(tollRateSheetModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportTollPriorityRateSheet() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("customer");
            filter.setPage(null);
            filter.setPageSize(null);

            TollPriorityRateSheetDao dao = new TollPriorityRateSheetDao();
            long recordCount = dao.countTollPriorityRateSheets(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<TollPriorityRateSheetVo> tollRateSheetVos = dao.getTollPriorityRateSheets(filter);
            List<TollPriorityRateSheetModel> tollRateSheetModels = ModelUtils.createListModelFromVo(tollRateSheetVos, TollPriorityRateSheetModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_toll_priority_rate_sheet_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSTollPriorityRateSheet(tollRateSheetModels, outputFilePath);
            this.setFileName("toll_priority_rate_sheet.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doPrintDhlDomesticZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("origin_name");
            filter.setPage(null);
            filter.setPageSize(null);

            MultiZoneDao dao = new MultiZoneDao();
            long recordCount = dao.countDhlDomZones(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<MultiZoneVo> zoneVos = dao.getDhlDomZones(filter);
            List<MultiZoneModel> zoneModels = ModelUtils.createListModelFromVo(zoneVos, MultiZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_dhl_domestic_zone_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlDhlDomesticZone(zoneModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportDhlDomesticZone() {
        try {
            SysStatsFilter filter = this.buildSysStatFilter("origin_name");
            filter.setPage(null);
            filter.setPageSize(null);

            MultiZoneDao dao = new MultiZoneDao();
            long recordCount = dao.countDhlDomZones(filter);
            this.validateTotalRecords(recordCount);
            // Get list of records toll remote areas.
            List<MultiZoneVo> zoneVos = dao.getDhlDomZones(filter);
            List<MultiZoneModel> zoneModels = ModelUtils.createListModelFromVo(zoneVos, MultiZoneModel.class);

            ISystemStatsRender render = new SystemStatsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/sysstats_dhl_domestic_zone_" + AppUtils.createMessageReference() + ".xls";
            render.generateXLSDhlDomesticZone(zoneModels, outputFilePath);
            this.setFileName("dhl_domestic_zone.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void validateTotalRecords(Long recordCount) throws CustomException {
        Long maxProcess = Long.valueOf(AppConstants.APP_SETTINGS.getMaxExportProcessRecord());
        if (recordCount > maxProcess) {
            String errMess = "The total records is too large. <br/>Maximum records can be exported is {maxRecord}.";
            throw new CustomException(errMess.replace("{maxRecord}", String.valueOf(maxProcess)));
        }
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }
}
