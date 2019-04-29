package com.gms.xms.weblib.controller.admin;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.dto.statistics.*;
import com.gms.xms.model.UserHomeModel;
import com.gms.xms.model.statistics.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.UserHomeDao;
import com.gms.xms.persistence.service.StatisticsService;
import com.gms.xms.txndb.vo.SystemSettingVo;
import com.gms.xms.txndb.vo.UserHomeVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Posted from Aug 23, 2016 4:30:24 PM
 * <p>
 * Author dattrinh
 */
public class StatisticsController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String period;
    private String logType;
    private List<StatServiceTypeModel> serviceTypes;
    private List<StatWebshipLogModel> webshipLogs;
    private List<StatShipmentModel> shipments;
    private List<StatCostVsMarginModel> costVsMargins;
    private List<StatFollowUpModel> followUps;
    private List<StatSalesRepByManagerModel> managers;
    private long[] teleStat1;
    private long[] teleStat2;
    private Map<String, String> salePerson;
    private List<StatSalesGraphModel> salesGraphs;
    private String salesGraphTotal;
    private Map<Integer, String> serviceTypePeriods;
    private String uniqueId;
    private String width;
    private String height;
    private UserHomeModel userHomeModel;
    private Map<String, String> graphTypes;

    public String doStatistics() {
        try {
            this.prepareSelectGraphTypes();
            Long userId = Long.valueOf((String) request.getSession().getAttribute("SESS_XMS_ADMIN_ID"));
            UserHomeDao userHomeDao = new UserHomeDao();
            UserHomeVo userHomeVo = userHomeDao.selectByUserId(userId);
            if (userHomeVo != null) {
                this.setUserHomeModel(ModelUtils.createModelFromVo(userHomeVo, UserHomeModel.class));
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doServiceTypes() {
        try {
            // Valid period type.
            if (!validPeriod()) {
                throw new CustomException("Service Types statistics: invalid period type.");
            }
            buildServiceTypePeriods();
            // Get service type statistics.
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatServiceTypeVo> serviceTypeVos = service.getServiceTypes(Integer.valueOf(this.getPeriod()));
            List<StatServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(serviceTypeVos, StatServiceTypeModel.class);
            this.setServiceTypes(serviceTypeModels);
            this.setUniqueId(UUID.randomUUID().toString());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doWebshipLogs() {
        try {
            // Valid log type.
            if (!validLogType()) {
                throw new CustomException("Webship Log statistics: invalid log type.");
            }
            int logType = Integer.valueOf(this.getLogType());
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatWebshipLogVo> logVos = service.getWebshipLog(logType);
            // Get display info for each record.
            for (StatWebshipLogVo logVo : logVos) {
                logVo.setLogInfo(this.getWebshipLogInfo(logVo, logType));
            }
            List<StatWebshipLogModel> logModels = ModelUtils.createListModelFromVo(logVos, StatWebshipLogModel.class);
            this.setWebshipLogs(logModels);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doShipments() {
        try {
            // Valid period type.
            if (!validPeriod()) {
                throw new CustomException("Shipments statistics: invalid period type.");
            }
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatShipmentVo> shipmentVos = service.getShipments(Integer.valueOf(this.getPeriod()));
            List<StatShipmentModel> shipmentModels = ModelUtils.createListModelFromVo(shipmentVos, StatShipmentModel.class);
            this.setShipments(shipmentModels);
            this.setUniqueId(UUID.randomUUID().toString());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doCostVsMargins() {
        try {
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatCostVsMarginVo> costVsMarginVos = service.getCostVsMargin();
            // Get column name of each row.
            for (StatCostVsMarginVo costVsMarginVo : costVsMarginVos) {
                costVsMarginVo.setColumnName(this.getCostVsMarginColumnName(costVsMarginVo));
            }
            List<StatCostVsMarginModel> costVsMarginModels = ModelUtils.createListModelFromVo(costVsMarginVos, StatCostVsMarginModel.class);
            // Remove , from field value.
            for (StatCostVsMarginModel costVsMarginModel : costVsMarginModels) {
                costVsMarginModel.setCost(costVsMarginModel.getCost().replace(",", ""));
                costVsMarginModel.setMargin(costVsMarginModel.getMargin().replace(",", ""));
                costVsMarginModel.setRevenue(costVsMarginModel.getRevenue().replace(",", ""));
            }
            this.setCostVsMargins(costVsMarginModels);
            this.setUniqueId(UUID.randomUUID().toString());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doFollowUps() {
        try {
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatFollowUpVo> followUpVos = service.getFollowUps();
            List<StatFollowUpModel> followUpModels = ModelUtils.createListModelFromVo(followUpVos, StatFollowUpModel.class);
            this.setFollowUps(followUpModels);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSalesRep() {
        String viewName = "";
        try {
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            int userLevel = this.getUserLevelId();
            if (userLevel == 11) {
                SystemSettingDao settingDao = new SystemSettingDao();
                SystemSettingVo settingVo = settingDao.getSystemSettingByName("");
                int month = (settingVo == null || settingVo.getSettingValue() == null) ? 0 : Integer.valueOf(settingVo.getSettingValue());
                Map<String, String> salePersonMap = service.getSalesRepBySalePerson(month);
                this.setSalePerson(salePersonMap);
                viewName = "sale_person";
            } else if (userLevel == 9) {
                long[] teleCount = service.getSalesRepByTeleMarketer(this.getUserLevelId(), this.getFranCode(), 0);
                this.setTeleStat1(teleCount);
                long[] teleQCount = service.getSalesRepByTeleMarketer(this.getUserLevelId(), this.getFranCode(), 1);
                this.setTeleStat2(teleQCount);
                viewName = "tele_marketer";
            } else if (userLevel < 9 || userLevel == 10) {
                List<StatSalesRepByManagerVo> managerVos = service.getSalesRepByManager();
                List<StatSalesRepByManagerModel> managerModels = ModelUtils.createListModelFromVo(managerVos, StatSalesRepByManagerModel.class);
                this.setManagers(managerModels);
                viewName = "sale_manager";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return viewName;
    }

    public String doSalesGraph() {
        try {
            StatisticsService service = new StatisticsService(this.getUserId(), this.getUserLevel(), this.buildFranchiseCodeList("All"));
            List<StatSalesGraphVo> graphVos = service.getSalesGraph(this.getUserLevelId(), this.getFranCode());
            List<StatSalesGraphModel> graphModels = ModelUtils.createListModelFromVo(graphVos, StatSalesGraphModel.class);
            this.setSalesGraphs(graphModels);
            // Get sales graph total.
            double graphTotal = service.getTotalSales(this.getUserLevelId(), this.getFranCode());
            this.setSalesGraphTotal(String.valueOf(graphTotal));
            this.setUniqueId(UUID.randomUUID().toString());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSaveDivInfo() {
        try {
            UserHomeDao userHomeDao = new UserHomeDao();
            Long userId = Long.valueOf((String) request.getSession().getAttribute("SESS_XMS_ADMIN_ID"));
            UserHomeVo userHomeVo = userHomeDao.selectByUserId(userId);
            if (userHomeVo == null) {
                userHomeVo = new UserHomeVo();
                userHomeVo.setUserId(userId);
                userHomeVo.setDiv1(0);
                userHomeVo.setDiv2(0);
                userHomeVo.setDiv3(0);
                userHomeVo.setDiv4(0);
                userHomeVo.setDiv5(0);
                userHomeVo.setDiv6(0);
                userHomeVo.setBottomLeft(0f);
                userHomeVo.setBottomRight(0f);
                userHomeVo.setLeftWidth(0f);
                userHomeVo.setTopLeft(0f);
                userHomeVo.setTopRight(0f);
                userHomeDao.insert(this.getAddInfoMap(), userHomeVo);
            }
            UserHomeVo updateHomeVo = ModelUtils.createVoFromModel(this.getUserHomeModel(), UserHomeVo.class);
            updateHomeVo.setUserId(userId);
            BeanUtils.copyProperties(userHomeVo, updateHomeVo);
            userHomeDao.update(getAddInfoMap(), userHomeVo);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareSelectGraphTypes() {
        Map<String, String> graphTypes = new TreeMap<String, String>();
        graphTypes.put("0", "None");
        graphTypes.put("1", "Service Types");
        graphTypes.put("2", "Webship Log (Today)");
        graphTypes.put("3", "Webship Log (Week)");
        graphTypes.put("4", "Shipments Month");
        graphTypes.put("5", "Shipments Today");
        graphTypes.put("6", "Shipments Week");
        graphTypes.put("7", "Cost/Profit/Revenue Graph");
        graphTypes.put("8", "Follow-ups");
        graphTypes.put("9", "Sales Rate");
        graphTypes.put("10", "Sales Graph");
        this.setGraphTypes(graphTypes);
    }

    protected String getCostVsMarginColumnName(StatCostVsMarginVo costVsMarginVo) {
        String columnName = DateUtils.convertDateToString(costVsMarginVo.getStartDate(), "dd/MM", null);
        columnName += "-" + DateUtils.convertDateToString(costVsMarginVo.getEndDate(), "dd/MM", null);
        return columnName;
    }

    protected String getWebshipLogInfo(StatWebshipLogVo logVo, Integer logType) {
        String displayInfo = "";
        if (logType == 1) {
            displayInfo += DateUtils.convertDateToString(logVo.getCreateDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null) + " - ";
        }
        displayInfo += DateUtils.convertDateToString(logVo.getCreateDate(), AppConstants.APP_SETTINGS.getDefaultTimeFormat(), null);
        displayInfo += " - " + logVo.getCustomerCode();
        displayInfo += " - " + logVo.getCustomerName();
        if ("voided".equalsIgnoreCase(logVo.getShipmentStatus())) {
            displayInfo += " voided a ";
        } else {
            displayInfo += " generated a ";
        }
        displayInfo += logVo.getServiceName() + " shipment";
        return displayInfo;
    }

    protected void buildServiceTypePeriods() {
        Map<Integer, String> periods = new LinkedHashMap<Integer, String>();
        String lastWord = this.getLocalizationText("last");
        String weekWord = this.getLocalizationText("week");
        String weeksWord = this.getLocalizationText("weeks");
        String monthsWord = this.getLocalizationText("months");
        periods.put(0, lastWord + " " + weekWord);
        periods.put(1, lastWord + " 2 " + weeksWord);
        periods.put(2, lastWord + " 4 " + weeksWord);
        periods.put(3, lastWord + " 12 " + weeksWord);
        periods.put(4, lastWord + " 12 " + monthsWord);
        this.setServiceTypePeriods(periods);
    }

    protected boolean validPeriod() {
        if (StringUtils.isBlank(this.getPeriod())) {
            // Set default period type is 0.
            this.setPeriod("0");
            return true;
        }
        try {
            Integer.valueOf(this.getPeriod());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected boolean validLogType() {
        if (StringUtils.isBlank(this.getLogType())) {
            // Set default log type is 0.
            this.setLogType("0");
            return true;
        }
        try {
            Integer.valueOf(this.getLogType());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected String getFranchiseList() throws Exception {
        return this.buildFranchiseCodeList("All");
    }

    protected Long getUserId() {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        return Long.valueOf(userId);
    }

    protected Integer getUserLevelId() {
        String userLevelId = (String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID");
        return Integer.valueOf(userLevelId);
    }

    protected Integer getUserLevel() {
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        return Double.valueOf(userLevel).intValue();
    }

    protected Long getFranCode() {
        String franCode = (String) this.getSession("SESS_XMS_ADMIN_CODE");
        franCode = franCode.substring(0, 3);
        return Long.valueOf(franCode);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<StatServiceTypeModel> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(List<StatServiceTypeModel> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Map<Integer, String> getServiceTypePeriods() {
        return serviceTypePeriods;
    }

    public void setServiceTypePeriods(Map<Integer, String> serviceTypePeriods) {
        this.serviceTypePeriods = serviceTypePeriods;
    }

    public List<StatWebshipLogModel> getWebshipLogs() {
        return webshipLogs;
    }

    public void setWebshipLogs(List<StatWebshipLogModel> webshipLogs) {
        this.webshipLogs = webshipLogs;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public List<StatShipmentModel> getShipments() {
        return shipments;
    }

    public void setShipments(List<StatShipmentModel> shipments) {
        this.shipments = shipments;
    }

    public List<StatCostVsMarginModel> getCostVsMargins() {
        return costVsMargins;
    }

    public void setCostVsMargins(List<StatCostVsMarginModel> costVsMargins) {
        this.costVsMargins = costVsMargins;
    }

    public List<StatFollowUpModel> getFollowUps() {
        return followUps;
    }

    public void setFollowUps(List<StatFollowUpModel> followUps) {
        this.followUps = followUps;
    }

    public List<StatSalesRepByManagerModel> getManagers() {
        return managers;
    }

    public void setManagers(List<StatSalesRepByManagerModel> managers) {
        this.managers = managers;
    }

    public long[] getTeleStat1() {
        return teleStat1;
    }

    public void setTeleStat1(long[] teleStat1) {
        this.teleStat1 = teleStat1;
    }

    public long[] getTeleStat2() {
        return teleStat2;
    }

    public void setTeleStat2(long[] teleStat2) {
        this.teleStat2 = teleStat2;
    }

    public Map<String, String> getSalePerson() {
        return salePerson;
    }

    public void setSalePerson(Map<String, String> salePerson) {
        this.salePerson = salePerson;
    }

    public List<StatSalesGraphModel> getSalesGraphs() {
        return salesGraphs;
    }

    public void setSalesGraphs(List<StatSalesGraphModel> salesGraphs) {
        this.salesGraphs = salesGraphs;
    }

    public String getSalesGraphTotal() {
        return salesGraphTotal;
    }

    public void setSalesGraphTotal(String salesGraphTotal) {
        this.salesGraphTotal = salesGraphTotal;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public UserHomeModel getUserHomeModel() {
        return userHomeModel;
    }

    public void setUserHomeModel(UserHomeModel userHomeModel) {
        this.userHomeModel = userHomeModel;
    }

    public Map<String, String> getGraphTypes() {
        return graphTypes;
    }

    public void setGraphTypes(Map<String, String> graphTypes) {
        this.graphTypes = graphTypes;
    }
}
