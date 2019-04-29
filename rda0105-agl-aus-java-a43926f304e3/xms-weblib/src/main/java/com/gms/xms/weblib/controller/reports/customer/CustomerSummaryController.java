package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.FormaterUtils;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import com.gms.xms.txndb.vo.reports.customer.status.CustomerStatusColumn;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.customersummary.CustomerSummaryRenderImp;
import com.gms.xms.workflow.render.customersummary.ICustomerSummaryRender;
import com.gms.xms.workflow.service.customersummary.CustomerSummaryServiceImp;
import com.gms.xms.workflow.service.customersummary.ICustomerSummaryService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from CustomerSummaryController
 * <p>
 * Author DatTV Aug 26, 2015
 */
public class CustomerSummaryController extends AdminJsonBaseController {

    private static final long serialVersionUID = -156727908825698003L;

    private List<UserModel> saleReps;
    private List<String> periods;
    private String rptTxnId;
    private String page;
    private String pageSize;
    private String saleRepId;
    private String franchiseCode;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private Paging<Map<String, String>> customers;
    private Map<String, String> summary;
    private String excludeGst;
    private String excludeGstSurcharge;
    private String carrierList;
    private String serviceList;
    private String showCostRevenue;
    private String htmlExportString;
    private List<ServiceModel> services;
    private List<CustomerStatusColumn> columns;

    private String fileName;
    private InputStream stream;

    public String show() {
        try {
            determineAdminLevel();
            prepareData();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            doSearch();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String export() {
        try {
            determineAdminLevel();
            int userLevel = this.getAdminLevel();
            this.fileName = "customer_summary_report.xls";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName;
            CustomerSummaryFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ICustomerSummaryService customerSummaryService = new CustomerSummaryServiceImp(this.getAddInfoMap());
            List<Map<String, String>> summary = customerSummaryService.selectByFilter(filter);
            List<Map<String, Object>> exportSummary = this.formatDataTableForExport(summary);
            Map<String, String> summaryTotal = customerSummaryService.sumByFilter(filter);
            Map<String, Object> exportSummaryTotal = this.formatDataRowExport(summaryTotal);
            List<CustomerStatusColumn> columns = this.buildDynamicColumns(filter, userLevel);

            // Render the file
            ICustomerSummaryRender render = new CustomerSummaryRenderImp(this.getAddInfoMap());
            render.renderXLS(columns, exportSummary, exportSummaryTotal, outPutFilePath);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String print() {
        try {
            determineAdminLevel();
            int userLevel = this.getAdminLevel();
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_summary_report_" + request.getSession().getId() + ".html";
            ICustomerSummaryRender render = new CustomerSummaryRenderImp(this.getAddInfoMap());
            CustomerSummaryFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ICustomerSummaryService customerSummaryService = new CustomerSummaryServiceImp(this.getAddInfoMap());
            List<Map<String, String>> summary = customerSummaryService.selectByFilter(filter);
            this.formatDataTable(summary);
            Map<String, String> summaryTotal = customerSummaryService.sumByFilter(filter);
            this.formatDataRow(summaryTotal);
            List<CustomerStatusColumn> columns = this.buildDynamicColumns(filter, userLevel);
            render.renderHTML(columns, summary, summaryTotal, outPutFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outPutFilePath));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    // private Map<String, Object> getExportData() throws Exception {
    // this.determineAdminLevel();
    // int userLevel = this.getAdminLevel();
    // CustomerSummaryFilter filter = null;
    // ICustomerSummaryService summaryService = new CustomerSummaryServiceImp();
    // filter = this.buildFilter();
    // // Set paging info
    // filter.setPage(null);
    // filter.setPageSize(null);
    // Get customer summary list
    // List<CustomerSummaryVo> customerSummaryVos =
    // summaryService.selectByFilter(filter);
    // if (customerSummaryVos != null && customerSummaryVos.size() > 0) {
    // for (CustomerSummaryVo customerSummaryVo : customerSummaryVos) {
    // this.removeValueFromCarrierCost(userLevel, customerSummaryVo);
    // }
    // }
    // List<CustomerSummaryModel> customerSummaryModels =
    // ModelUtils.createListModelFromVo(customerSummaryVos,
    // CustomerSummaryModel.class);
    //
    // // Get summary record
    // CustomerSummaryVo summaryVo = summaryService.sumByFilter(filter);
    // CustomerSummaryModel summaryModel =
    // ModelUtils.createModelFromVo(summaryVo, CustomerSummaryModel.class);
    // Map<String, Object> data = new HashMap<String, Object>();
    // data.put("summaryList", customerSummaryModels);
    // data.put("totalSummary", summaryModel);
    // return null;
    // }

    private List<CustomerStatusColumn> buildDynamicColumns(CustomerSummaryFilter filter, int userLevel) throws Exception {
        List<CustomerStatusColumn> columnList = new ArrayList<CustomerStatusColumn>();
        // Customer #.
        CustomerStatusColumn customerNumberCol = new CustomerStatusColumn();
        customerNumberCol.setFieldName("customer_code");
        customerNumberCol.setColumnName(this.getLocalizationText("Customer #"));
        columnList.add(customerNumberCol);
        // Customer Name.
        CustomerStatusColumn customerNameCol = new CustomerStatusColumn();
        customerNameCol.setFieldName("customer_name");
        customerNameCol.setColumnName(this.getLocalizationText("Customer Name"));
        columnList.add(customerNameCol);
        // Sales Rep Name.
        CustomerStatusColumn salesRepNameCol = new CustomerStatusColumn();
        salesRepNameCol.setFieldName("sales_rep_name");
        salesRepNameCol.setColumnName(this.getLocalizationText("Sales Rep"));
        columnList.add(salesRepNameCol);
        // Customer Revenue.
        CustomerStatusColumn custRevCol = new CustomerStatusColumn();
        custRevCol.setFieldName("cust_cost");
        custRevCol.setColumnName(this.getLocalizationText("Customer Revenue"));
        columnList.add(custRevCol);
        // Franchise Cost.
        CustomerStatusColumn franCostCol = new CustomerStatusColumn();
        franCostCol.setFieldName("fran_cost");
        franCostCol.setColumnName(this.getLocalizationText("Franchise Cost"));
        columnList.add(franCostCol);
        // Carrier Cost.
        if (userLevel < 3) {
            CustomerStatusColumn carrierCostCol = new CustomerStatusColumn();
            carrierCostCol.setFieldName("carrier_cost");
            carrierCostCol.setColumnName(this.getLocalizationText("Carrier Cost"));
            columnList.add(carrierCostCol);
        }
        // Gross Margin.
        CustomerStatusColumn grossMarginCol = new CustomerStatusColumn();
        grossMarginCol.setFieldName("gross_margin");
        grossMarginCol.setColumnName(this.getLocalizationText("Gross Margin"));
        columnList.add(grossMarginCol);
        // Gross Margin %.
        CustomerStatusColumn grossMarginPctCol = new CustomerStatusColumn();
        grossMarginPctCol.setFieldName("gross_margin_pct");
        grossMarginPctCol.setColumnName(this.getLocalizationText("Gross Margin %"));
        columnList.add(grossMarginPctCol);
        // Customer Cost Base Charge.
        CustomerStatusColumn custBcCol = new CustomerStatusColumn();
        custBcCol.setFieldName("cust_bc_cost");
        custBcCol.setColumnName(this.getLocalizationText("Customer Cost (Base Charge)"));
        columnList.add(custBcCol);
        // Franchise Cost Base Charge.
        CustomerStatusColumn franBcCol = new CustomerStatusColumn();
        franBcCol.setFieldName("fran_bc_cost");
        franBcCol.setColumnName(this.getLocalizationText("Franchise Cost (Base Charge)"));
        columnList.add(franBcCol);
        // Carrier Cost Base Charge.
        if (userLevel < 3) {
            CustomerStatusColumn carrierBcCol = new CustomerStatusColumn();
            carrierBcCol.setFieldName("carrier_bc_cost");
            carrierBcCol.setColumnName(this.getLocalizationText("Carrier Cost (Base Charge)"));
            columnList.add(carrierBcCol);
        }
        // Margin On Base Charge.
        CustomerStatusColumn marginOnBcCol = new CustomerStatusColumn();
        marginOnBcCol.setFieldName("margin_on_bc");
        marginOnBcCol.setColumnName(this.getLocalizationText("Margin On Base Charge"));
        columnList.add(marginOnBcCol);
        IServiceService iService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iService.getIntegratedServices();
        CustomerStatusColumn column = null;
        if (filter.getCarriers() != null || filter.getCarriers().size() > 0) {
            for (Integer carrierId : filter.getCarriers()) {
                String serviceName = null;
                for (ServiceVo service : serviceVos) {
                    if (service.getServiceId() == carrierId) {
                        serviceName = service.getServiceName();
                        break;
                    }
                }
                // Specific carrier: Cost (Carrier Cost)
                if (userLevel < 3) {
                    column = new CustomerStatusColumn();
                    column.setFieldName("carrier_cost_" + String.valueOf(carrierId));
                    column.setColumnName(serviceName + " " + this.getLocalizationText("Cost"));
                    column.setGroup(1);
                    columnList.add(column);
                }
                // Specific carrier: Revenue (Cust Cost)
                column = new CustomerStatusColumn();
                column.setFieldName("cust_cost_" + String.valueOf(carrierId));
                column.setColumnName(serviceName + " " + this.getLocalizationText("Revenue"));
                column.setGroup(1);
                columnList.add(column);
                // Specific carrier: Franchise Cost
                column = new CustomerStatusColumn();
                column.setFieldName("fran_cost_" + String.valueOf(carrierId));
                column.setColumnName(serviceName + " " + this.getLocalizationText("Franchise Cost"));
                columnList.add(column);
                // Specific carrier: Gross Margin
                column = new CustomerStatusColumn();
                column.setFieldName("gross_margin_" + String.valueOf(carrierId));
                column.setColumnName(serviceName + " " + this.getLocalizationText("Gross Margin"));
                columnList.add(column);
                // Specific carrier: Shipments
                column = new CustomerStatusColumn();
                column.setFieldName("shipment_count_" + String.valueOf(carrierId));
                column.setColumnName(serviceName + " " + this.getLocalizationText("Shipments"));
                columnList.add(column);
            }
        }
        // Total Shipment Count.
        CustomerStatusColumn totalShipmentCountCol = new CustomerStatusColumn();
        totalShipmentCountCol.setFieldName("total_shipment_count");
        totalShipmentCountCol.setColumnName(this.getLocalizationText("Total Shipments"));
        columnList.add(totalShipmentCountCol);
        // Avg. Margin Per AWB.
        CustomerStatusColumn avgMarginPerAwbCol = new CustomerStatusColumn();
        avgMarginPerAwbCol.setFieldName("avg_margin_per_awb");
        avgMarginPerAwbCol.setColumnName(this.getLocalizationText("Avg.Margin Per AWB"));
        columnList.add(avgMarginPerAwbCol);
        return columnList;
    }

    private void formatDataRow(Map<String, String> row) {
        if (row == null) {
            return;
        }
        for (String key : row.keySet()) {
            // If column is double number.
            if (key.contains("margin") || key.contains("cost")) {
                Double num = Double.valueOf(row.get(key));
                // If column is percent number.
                if (key.contains("_pct")) {
                    row.put(key, FormaterUtils.double2String(num));
                } else {
                    row.put(key, this.getCurrencySymbol() + FormaterUtils.double2String(num));
                }
            }
            // If column is long number.
            if (key.contains("shipment_count")) {
                Long num = Long.valueOf(row.get(key));
                row.put(key, FormaterUtils.int2String(num));
            }
        }
    }

    private Map<String, Object> formatDataRowExport(Map<String, String> row) {
        Map<String, Object> newData = new HashMap<>();
        if (row == null) {
            return null;
        }
        for (String key : row.keySet()) {
            // If column is double number.
            if (key.contains("margin") || key.contains("cost")) {
                Double num = MathUtils.round(Double.valueOf(row.get(key)), 2);
                newData.put(key, num);
            } else if (key.contains("shipment_count")) {
                Long num = Long.valueOf(row.get(key));
                newData.put(key, num);
            } else {
                newData.put(key, row.get(key));
            }
        }
        return newData;
    }

    private void formatDataTable(List<Map<String, String>> report) {
        if (report == null) {
            return;
        }
        for (Map<String, String> record : report) {
            this.formatDataRow(record);
        }
    }

    private List<Map<String, Object>> formatDataTableForExport(List<Map<String, String>> report) {
        List<Map<String, Object>> newData = new LinkedList<>();
        if (report == null) {
            return null;
        }
        for (Map<String, String> record : report) {
            Map<String, Object> newMap = this.formatDataRowExport(record);
            newData.add(newMap);
        }
        return newData;
    }

    private void doSearch() throws Exception {
        this.determineAdminLevel();
        int userLevel = this.getAdminLevel();
        // Create filter
        CustomerSummaryFilter filter = this.buildFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        ICustomerSummaryService summaryService = new CustomerSummaryServiceImp(this.getAddInfoMap());
        long recordCount = summaryService.countByFilter(filter);
        // Set paging info
        Paging<Map<String, String>> paging = new Paging<Map<String, String>>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get customer summary list
        paging.setRecords(summaryService.selectByFilter(filter));
        this.formatDataTable(paging.getRecords());
        this.setCustomers(paging);
        // Get summary record
        Map<String, String> summary = summaryService.sumByFilter(filter);
        this.formatDataRow(summary);
        this.setSummary(summary);
        // Set display columns
        this.setColumns(this.buildDynamicColumns(filter, userLevel));
    }

    private CustomerSummaryFilter buildFilter() throws Exception {
        CustomerSummaryFilter filter = new CustomerSummaryFilter();
        // Get user id and user level.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        filter.setUserId(Long.valueOf(userId));
        filter.setUserLevel(Double.valueOf(userLevel).intValue());
        // Create new report transaction id if it's null
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
        filter.setFranchiseList(this.buildFranchiseCodeList(StringUtils.isBlank(franchiseCode) ? "All" : franchiseCode));
        // Set start date
        if (StringUtils.isBlank(startDate)) {
            throw new CustomException("Please choose the start date");
        }
        Date start = null;
        try {
            start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (start == null) {
            throw new CustomException("Invalid start date format");
        }
        filter.setStartDate(start);
        // Set end date
        if (StringUtils.isBlank(endDate)) {
            throw new CustomException("Please choose the end date");
        }
        Date end = null;
        try {
            end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (end == null) {
            throw new CustomException("Invalid end date format");
        }
        filter.setEndDate(end);
        // The end date must be after start date
        if (end.before(start)) {
            throw new CustomException("The start date must be before the end date");
        }
        // Set page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        // Set page
        page = StringUtils.isBlank(page) ? "1" : page;
        filter.setPage(Integer.valueOf(page));
        filter.setSaleRepId(StringUtils.isBlank(saleRepId) ? null : Long.valueOf(saleRepId));
        filter.setExcludeGst(Boolean.valueOf(excludeGst));
        filter.setExcludeDuty(Boolean.valueOf(excludeGstSurcharge));
        filter.setServiceList(serviceList);
        filter.setCarriers(this.buildCarrierList(carrierList));
        // Default sort field is customer_code
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 0 (ascending)
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        // Set default page
        page = StringUtils.isBlank(page) ? "0" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        return filter;
    }

    private List<Integer> buildCarrierList(String carrierList) {
        List<Integer> result = new ArrayList<Integer>();
        if (StringUtils.isBlank(carrierList)) {
            return result;
        }
        String[] list = carrierList.split(",");
        for (String item : list) {
            result.add(Integer.valueOf(item));
        }
        return result;
    }

    private void prepareData() throws Exception {
        prepareFranchises();
        prepareSaleRepList();
        prepareDateRange();
        preparePageSizes();
        prepareServices();
    }

    protected void prepareServices() throws Exception {
        IServiceService iService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iService.getIntegratedServiceWithShipmentTypes();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    private void prepareSaleRepList() throws Exception {
        IUserService userService = new UserServiceImp();
        List<UserVo> userVos = userService.getSaleReps(this.buildFranchiseList("All"));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSaleReps(userModels);
    }

    private void prepareDateRange() {
        this.setPeriods(this.buildDateRange());
    }

    private List<String> buildDateRange() {
        List<String> result = new ArrayList<String>();

        // Get current date.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, (8 - calendar.get(Calendar.DAY_OF_WEEK)));

        SimpleDateFormat formatDate = new SimpleDateFormat(AppConstants.APP_SETTINGS.getDefaultDateFormat());

        for (int i = 0; i < 10; i++) {
            String endDate = formatDate.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String startDate = formatDate.format(calendar.getTime());
            String input = startDate + " - " + endDate;
            result.add(input);
            calendar.add(Calendar.DATE, -1);
        }

        return result;
    }

    public List<UserModel> getSaleReps() {
        return saleReps;
    }

    public void setSaleReps(List<UserModel> saleReps) {
        this.saleReps = saleReps;
    }

    public List<String> getPeriods() {
        return periods;
    }

    public void setPeriods(List<String> periods) {
        this.periods = periods;
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

    public String getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(String saleRepId) {
        this.saleRepId = saleRepId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public Paging<Map<String, String>> getCustomers() {
        return customers;
    }

    public void setCustomers(Paging<Map<String, String>> customers) {
        this.customers = customers;
    }

    public Map<String, String> getSummary() {
        return summary;
    }

    public void setSummary(Map<String, String> summary) {
        this.summary = summary;
    }

    public String getExcludeGst() {
        return excludeGst;
    }

    public void setExcludeGst(String excludeGst) {
        this.excludeGst = excludeGst;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getShowCostRevenue() {
        return showCostRevenue;
    }

    public void setShowCostRevenue(String showCostRevenue) {
        this.showCostRevenue = showCostRevenue;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }

    public String getExcludeGstSurcharge() {
        return excludeGstSurcharge;
    }

    public void setExcludeGstSurcharge(String excludeGstSurcharge) {
        this.excludeGstSurcharge = excludeGstSurcharge;
    }

    public String getCarrierList() {
        return carrierList;
    }

    public void setCarrierList(String carrierList) {
        this.carrierList = carrierList;
    }

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
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

    public List<CustomerStatusColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<CustomerStatusColumn> columns) {
        this.columns = columns;
    }
}