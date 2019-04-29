package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.systemstats.SysStatsAdjustmentLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsLoginLogFilter;
import com.gms.xms.filter.admin.systemstats.SysStatsWebshipLogFilter;
import com.gms.xms.model.*;
import com.gms.xms.model.admin.LoginLogModel;
import com.gms.xms.model.admin.WebshipLogModel;
import com.gms.xms.model.admin.systemstats.RecentImportModel;
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
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.LoginLogVo;
import com.gms.xms.txndb.vo.admin.WebshipLogVo;
import com.gms.xms.txndb.vo.admin.systemstats.IdsVo;
import com.gms.xms.txndb.vo.admin.systemstats.RecentImportVo;
import com.gms.xms.txndb.vo.admin.systemstats.SysStatAdjustmentLogVo;
import com.gms.xms.txndb.vo.webship.TntInternationalAuZoneVo;
import com.gms.xms.txndb.vo.webship.TntRemoteAreaVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneVo;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetVo;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbVo;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Posted from Aug 31, 2016 1:51:50 PM
 * <p>
 * Author dattrinh
 */
public class SystemStatsController extends AdminJsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String startDate;
    private String endDate;
    private String airbillNumber;
    private String action;
    private String requestType;
    private String status;
    private String loginType;

    private Paging<DhlCountryModel> dhlZones;
    private Paging<LoginLogModel> loginLogs;
    private Paging<WebshipLogModel> webshipLogs;
    private Paging<SysStatAdjustmentLogModel> adjustmentLogs;
    private Paging<CarrierZoneModel> tntZones;
    private Paging<CarrierPostCodeModel> tntPostCodes;
    private Paging<CarrierSuburbModel> tntSuburbs;
    private Paging<TntRemoteAreaModel> tntRemoteAreas;
    private Paging<MultiZoneModel> dhlDomZones;
    private Paging<TollPrioritySuburbModel> tollPrioritySuburbs;
    private Paging<TollPriorityRateSheetModel> tollPriorityRateSheets;
    private Paging<TollRemoteAreaModel> tollRemoteAreas;
    private Paging<TntInternationalAuZoneModel> tntIntlZones;
    private Paging<TollIpecZoneModel> tollIpecZones;
    private Map<String, String> searchOptions;
    private Paging<RecentImportModel> recentImports;
    private List<String> actions;
    private List<String> requestTypes;
    private Map<String, String> statusList;

    public String show() {
        try {
            preparePageSizes();
            prepareSearchOptions();
            prepareStatusList();
            prepareActions();
            prepareRequestTypes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doRecentImports() {
        try {
            preparePageSizes();
            getRecentImportsStats();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doDhlCountryZones() {
        try {
            preparePageSizes();
            getDhlCountryZones();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doLoginLogs() {
        try {
            preparePageSizes();
            searchLoginLogs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doWebshipLogs() {
        try {
            preparePageSizes();
            searchWebshipLogs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doAdjustmentLogs() {
        try {
            preparePageSizes();
            searchAdjustmentLogs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTntZones() {
        try {
            preparePageSizes();
            searchTntZones();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTntPostCodes() {
        try {
            preparePageSizes();
            searchTntPostCodes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTntSuburbs() {
        try {
            preparePageSizes();
            searchTntSuburbs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTntDomRemoteAreas() {
        try {
            preparePageSizes();
            searchTntRemoteAreas();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doDhlDomZones() {
        try {
            preparePageSizes();
            searchDhlDomZones();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTollPrioritySuburbs() {
        try {
            preparePageSizes();
            searchTollPrioritySuburbs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTollPriorityRateSheets() {
        try {
            preparePageSizes();
            searchTollPriorityRateSheets();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTollPriorityRemoteAreas() {
        try {
            preparePageSizes();
            searchTollPriorityRemoteAreas();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTntIntlZones() {
        try {
            preparePageSizes();
            searchTntIntlZones();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doTollIpecZones() {
        try {
            preparePageSizes();
            searchTollIpecZones();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void searchTollIpecZones() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("business");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TollIpecZoneDao dao = new TollIpecZoneDao();
        long recordCount = dao.countTollIpecZones(filter);
        // Build paging object.
        Paging<TollIpecZoneModel> paging = new Paging<TollIpecZoneModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records toll remote areas.
        List<TollIpecZoneVo> ipecZoneVos = dao.getTollIpecZones(filter);
        List<TollIpecZoneModel> ipecZoneModels = ModelUtils.createListModelFromVo(ipecZoneVos, TollIpecZoneModel.class);
        paging.setRecords(ipecZoneModels);
        // Set toll remote areas.
        this.setTollIpecZones(paging);
    }

    protected void searchTntIntlZones() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("original_file_countryname");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TntInternationalAuZoneDao dao = new TntInternationalAuZoneDao();
        long recordCount = dao.countTntIntlZones(filter);
        // Build paging object.
        Paging<TntInternationalAuZoneModel> paging = new Paging<TntInternationalAuZoneModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records toll remote areas.
        List<TntInternationalAuZoneVo> auZoneVos = dao.getTntIntlZones(filter);
        List<TntInternationalAuZoneModel> auZoneModels = ModelUtils.createListModelFromVo(auZoneVos, TntInternationalAuZoneModel.class);
        paging.setRecords(auZoneModels);
        // Set toll remote areas.
        this.setTntIntlZones(paging);
    }

    protected void searchTollPriorityRemoteAreas() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("postcode");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TollRemoteAreaDao dao = new TollRemoteAreaDao();
        long recordCount = dao.countTollRemoteAreas(filter);
        // Build paging object.
        Paging<TollRemoteAreaModel> paging = new Paging<TollRemoteAreaModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records toll remote areas.
        List<TollRemoteAreaVo> suburbVos = dao.getTollRemoteAreas(filter);
        List<TollRemoteAreaModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, TollRemoteAreaModel.class);
        paging.setRecords(suburbModels);
        // Set toll remote areas.
        this.setTollRemoteAreas(paging);
    }

    protected void searchTollPriorityRateSheets() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("customer");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TollPriorityRateSheetDao dao = new TollPriorityRateSheetDao();
        long recordCount = dao.countTollPriorityRateSheets(filter);
        // Build paging object.
        Paging<TollPriorityRateSheetModel> paging = new Paging<TollPriorityRateSheetModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records toll priority rate sheets.
        List<TollPriorityRateSheetVo> suburbVos = dao.getTollPriorityRateSheets(filter);
        List<TollPriorityRateSheetModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, TollPriorityRateSheetModel.class);
        paging.setRecords(suburbModels);
        // Set toll priority rate sheets.
        this.setTollPriorityRateSheets(paging);
    }

    protected void searchTollPrioritySuburbs() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("business_unit");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TollPrioritySuburbDao dao = new TollPrioritySuburbDao();
        long recordCount = dao.countTollPrioritySuburbs(filter);
        // Build paging object.
        Paging<TollPrioritySuburbModel> paging = new Paging<TollPrioritySuburbModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records toll priority suburbs.
        List<TollPrioritySuburbVo> suburbVos = dao.getTollPrioritySuburbs(filter);
        List<TollPrioritySuburbModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, TollPrioritySuburbModel.class);
        paging.setRecords(suburbModels);
        // Set toll priority suburbs.
        this.setTollPrioritySuburbs(paging);
    }

    protected void searchDhlDomZones() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("origin_name");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        MultiZoneDao dao = new MultiZoneDao();
        long recordCount = dao.countDhlDomZones(filter);
        // Build paging object.
        Paging<MultiZoneModel> paging = new Paging<MultiZoneModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records dhl dom zones.
        List<MultiZoneVo> zoneVos = dao.getDhlDomZones(filter);
        List<MultiZoneModel> zoneModels = ModelUtils.createListModelFromVo(zoneVos, MultiZoneModel.class);
        paging.setRecords(zoneModels);
        // Set dhl dom zones.
        this.setDhlDomZones(paging);
    }

    protected void searchTntRemoteAreas() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("postcode");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        TntRemoteAreaDao dao = new TntRemoteAreaDao();
        long recordCount = dao.countTntRemoteAreas(filter);
        // Build paging object.
        Paging<TntRemoteAreaModel> paging = new Paging<TntRemoteAreaModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records tnt remote areas.
        List<TntRemoteAreaVo> remoteAreaVos = dao.getTntRemoteAreas(filter);
        List<TntRemoteAreaModel> remoteAreaModels = ModelUtils.createListModelFromVo(remoteAreaVos, TntRemoteAreaModel.class);
        paging.setRecords(remoteAreaModels);
        // Set tnt remote areas.
        this.setTntRemoteAreas(paging);
    }

    protected void searchTntSuburbs() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("suburb_name");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        CarrierSuburbDao dao = new CarrierSuburbDao();
        long recordCount = dao.countTntSuburbs(filter);
        // Build paging object.
        Paging<CarrierSuburbModel> paging = new Paging<CarrierSuburbModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records tnt suburbs.
        List<CarrierSuburbVo> suburbVos = dao.getTntSuburbs(filter);
        List<CarrierSuburbModel> suburbModels = ModelUtils.createListModelFromVo(suburbVos, CarrierSuburbModel.class);
        paging.setRecords(suburbModels);
        // Set tnt suburbs.
        this.setTntSuburbs(paging);
    }

    protected void searchTntPostCodes() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("zone_code");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        CarrierPostCodeDao dao = new CarrierPostCodeDao();
        long recordCount = dao.countTntPostCodes(filter);
        // Build paging object.
        Paging<CarrierPostCodeModel> paging = new Paging<CarrierPostCodeModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records post codes.
        List<CarrierPostCodeVo> carrierPostCodeVos = dao.getTntPostCodes(filter);
        List<CarrierPostCodeModel> carrierPostCodeModels = ModelUtils.createListModelFromVo(carrierPostCodeVos, CarrierPostCodeModel.class);
        paging.setRecords(carrierPostCodeModels);
        // Set post codes.
        this.setTntPostCodes(paging);
    }

    protected void searchTntZones() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("zone_code");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        CarrierZoneDao dao = new CarrierZoneDao();
        long recordCount = dao.countTntZones(filter);
        // Build paging object.
        Paging<CarrierZoneModel> paging = new Paging<CarrierZoneModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records carrier zones.
        List<CarrierZoneVo> loginLogVos = dao.getTntZones(filter);
        List<CarrierZoneModel> loginLogModels = ModelUtils.createListModelFromVo(loginLogVos, CarrierZoneModel.class);
        paging.setRecords(loginLogModels);
        // Set carrier zones.
        this.setTntZones(paging);
    }

    protected void searchLoginLogs() throws Exception {
        // Get filter.
        SysStatsLoginLogFilter filter = this.buildLoginLogFilter("franchise_code");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        LoginLogDao dao = new LoginLogDao();
        long recordCount = dao.countLoginLogs(filter);
        // Build paging object.
        Paging<LoginLogModel> paging = new Paging<LoginLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of login logs.
        List<LoginLogVo> loginLogVos = dao.getLoginLogs(filter);
        List<LoginLogModel> loginLogModels = ModelUtils.createListModelFromVo(loginLogVos, LoginLogModel.class);
        paging.setRecords(loginLogModels);
        // Set login logs.
        this.setLoginLogs(paging);
    }

    protected void searchWebshipLogs() throws Exception {
        // Get filter.
        SysStatsWebshipLogFilter filter = this.buildWebshipLogFilter("action_date");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        WebshipLogDao dao = new WebshipLogDao();
        long recordCount = dao.countWebshipLogs(filter);
        // Build paging object.
        Paging<WebshipLogModel> paging = new Paging<WebshipLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of webship logs.
        List<WebshipLogVo> logVos = dao.getWebshipLogs(filter);
        for (WebshipLogVo webshipLogVo : logVos) {
            webshipLogVo.setChangesMode(webshipLogVo.getChangesMode().replace("@,@", "<br/>"));
        }
        List<WebshipLogModel> logModels = ModelUtils.createListModelFromVo(logVos, WebshipLogModel.class);
        paging.setRecords(logModels);
        // Set webship logs.
        this.setWebshipLogs(paging);
    }

    protected void searchAdjustmentLogs() throws Exception {
        // Get filter.
        SysStatsAdjustmentLogFilter filter = this.buildAdjustmentLogFilter("adjustmentid");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        SystemStatsDao dao = new SystemStatsDao();
        long recordCount = dao.countAdjustmentLogs(filter);
        // Build paging object.
        Paging<SysStatAdjustmentLogModel> paging = new Paging<SysStatAdjustmentLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of adjustment logs.
        List<SysStatAdjustmentLogVo> logVos = dao.getAdjustmentLogs(filter);
        List<SysStatAdjustmentLogModel> logModels = ModelUtils.createListModelFromVo(logVos, SysStatAdjustmentLogModel.class);
        paging.setRecords(logModels);
        // Set adjustment logs.
        this.setAdjustmentLogs(paging);
    }

    protected SysStatsLoginLogFilter buildLoginLogFilter(String defaultOrderField) throws Exception {
        SysStatsLoginLogFilter filter = new SysStatsLoginLogFilter();
        BeanUtils.copyProperties(filter, this.buildSysStatFilter(defaultOrderField));
        // Set login type.
        this.loginType = StringUtils.isBlank(this.getLoginType()) ? "0" : this.getLoginType();
        if (!"0".equalsIgnoreCase(this.getLoginType()) && !"1".equalsIgnoreCase(this.getLoginType())) {
            throw new CustomException("Invalid login type.");
        }
        filter.setLoginType(Integer.valueOf(this.getLoginType()));
        // Set start date.
        Date startDate = null;
        try {
            startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (startDate == null) {
                throw new CustomException("Please choose start date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (endDate == null) {
                throw new CustomException("Please choose end date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        filter.setEndDate(endDate);
        // Check date range.
        if (endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
        return filter;
    }

    protected SysStatsAdjustmentLogFilter buildAdjustmentLogFilter(String defaultOrderField) throws Exception {
        SysStatsAdjustmentLogFilter filter = new SysStatsAdjustmentLogFilter();
        BeanUtils.copyProperties(filter, this.buildSysStatFilter(defaultOrderField));
        // Set status.
        this.status = StringUtils.isBlank(this.getStatus()) ? "-1" : this.getStatus();
        try {
            Integer.valueOf(this.getStatus());
        } catch (Exception e) {
            throw new CustomException("Invalid status.");
        }
        filter.setStatus(Integer.valueOf(this.getStatus()));
        // Set request type.
        filter.setRequestType("-1".equalsIgnoreCase(this.getRequestType()) ? null : this.getRequestType());
        // Set airbill number.
        filter.setAirbillNumber(this.getAirbillNumber());
        // Set start date.
        Date startDate = null;
        try {
            startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (startDate == null) {
                throw new CustomException("Please choose start date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (endDate == null) {
                throw new CustomException("Please choose end date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        filter.setEndDate(endDate);
        // Check date range.
        if (endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
        return filter;
    }

    protected SysStatsWebshipLogFilter buildWebshipLogFilter(String defaultOrderField) throws Exception {
        SysStatsWebshipLogFilter filter = new SysStatsWebshipLogFilter();
        BeanUtils.copyProperties(filter, this.buildSysStatFilter(defaultOrderField));
        // Set action.
        filter.setAction(this.getAction());
        // Set filter collections.
        filter.setFilterCollections(this.buildFilterCollections(airbillNumber));
        // Set start date.
        Date startDate = null;
        try {
            startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (startDate == null) {
                throw new CustomException("Please choose start date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (endDate == null) {
                throw new CustomException("Please choose end date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        filter.setEndDate(endDate);
        // Check date range.
        if (endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
        return filter;
    }

    protected void getDhlCountryZones() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("dhl_ap_code");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        DhlCountryDao dao = new DhlCountryDao();
        long recordCount = dao.countDhlCountryZones(filter);
        // Build paging object.
        Paging<DhlCountryModel> paging = new Paging<DhlCountryModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of dhl country/zones.
        List<DhlCountryVo> dhlCountryVos = dao.getDhlCountryZones(filter);
        List<DhlCountryModel> dhlCountryModels = ModelUtils.createListModelFromVo(dhlCountryVos, DhlCountryModel.class);
        paging.setRecords(dhlCountryModels);
        // Set dhl country/zones.
        this.setDhlZones(paging);
    }

    protected void prepareActions() {
        List<String> actions = new ArrayList<String>();
        actions.add("Cancel Pickup");
        actions.add("Delete");
        actions.add("Import");
        actions.add("Insert");
        actions.add("Login");
        actions.add("Modify Pickup Address");
        actions.add("Modify Pickup Collection");
        actions.add("Pickup Address");
        actions.add("Pickup Collection");
        actions.add("Receiver Address");
        actions.add("Sender Address");
        actions.add("Shipment Detail");
        actions.add("Shipment Request");
        actions.add("Update");
        actions.add("Void");
        this.setActions(actions);
    }

    protected void prepareStatusList() {
        Map<String, String> statusList = new LinkedHashMap<String, String>();
        statusList.put("1", "Submitted");
        statusList.put("2", "Pending");
        statusList.put("3", "Disputed");
        statusList.put("4", "Approved");
        statusList.put("5", "Disputed Denied");
        statusList.put("6", "Deleted");
        this.setStatusList(statusList);
    }

    protected void prepareRequestTypes() throws Exception {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        List<String> requestTypes = adjustmentDao.getRequestTypes();
        this.setRequestTypes(requestTypes);
    }

    protected void getRecentImportsStats() throws Exception {
        // Get filter.
        SysStatsFilter filter = this.buildSysStatFilter("import_date");
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        SystemStatsDao dao = new SystemStatsDao();
        long recordCount = dao.countRecentImports(filter);
        // Build paging object.
        Paging<RecentImportModel> paging = new Paging<RecentImportModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of recent imports.
        List<RecentImportVo> importVos = dao.getRecentImports(filter);
        List<RecentImportModel> importModels = ModelUtils.createListModelFromVo(importVos, RecentImportModel.class);
        paging.setRecords(importModels);
        // Set recent imports.
        this.setRecentImports(paging);
    }

    protected SysStatsFilter buildSysStatFilter(final String defaultOrderField) throws Exception {
        SysStatsFilter filter = new SysStatsFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Set page.
        Integer page = null;
        try {
            String pageStr = StringUtils.isBlank(this.getPage()) ? "1" : this.getPage();
            page = Integer.valueOf(pageStr);
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
            this.setPage(pageStr);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            String pageSizeStr = StringUtils.isBlank(this.getPageSize()) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : this.getPageSize();
            pageSize = Integer.valueOf(pageSizeStr);
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
            this.setPageSize(pageSizeStr);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            String orderStr = StringUtils.isBlank(this.getOrderType()) ? "0" : this.getOrderType();
            order = Integer.valueOf(orderStr);
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
            this.setOrderType(orderStr);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        String orderField = StringUtils.isBlank(this.getOrderField()) ? defaultOrderField : this.getOrderField();
        filter.setOrderField(orderField);
        this.setOrderField(orderField);
        return filter;
    }

    protected String buildFilterCollections(final String airbillNumber) throws Exception {
        if (StringUtils.isBlank(airbillNumber)) {
            return null;
        }
        SystemStatsDao statsDao = new SystemStatsDao();
        List<IdsVo> idsVos = statsDao.getFilterCollections(airbillNumber);
        List<String> idList = new ArrayList<String>();
        // Remove duplicate id for each type.
        String key;
        for (IdsVo idsVo : idsVos) {
            // Shipment id.
            key = idsVo.getShipmentId() + "@0";
            if (!idList.contains(key)) {
                idList.add(key);
            }
            // Sender address id.
            key = idsVo.getSenderAddressId() + "@1";
            if (!idList.contains(key)) {
                idList.add(key);
            }
            // Receiver address id.
            key = idsVo.getReceiverAddressId() + "@1";
            if (!idList.contains(key)) {
                idList.add(key);
            }
            // Schedule collection id.
            key = idsVo.getScheduleCollectionId() + "@2";
            if (!idList.contains(key)) {
                idList.add(key);
            }
            // Schedule address id.
            key = idsVo.getScheduleAddressId() + "@1";
            if (!idList.contains(key)) {
                idList.add(key);
            }
            // Piece id.
            key = idsVo.getPieceId() + "@3";
            if (!idList.contains(key)) {
                idList.add(key);
            }
        }
        // Build filter collections.
        String result = "";
        for (String id : idList) {
            String[] arr = id.split("@");
            switch (Integer.valueOf(arr[1])) {
                case 0:
                    result += "'shipmentid = \\'" + arr[0] + "\\'',";
                    break;
                case 1:
                    result += "'addressid = \\'" + arr[0] + "\\'',";
                    break;
                case 2:
                    result += "'schedule_collection_id = \\'" + arr[0] + "\\'',";
                    break;
                case 3:
                    result += "'piece_id = \\'" + arr[0] + "\\'',";
                    break;
            }
        }
        return StringUtils.isBlank(result) ? null : result.substring(0, result.length() - 1);
    }

    protected void prepareSearchOptions() throws Exception {
        Map<String, String> options = new LinkedHashMap<>();
        options.put("recent_import", "Recent Imports");
        options.put("dhl_country_zone", "DHL Country/Zone");
        options.put("login_log", "Login Log");
        options.put("error_login_log", "Error Login Log");
        options.put("webship_log", "Webship Log");
        options.put("adjustment_log", "Adjustment Log");
        SystemSettingDao settingDao = new SystemSettingDao();
        SystemSettingVo defaultCountry = settingDao.getSystemSettingByName("Default Origin Country");
        Integer defaultCountryCode = 0;
        if (defaultCountry != null && defaultCountry.getSettingValue() != null) {
            defaultCountryCode = Integer.valueOf(defaultCountry.getSettingValue());
        }
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> services = service.selectAll();
        // 6. $fedex_display (FedEx Zone)
        // 7. $ram_display (Ram Rate Sheet)
        // 8. $ups_display (Ups Zone)
        // 9. $post_display ()
        // 10. $tnt_display
        // 11. $dhl_domestic_display
        // 12. $ukmail_postcode_zone_display
        // 13. $ups_zone_display
        // 14. $toll_priority_display
        // 15. $toll_priority_rate_sheet_display
        // 16. $toll_ipec_display
        // 17. $toll_priority_remote_area_display
        // 18. $tnt_int_zone_display
        // 19. $tnt_domestic_remote_area_display
        for (ServiceVo serviceVo : services) {
            if (serviceVo.getServiceId() == 40 && serviceVo.getInactive() == 0) {
                options.put("fedex_zone", "FedEx Zone");
            }
            if (serviceVo.getServiceId() == 56 && serviceVo.getInactive() == 0) {
                options.put("ram", "Ram rate sheet");
            }
            if (serviceVo.getServiceId() == 59 && serviceVo.getInactive() == 0) {
                options.put("toll_ipec_zone", "Toll IPEC Town Postcode");
            }
            if (defaultCountryCode == 16 /* Australia */ && serviceVo.getServiceId() == 3 && serviceVo.getInactive() == 0) {
                options.put("tnt_zone", "TNT Zone");
                options.put("tnt_post_code", "TNT Post Code");
                options.put("tnt_suburb", "TNT Suburb");
                options.put("tnt_dom_remote_area", "TNT Domestic Remote Area");
            }
            if (defaultCountryCode == 16 /* Australia */ && serviceVo.getServiceId() == 52 && serviceVo.getInactive() == 0) {
                options.put("toll_priority_suburb", "Toll Priority Suburb");
                options.put("toll_priority_rate_sheet", "Toll Priority Rate Sheet");
                options.put("toll_priority_remote_area", "Toll Priority Remote Area");
            }
            if (defaultCountryCode == 16 /* Australia */ && serviceVo.getServiceId() == 15 && serviceVo.getInactive() == 0) {
                options.put("dhl_dom_zone", "DHL Domestic Zone");
            }
            if (defaultCountryCode == 16 /* Australia */ && serviceVo.getServiceId() == 54 && serviceVo.getInactive() == 0) {
                options.put("tnt_intl_zone", "TNT International Zone");
            }
        }
        this.setSearchOptions(options);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Map<String, String> getSearchOptions() {
        return searchOptions;
    }

    public void setSearchOptions(Map<String, String> searchOptions) {
        this.searchOptions = searchOptions;
    }

    public Paging<RecentImportModel> getRecentImports() {
        return recentImports;
    }

    public void setRecentImports(Paging<RecentImportModel> recentImports) {
        this.recentImports = recentImports;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(List<String> requestTypes) {
        this.requestTypes = requestTypes;
    }

    public Map<String, String> getStatusList() {
        return statusList;
    }

    public void setStatusList(Map<String, String> statusList) {
        this.statusList = statusList;
    }

    public Paging<DhlCountryModel> getDhlZones() {
        return dhlZones;
    }

    public void setDhlZones(Paging<DhlCountryModel> dhlZones) {
        this.dhlZones = dhlZones;
    }

    public Paging<LoginLogModel> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Paging<LoginLogModel> loginLogs) {
        this.loginLogs = loginLogs;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Paging<WebshipLogModel> getWebshipLogs() {
        return webshipLogs;
    }

    public void setWebshipLogs(Paging<WebshipLogModel> webshipLogs) {
        this.webshipLogs = webshipLogs;
    }

    public Paging<SysStatAdjustmentLogModel> getAdjustmentLogs() {
        return adjustmentLogs;
    }

    public void setAdjustmentLogs(Paging<SysStatAdjustmentLogModel> adjustmentLogs) {
        this.adjustmentLogs = adjustmentLogs;
    }

    public Paging<CarrierZoneModel> getTntZones() {
        return tntZones;
    }

    public void setTntZones(Paging<CarrierZoneModel> tntZones) {
        this.tntZones = tntZones;
    }

    public Paging<CarrierPostCodeModel> getTntPostCodes() {
        return tntPostCodes;
    }

    public void setTntPostCodes(Paging<CarrierPostCodeModel> tntPostCodes) {
        this.tntPostCodes = tntPostCodes;
    }

    public Paging<CarrierSuburbModel> getTntSuburbs() {
        return tntSuburbs;
    }

    public void setTntSuburbs(Paging<CarrierSuburbModel> tntSuburbs) {
        this.tntSuburbs = tntSuburbs;
    }

    public Paging<TntRemoteAreaModel> getTntRemoteAreas() {
        return tntRemoteAreas;
    }

    public void setTntRemoteAreas(Paging<TntRemoteAreaModel> tntRemoteAreas) {
        this.tntRemoteAreas = tntRemoteAreas;
    }

    public Paging<MultiZoneModel> getDhlDomZones() {
        return dhlDomZones;
    }

    public void setDhlDomZones(Paging<MultiZoneModel> dhlDomZones) {
        this.dhlDomZones = dhlDomZones;
    }

    public Paging<TollPrioritySuburbModel> getTollPrioritySuburbs() {
        return tollPrioritySuburbs;
    }

    public void setTollPrioritySuburbs(Paging<TollPrioritySuburbModel> tollPrioritySuburbs) {
        this.tollPrioritySuburbs = tollPrioritySuburbs;
    }

    public Paging<TollPriorityRateSheetModel> getTollPriorityRateSheets() {
        return tollPriorityRateSheets;
    }

    public void setTollPriorityRateSheets(Paging<TollPriorityRateSheetModel> tollPriorityRateSheets) {
        this.tollPriorityRateSheets = tollPriorityRateSheets;
    }

    public Paging<TollRemoteAreaModel> getTollRemoteAreas() {
        return tollRemoteAreas;
    }

    public void setTollRemoteAreas(Paging<TollRemoteAreaModel> tollRemoteAreas) {
        this.tollRemoteAreas = tollRemoteAreas;
    }

    public Paging<TntInternationalAuZoneModel> getTntIntlZones() {
        return tntIntlZones;
    }

    public void setTntIntlZones(Paging<TntInternationalAuZoneModel> tntIntlZones) {
        this.tntIntlZones = tntIntlZones;
    }

    public Paging<TollIpecZoneModel> getTollIpecZones() {
        return tollIpecZones;
    }

    public void setTollIpecZones(Paging<TollIpecZoneModel> tollIpecZones) {
        this.tollIpecZones = tollIpecZones;
    }
}