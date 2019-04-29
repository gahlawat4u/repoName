package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.FormaterUtils;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.reports.customer.status.CustomerStatusColumn;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.report.customer.status.CustomerStatusServiceImp;
import com.gms.xms.workflow.service.report.customer.status.ICustomerStatusService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Posted from CustomerStatusController
 * <p>
 * Author DatTV Nov 6, 2015
 */
public class CustomerStatusController extends JsonBaseController {

    private static final long serialVersionUID = 3208379909838514372L;

    private List<FranchiseInfoModel> franchises;
    private String rptTxnId;
    private String franchiseCode;
    private String reportDate;
    private String weeklyField;
    private String weeklyType;
    private String monthlyField;
    private String monthlyType;
    private List<Map<String, String>> weeklyReport;
    private List<Map<String, String>> monthlyReport;
    private Map<String, String> weeklyTotal;
    private Map<String, String> monthlyTotal;
    private List<CustomerStatusColumn> columns;
    private List<ServiceModel> services;

    public String show() {
        try {
            prepareFranchiseList();
            prepareServices();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String search() {
        try {
            prepareWeeklyReport();
            prepareMonthlyReport();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doWeekly() {
        try {
            prepareWeeklyReport();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doMonthly() {
        try {
            prepareMonthlyReport();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    private void prepareFranchiseList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    private String buildFranchiseCodeList(String franCode) throws Exception {
        String franchiseList = "";
        if ("All".equalsIgnoreCase(franCode) || StringUtils.isBlank(franCode)) {
            if (franchises == null || franchises.size() <= 0) {
                prepareFranchiseList();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseList += franchise.getCode() + ",";
            }
            franchiseList = franchiseList.substring(0, franchiseList.length() - 1);
        } else {
            franchiseList = franCode;
        }

        return franchiseList;
    }

    private CustomerStatusFilter buildWeeklyFilter() throws Exception {
        CustomerStatusFilter filter = new CustomerStatusFilter();
        // Set report transaction id
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
        // Set report date
        Date dateToReport = null;
        try {
            if (StringUtils.isBlank(reportDate)) {
                reportDate = DateUtils.convertDateToString(Calendar.getInstance().getTime(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            }
            dateToReport = DateUtils.convertStringToDate(reportDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (dateToReport == null) {
            throw new Exception("Invalid report date format");
        }
        filter.setReportDate(dateToReport);
        // Set franchise list
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set service list
        if (this.getServices() == null) {
            prepareServices();
        }
        List<Integer> serviceIdList = new ArrayList<Integer>();
        for (ServiceModel service : this.getServices()) {
            serviceIdList.add(Integer.valueOf(service.getServiceId()));
        }
        filter.setServiceList(serviceIdList);
        // Set sorting properties
        filter.setOrderField(StringUtils.isBlank(weeklyField) ? "sort_start_date" : weeklyField); // Default sort field is start date
        filter.setOrderType(StringUtils.isBlank(weeklyType) ? 1 : Integer.valueOf(weeklyType)); // Default sort type is 1 (descending)
        return filter;
    }

    private CustomerStatusFilter buildMonthlyFilter() throws Exception {
        CustomerStatusFilter filter = new CustomerStatusFilter();
        // Set report transaction id
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
        // Set report date
        Date dateToReport = null;
        try {
            if (StringUtils.isBlank(reportDate)) {
                reportDate = DateUtils.convertDateToString(Calendar.getInstance().getTime(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            }
            dateToReport = DateUtils.convertStringToDate(reportDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (dateToReport == null) {
            throw new Exception("Invalid report date format");
        }
        filter.setReportDate(dateToReport);
        // Set franchise list
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set service list
        if (this.getServices() == null) {
            prepareServices();
        }
        List<Integer> serviceIdList = new ArrayList<Integer>();
        for (ServiceModel service : this.getServices()) {
            serviceIdList.add(Integer.valueOf(service.getServiceId()));
        }
        filter.setServiceList(serviceIdList);
        // Set sorting properties
        filter.setOrderField(StringUtils.isBlank(monthlyField) ? "sort_start_date" : monthlyField); // Default sort field is start date
        filter.setOrderType(StringUtils.isBlank(monthlyType) ? 1 : Integer.valueOf(monthlyType)); // Default sort type is 1 (descending)
        return filter;
    }

    private List<CustomerStatusColumn> buildDynamicColumns(CustomerStatusFilter filter) throws Exception {
        if (filter.getServiceList() == null || filter.getServiceList().size() == 0) {
            return null;
        }
        List<CustomerStatusColumn> columnList = new ArrayList<CustomerStatusColumn>();
        IServiceService iService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iService.getIntegratedServices();
        CustomerStatusColumn column = null;
        for (Integer serviceId : filter.getServiceList()) {
            String serviceName = null;
            for (ServiceVo service : serviceVos) {
                if (service.getServiceId() == serviceId) {
                    serviceName = service.getServiceName();
                    break;
                }
            }
            // Add service shipment count column
            column = new CustomerStatusColumn();
            column.setFieldName("shipment_count_" + String.valueOf(serviceId));
            column.setColumnName(serviceName + " " + this.getLocalizationText("Ship"));
            column.setGroup(0);
            column.setServiceId(serviceId);
            columnList.add(column);
            // Add service revenue include tax column
            column = new CustomerStatusColumn();
            column.setFieldName("rev_inc_gst_" + String.valueOf(serviceId));
            column.setColumnName(serviceName + " " + this.getLocalizationText("Rev"));
            column.setGroup(1);
            column.setServiceId(serviceId);
            columnList.add(column);
            // Add service revenue exclude tax column
            column = new CustomerStatusColumn();
            column.setFieldName("rev_exc_gst_" + String.valueOf(serviceId));
            column.setColumnName(serviceName + " " + this.getLocalizationText("Rev"));
            column.setGroup(2);
            column.setServiceId(serviceId);
            columnList.add(column);
            // Add service margin include tax column
            column = new CustomerStatusColumn();
            column.setFieldName("margin_inc_gst_" + String.valueOf(serviceId));
            column.setColumnName(serviceName + " " + this.getLocalizationText("Mrg"));
            column.setGroup(1);
            column.setServiceId(serviceId);
            columnList.add(column);
            // Add service margin exclude tax column
            column = new CustomerStatusColumn();
            column.setFieldName("margin_exc_gst_" + String.valueOf(serviceId));
            column.setColumnName(serviceName + " " + this.getLocalizationText("Mrg"));
            column.setGroup(2);
            column.setServiceId(serviceId);
            columnList.add(column);
        }
        return columnList;
    }

    private void prepareWeeklyReport() throws Exception {
        ICustomerStatusService statusService = new CustomerStatusServiceImp(this.getAddInfoMap());
        CustomerStatusFilter filter = this.buildWeeklyFilter();
        List<Map<String, String>> weeklyReport = statusService.getWeeklyReport(filter);
        this.formatColumns(weeklyReport);
        if (this.getColumns() == null) {
            this.setColumns(this.buildDynamicColumns(filter));
        }
        this.setWeeklyReport(weeklyReport);
        Map<String, String> totalWeekly = statusService.sumWeeklyReport(filter);
        this.formatColumn(totalWeekly);
        this.setWeeklyTotal(totalWeekly);
    }

    private void prepareMonthlyReport() throws Exception {
        ICustomerStatusService statusService = new CustomerStatusServiceImp(this.getAddInfoMap());
        CustomerStatusFilter filter = this.buildMonthlyFilter();
        List<Map<String, String>> monthlyReport = statusService.getMonthlyReport(filter);
        this.formatColumns(monthlyReport);
        if (this.getColumns() == null) {
            this.setColumns(this.buildDynamicColumns(filter));
        }
        this.setMonthlyReport(monthlyReport);
        Map<String, String> totalMonthly = statusService.sumMonthlyReport(filter);
        this.formatColumn(totalMonthly);
        this.setMonthlyTotal(totalMonthly);
    }

    private void prepareServices() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.getIntegratedServices();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    private void formatColumn(Map<String, String> record) {
        for (String key : record.keySet()) {
            // Format date columns
            if (key.endsWith("_date")) {
                // Do nothing (Ignore date columns)
            } else if (key.contains("rev_") || key.contains("margin_")) {
                Double num = Double.valueOf(record.get(key));
                record.put(key, FormaterUtils.double2String(num));
            } else {
                Long num = Long.valueOf(record.get(key));
                record.put(key, FormaterUtils.int2String(num));
            }
        }
    }

    private void formatColumns(List<Map<String, String>> report) {
        for (Map<String, String> record : report) {
            for (String key : record.keySet()) {
                // Format date columns
                if (key.endsWith("_date")) {
                    // Ignore sort start date column
                    if (!key.startsWith("sort_")) {
                        Date date = DateUtils.convertStringToDate(record.get(key), "dd-MM-yyyy", null);
                        record.put(key, FormaterUtils.date2String(date));
                    }
                } else if (key.contains("rev_") || key.contains("margin_")) {
                    Double num = Double.valueOf(record.get(key));
                    record.put(key, FormaterUtils.double2String(num));
                } else {
                    Long num = Long.valueOf(record.get(key));
                    record.put(key, FormaterUtils.int2String(num));
                }
            }
        }
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public List<Map<String, String>> getWeeklyReport() {
        return weeklyReport;
    }

    public void setWeeklyReport(List<Map<String, String>> weeklyReport) {
        this.weeklyReport = weeklyReport;
    }

    public List<Map<String, String>> getMonthlyReport() {
        return monthlyReport;
    }

    public void setMonthlyReport(List<Map<String, String>> monthlyReport) {
        this.monthlyReport = monthlyReport;
    }

    public String getWeeklyField() {
        return weeklyField;
    }

    public void setWeeklyField(String weeklyField) {
        this.weeklyField = weeklyField;
    }

    public String getWeeklyType() {
        return weeklyType;
    }

    public void setWeeklyType(String weeklyType) {
        this.weeklyType = weeklyType;
    }

    public String getMonthlyField() {
        return monthlyField;
    }

    public void setMonthlyField(String monthlyField) {
        this.monthlyField = monthlyField;
    }

    public String getMonthlyType() {
        return monthlyType;
    }

    public void setMonthlyType(String monthlyType) {
        this.monthlyType = monthlyType;
    }

    public Map<String, String> getWeeklyTotal() {
        return weeklyTotal;
    }

    public void setWeeklyTotal(Map<String, String> weeklyTotal) {
        this.weeklyTotal = weeklyTotal;
    }

    public Map<String, String> getMonthlyTotal() {
        return monthlyTotal;
    }

    public void setMonthlyTotal(Map<String, String> monthlyTotal) {
        this.monthlyTotal = monthlyTotal;
    }

    public List<CustomerStatusColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CustomerStatusColumn> columns) {
        this.columns = columns;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}