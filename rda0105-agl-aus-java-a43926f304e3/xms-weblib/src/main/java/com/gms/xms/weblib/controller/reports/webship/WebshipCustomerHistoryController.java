package com.gms.xms.weblib.controller.reports.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.webship.IWebshipCustomerHistoryRender;
import com.gms.xms.workflow.render.report.webship.WebshipCustomerHistoryRenderImp;
import com.gms.xms.workflow.service.report.webship.IWebshipCustomerHistoryService;
import com.gms.xms.workflow.service.report.webship.WebshipCustomerHistoryServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Posted from WebshipCustomerHistoryController.java
 * <p>
 * Author dattrinh 3:14:20 PM
 */
public class WebshipCustomerHistoryController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String franchiseCode;
    private String serviceId;
    private String carrierId;
    private String period;
    private String startDate;
    private String endDate;
    private String numberOfPeriods;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<Map<String, String>> report;
    private List<String> columns;
    private List<String> displayColumns;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;
    private List<ShipmentTypeModel> services;
    private List<ServiceModel> carriers;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareCarriers();
            prepareServices(null);
            prepareFranchises();
            preparePageSizes();
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String loadServices() {
        try {
          Integer carrier = Integer.valueOf(this.getCarrierId());
          prepareServices(carrier);             
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareReportData();
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/webship_customer_history_" + this.rptTxnId + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                WebshipCustomerHistoryFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                List<String> columns = this.buildColumns(filter);
                List<String> displayColumns = this.buildDisplayColumns(filter);
                IWebshipCustomerHistoryRender render = new WebshipCustomerHistoryRenderImp(this.getAddInfoMap());
                render.renderWebshipCustomerHistoryHtmlFile(filter, columns, displayColumns, htmlFilePath, realPath);
                this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String export() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "webship_customer_history";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.rptTxnId + ".xlsx";
                WebshipCustomerHistoryFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                List<String> columns = this.buildColumns(filter);
                List<String> displayColumns = this.buildDisplayColumns(filter);
                IWebshipCustomerHistoryRender render = new WebshipCustomerHistoryRenderImp(this.getAddInfoMap());
                render.renderWebshipCustomerHistoryXlsFile(filter, columns, displayColumns, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void prepareFranchises() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    private void prepareCarriers() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setCarriers(serviceModels);
    }

    private void prepareServices(Integer carrierId) throws Exception {
        IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
        List<ShipmentTypeVo> shipmentTypeVos = shipmentTypeService.getServicesByCarrier(carrierId);
        List<ShipmentTypeModel> shipmentTypeModels = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
        this.setServices(shipmentTypeModels);
    }
    
    
   

    private String buildFranchiseCodeList(String franCode) throws Exception {
        String franchiseList = "";
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            if (franchises == null) {
                prepareFranchises();
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

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    private void prepareReportData() throws Exception {
        IWebshipCustomerHistoryService historyService = new WebshipCustomerHistoryServiceImp(this.getAddInfoMap());
        WebshipCustomerHistoryFilter filter = this.buildFilter();
        this.setColumns(this.buildColumns(filter));
        this.setDisplayColumns(this.buildDisplayColumns(filter));
        // Prepare for paging
        Long recordCount = historyService.getWebshipCustomerHistoryCount(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<Map<String, String>> paging = new Paging<Map<String, String>>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the webship customer history report data
        List<Map<String, String>> histories = historyService.getWebshipCustomerHistoryReport(filter);
        this.formatNumberColumns(histories);
        paging.setRecords(histories);
        // Set the webship customer history report data
        this.setReport(paging);
    }

    private void formatNumberColumns(List<Map<String, String>> histories) {
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultIntNumberFormat());
        for (Map<String, String> map : histories) {
            for (String column : this.getColumns()) {
                if (column.startsWith("d_")) {
                    Long val = Long.valueOf(map.get(column));
                    map.put(column, formatter.format(val));
                }
            }
        }
    }

    private WebshipCustomerHistoryFilter buildFilter() throws Exception {
        WebshipCustomerHistoryFilter filter = new WebshipCustomerHistoryFilter();
        // Generate report transaction id if null
        rptTxnId = StringUtils.isBlank(rptTxnId) ? UUID.randomUUID().toString() : rptTxnId;
        filter.setRptTxnId(rptTxnId);
        // Set carrierId
        Integer carId = StringUtils.isBlank(carrierId) ? null : Integer.valueOf(carrierId);
        filter.setCarrierId(carId);
        // Set serviceId
        Integer svId = StringUtils.isBlank(serviceId) ? null : Integer.valueOf(serviceId);
        filter.setServiceId(svId);
        // Set franchise list
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Determine the period type
        Integer periodType = StringUtils.isBlank(period) ? null : Integer.valueOf(period);
        if (periodType < 1 || periodType > 4) {
            throw new CustomException("Unknown period type");
        } else {
            if (periodType == 1 || periodType == 3) {
                // Period type is daily date range
                filter.setPeriodType(1);
            } else {
                // Period type is weekly date range
                filter.setPeriodType(2);
            }
        }
        // Determine the start and end date
        Calendar cal = Calendar.getInstance();
        Date start = null;
        Date end = null;
        switch (periodType) {
            case 1:
                end = cal.getTime();
                cal.add(Calendar.DATE, -13);
                start = cal.getTime();
                break;
            case 2:
                cal.add(Calendar.DATE, (2 - cal.get(Calendar.DAY_OF_WEEK)));
                cal.add(Calendar.DATE, 6);
                end = cal.getTime();
                cal.add(Calendar.DATE, -6);
                cal.add(Calendar.DATE, -7 * 7);
                start = cal.getTime();
                break;
            case 3:
                // Set start date
                if (StringUtils.isBlank(startDate)) {
                    throw new CustomException("Please choose the start date");
                }
                try {
                    start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                } catch (Exception e) {
                }
                if (start == null) {
                    throw new CustomException("Invalid start date format");
                }
                // Set end date
                if (StringUtils.isBlank(endDate)) {
                    throw new CustomException("Please choose the end date");
                }
                try {
                    end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                } catch (Exception e) {
                }
                if (end == null) {
                    throw new CustomException("Invalid end date format");
                }
                // The end date must be after start date
                if (end.before(start)) {
                    throw new CustomException("The start date must be before the end date");
                }
                break;
            case 4:
                // Determine number of periods
                Integer numOfPeriods = StringUtils.isBlank(numberOfPeriods) ? null : Integer.valueOf(numberOfPeriods);
                if (numOfPeriods == null) {
                    throw new CustomException("The number of periods cannot be blank.");
                }
                if (numOfPeriods <= 0) {
                    throw new CustomException("Invalid the number of periods. It's must be greater than zero.");
                }
                cal.add(Calendar.DATE, (2 - cal.get(Calendar.DAY_OF_WEEK)));
                cal.add(Calendar.DATE, 6);
                end = cal.getTime();
                cal.add(Calendar.DATE, -6);
                cal.add(Calendar.DATE, -(numOfPeriods - 1) * 7);
                start = cal.getTime();
                break;
        }
        filter.setStartDate(start);
        filter.setEndDate(end);
        // Default sort field is customer_code
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 0 (ascending)
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        // Set default page
        page = StringUtils.isBlank(page) ? "1" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        return filter;
    }

    private List<String> buildColumns(WebshipCustomerHistoryFilter filter) throws Exception {
        List<String> columnList = new ArrayList<String>();
        columnList.add("customer_code");
        columnList.add("customer_name");
        columnList.add("sale_rep_name");
        if (filter == null) {
            throw new Exception("the webship customer history filter is null");
        } else if (filter.getPeriodList() == null) {
            throw new Exception("the webship customer history filter: no date range to search");
        }
        for (int i = 1; i <= filter.getPeriodList().size(); i++) {
            columnList.add("d_" + String.valueOf(i));
        }
        return columnList;
    }

    private List<String> buildDisplayColumns(WebshipCustomerHistoryFilter filter) throws Exception {
        List<String> columnList = new LinkedList<String>();
        columnList.add(this.getLocalizationText("Customer Code"));
        columnList.add(this.getLocalizationText("Customer Name"));
        columnList.add(this.getLocalizationText("Sale Rep Name"));
        if (filter == null) {
            throw new Exception("the webship customer history filter is null");
        } else if (filter.getPeriodList() == null) {
            throw new Exception("the webship customer history filter: no date range to search");
        }
        List<Date> periods = filter.getPeriodList();
        Integer periodType = filter.getPeriodType();
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < periods.size(); i++) {
            String column = "";
            if (periodType == 1) {
                column = DateUtils.convertDateToString(periods.get(i), "d/M", null);
            } else {
                cal.setTime(periods.get(i));
                column = DateUtils.convertDateToString(periods.get(i), "d/M", null);
                cal.add(Calendar.DATE, 6);
                column += "-" + DateUtils.convertDateToString(cal.getTime(), "d/M", null);
            }
            columnList.add(column);
        }
        return columnList;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(String numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
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

    public Paging<Map<String, String>> getReport() {
        return report;
    }

    public void setReport(Paging<Map<String, String>> report) {
        this.report = report;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getDisplayColumns() {
        return displayColumns;
    }

    public void setDisplayColumns(List<String> displayColumns) {
        this.displayColumns = displayColumns;
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

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public List<ServiceModel> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<ServiceModel> carriers) {
        this.carriers = carriers;
    }

    public List<ShipmentTypeModel> getServices() {
        return services;
    }

    public void setServices(List<ShipmentTypeModel> services) {
        this.services = services;
    }
}
