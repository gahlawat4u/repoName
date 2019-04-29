package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingColumnFlagsModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingColumnFlagsVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.render.customeraging.CustomerAgingRenderImp;
import com.gms.xms.workflow.render.customeraging.ICustomerAgingRender;
import com.gms.xms.workflow.service.customeraging.CustomerAgingServiceImp;
import com.gms.xms.workflow.service.customeraging.ICustomerAgingService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Posted from CustomerAgingController
 * <p>
 * Author DatTV Date Aug 14, 2015 10:23:36 AM
 */
public class CustomerAgingController extends JsonBaseController {

    private static final long serialVersionUID = 8449638779317264304L;

    private List<FranchiseInfoModel> franchises;
    private List<UserModel> collectors;
    private List<UserModel> saleReps;
    private List<String> pageSizes;
    private String collectorId;
    private String withBalanceOnly;
    private String minAvgDaysToPay;
    private String maxAvgDaysToPay;
    private String saleRepId;
    private String minInvoiceAge;
    private String maxInvoiceAge;
    private String minDaysOverdue;
    private String maxDaysOverdue;
    private String franchiseCode;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private String rptTxnId;
    private String agingType;
    private Paging<CustomerAgingModel> agingList;
    private CustomerAgingModel agingTotal;
    private CustomerAgingColumnFlagsModel columnFlags;
    private String htmlExportString;

    private String fileName;
    private InputStream stream;

    public String show() {
        try {
            // Set default order field and type
            this.setOrderField("customer_name");
            this.setOrderType("0");
            this.setPage("1");
            this.setAgingType("1"); // Default aging type is by invoice date
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            prepareData();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Prepare page size list
            pageSizes = this.buildPageSizeList();

            ICustomerAgingService customerAgingService = new CustomerAgingServiceImp(this.getAddInfoMap());
            CustomerAgingFilter filter = this.buildFilter();
            long recordCount = customerAgingService.countByFilter(filter);

            // Get total record
            int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            CustomerAgingVo agingTotalVo = customerAgingService.sumByFilter(filter);
            CustomerAgingModel agingTotalModel = ModelUtils.createModelFromVo(agingTotalVo, CustomerAgingModel.class);
            this.setAgingTotal(agingTotalModel);

            // Set paging info
            Paging<CustomerAgingModel> paging = new Paging<CustomerAgingModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());

            // Get data of page
            List<CustomerAgingVo> agingVos = customerAgingService.selectByFilter(filter);
            List<CustomerAgingModel> agingModels = ModelUtils.createListModelFromVo(agingVos, CustomerAgingModel.class);
            paging.setRecords(agingModels);
            this.setAgingList(paging);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadCollectorsAndSales() {
        try {
            prepareCollectorList();
            prepareSaleRepList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String print() {
        try {
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_aging_" + this.franchiseCode + ".html";
            CustomerAgingFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            CustomerAgingColumnFlagsVo columnFlags = ModelUtils.createVoFromModel(this.columnFlags, CustomerAgingColumnFlagsVo.class);
            ICustomerAgingRender render = new CustomerAgingRenderImp(this.getAddInfoMap());
            render.renderHTMLFile(filter, columnFlags, htmlFilePath);
            this.htmlExportString = AppUtils.readUTF8File2String(htmlFilePath);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String export() {
        try {
            String xlsFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_aging_" + this.franchiseCode + ".xls";
            CustomerAgingFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            CustomerAgingColumnFlagsVo columnFlags = ModelUtils.createVoFromModel(this.columnFlags, CustomerAgingColumnFlagsVo.class);
            ICustomerAgingRender render = new CustomerAgingRenderImp(this.getAddInfoMap());
            render.renderXLSFile(filter, columnFlags, xlsFilePath);
            this.setFileName("customer_aging_" + this.franchiseCode + ".xls");
            this.setStream(new FileInputStream(new File(xlsFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private CustomerAgingFilter buildFilter() throws Exception {
        CustomerAgingFilter filter = new CustomerAgingFilter();
        Long collectId = Long.valueOf(collectorId);
        Boolean withBalance = StringUtils.isBlank(withBalanceOnly) ? false : true;
        Integer minAvg = StringUtils.isBlank(minAvgDaysToPay) ? null : Integer.valueOf(minAvgDaysToPay);
        Integer maxAvg = StringUtils.isBlank(maxAvgDaysToPay) ? null : Integer.valueOf(maxAvgDaysToPay);
        Long saleId = Long.valueOf(saleRepId);
        Integer minAge = StringUtils.isBlank(minInvoiceAge) ? null : Integer.valueOf(minInvoiceAge);
        Integer maxAge = StringUtils.isBlank(maxInvoiceAge) ? null : Integer.valueOf(maxInvoiceAge);
        Integer minOverdue = StringUtils.isBlank(minDaysOverdue) ? null : Integer.valueOf(minDaysOverdue);
        Integer maxOverdue = StringUtils.isBlank(maxDaysOverdue) ? null : Integer.valueOf(maxDaysOverdue);
        filter.setCollectorId(collectId);
        filter.setWithBalanceOnly(withBalance);
        filter.setMinAvgDaysToPay(minAvg);
        filter.setMaxAvgDaysToPay(maxAvg);
        filter.setSalesRepId(saleId);
        filter.setMinInvoiceAge(minAge);
        filter.setMaxInvoiceAge(maxAge);
        filter.setMinDaysOverdue(minOverdue);
        filter.setMaxDaysOverdue(maxOverdue);
        filter.setPage(StringUtils.isBlank(page) ? 1 : Integer.valueOf(page));
        filter.setPageSize(StringUtils.isBlank(pageSize) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(pageSize));
        filter.setOrderField(orderField);
        filter.setOrderType(StringUtils.isBlank(orderType) ? 0 : Integer.valueOf(orderType));
        filter.setFranchiseCodeList(this.buildFranchiseCodeList(franchiseCode));
        String customerAgingEndDate = AppConstants.APP_SETTINGS.getCustomerAgingEndDate();
        Date agingEndDate = null;
        if (!StringUtils.isBlank(customerAgingEndDate)) {
            agingEndDate = DateUtils.convertStringToDate(customerAgingEndDate, AppConstants.GLOBAL_DATE_FORMAT, null);
        } else {
            agingEndDate = Calendar.getInstance().getTime();
        }
        filter.setEndDate(agingEndDate);
        // Set aging type.
        try {
            Integer agingType = Integer.valueOf(this.getAgingType());
            if (agingType != 0 && agingType != 1) {
                throw new CustomException("Unknown aging type.");
            }
            filter.setAgingType(agingType);
        } catch (Exception e) {
            throw new CustomException("Invalid aging type. It must be a integer number.");
        }
        // Create report transaction id if it doesn't exists
        if (StringUtils.isBlank(rptTxnId)) {
            String newRptTxnId = UUID.randomUUID().toString();
            this.setRptTxnId(newRptTxnId);
        }
        filter.setRptTxnId(rptTxnId);

        return filter;
    }

    private void prepareData() throws Exception {
        prepareFranchiseList();
        prepareCollectorList();
        prepareSaleRepList();
        pageSizes = this.buildPageSizeList();
    }

    private void prepareFranchiseList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    private void prepareCollectorList() throws Exception {
        IUserService userService = new UserServiceImp();
        List<UserVo> userVos = userService.getCollectorsByFranchises(this.buildFranchiseCodeList(franchiseCode));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setCollectors(userModels);
    }

    private void prepareSaleRepList() throws Exception {
        IUserService userService = new UserServiceImp();
        List<UserVo> userVos = userService.getSaleRepsByFranchises(this.buildFranchiseCodeList(franchiseCode));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSaleReps(userModels);
    }

    private String buildFranchiseCodeList(String franCode) throws Exception {
        String franchiseCodeList = "";
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            if (franchises == null) {
                prepareFranchiseList();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseCodeList += franchise.getCode() + ",";
            }
            franchiseCodeList = franchiseCodeList.substring(0, franchiseCodeList.length() - 1);
        } else {
            franchiseCodeList = franCode;
        }

        return franchiseCodeList;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<UserModel> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<UserModel> collectors) {
        this.collectors = collectors;
    }

    public List<UserModel> getSaleReps() {
        return saleReps;
    }

    public void setSaleReps(List<UserModel> saleReps) {
        this.saleReps = saleReps;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getWithBalanceOnly() {
        return withBalanceOnly;
    }

    public void setWithBalanceOnly(String withBalanceOnly) {
        this.withBalanceOnly = withBalanceOnly;
    }

    public String getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(String saleRepId) {
        this.saleRepId = saleRepId;
    }

    public String getMinInvoiceAge() {
        return minInvoiceAge;
    }

    public void setMinInvoiceAge(String minInvoiceAge) {
        this.minInvoiceAge = minInvoiceAge;
    }

    public String getMaxInvoiceAge() {
        return maxInvoiceAge;
    }

    public void setMaxInvoiceAge(String maxInvoiceAge) {
        this.maxInvoiceAge = maxInvoiceAge;
    }

    public String getMinDaysOverdue() {
        return minDaysOverdue;
    }

    public void setMinDaysOverdue(String minDaysOverdue) {
        this.minDaysOverdue = minDaysOverdue;
    }

    public String getMaxDaysOverdue() {
        return maxDaysOverdue;
    }

    public void setMaxDaysOverdue(String maxDaysOverdue) {
        this.maxDaysOverdue = maxDaysOverdue;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Paging<CustomerAgingModel> getAgingList() {
        return agingList;
    }

    public void setAgingList(Paging<CustomerAgingModel> agingList) {
        this.agingList = agingList;
    }

    public CustomerAgingModel getAgingTotal() {
        return agingTotal;
    }

    public void setAgingTotal(CustomerAgingModel agingTotal) {
        this.agingTotal = agingTotal;
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

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public CustomerAgingColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(CustomerAgingColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
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

    public String getMinAvgDaysToPay() {
        return minAvgDaysToPay;
    }

    public void setMinAvgDaysToPay(String minAvgDaysToPay) {
        this.minAvgDaysToPay = minAvgDaysToPay;
    }

    public String getMaxAvgDaysToPay() {
        return maxAvgDaysToPay;
    }

    public void setMaxAvgDaysToPay(String maxAvgDaysToPay) {
        this.maxAvgDaysToPay = maxAvgDaysToPay;
    }

    public String getAgingType() {
        return agingType;
    }

    public void setAgingType(String agingType) {
        this.agingType = agingType;
    }
}