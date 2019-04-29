package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.customer.inactivity.CustomerInactivityModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.report.customer.inactivity.CustomerInactivityServiceImp;
import com.gms.xms.persistence.service.report.customer.inactivity.ICustomerInactivityService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.reports.customer.inactivity.CustomerInactivityVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.customer.inactivity.CustomerInactivityRenderImp;
import com.gms.xms.workflow.render.report.customer.inactivity.ICustomerInactivityRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from CustomerInactivityController.java
 * <p>
 * Author dattrinh 8:59:29 AM
 */
public class CustomerInactivityController extends JsonBaseController {
    private static final long serialVersionUID = -4944015364168814020L;
    private String franchiseCode;
    private String showCustomerOption;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<CustomerInactivityModel> inactivityReport;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            preparePageSizes();
            prepareFranchises();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareInactivityReport();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                ICustomerInactivityRender render = new CustomerInactivityRenderImp(this.getAddInfoMap());
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_inactivity_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerInactivityFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                render.renderCustomerInactivityHtmlFile(filter, htmlFilePath, realPath);
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
                ICustomerInactivityRender render = new CustomerInactivityRenderImp(this.getAddInfoMap());
                this.fileName = "customer_inactivity";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                CustomerInactivityFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                render.renderCustomerInactivityXlsFile(filter, outPutFilePath);
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

    private CustomerInactivityFilter buildFilter() throws Exception {
        CustomerInactivityFilter filter = new CustomerInactivityFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set start date
        if (StringUtils.isBlank(startDate)) {
            throw new Exception("Please choose the start date");
        }
        Date start = null;
        try {
            start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (start == null) {
            throw new Exception("Invalid start date format");
        }
        filter.setStartDate(start);
        // Set end date
        if (StringUtils.isBlank(endDate)) {
            throw new Exception("Please choose the end date");
        }
        Date end = null;
        try {
            end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (end == null) {
            throw new Exception("Invalid end date format");
        }
        filter.setEndDate(end);
        // The end date must be after start date
        if (end.before(start)) {
            throw new Exception("The start date must be before the end date");
        }
        // Set show customer option
        Integer showOption = null;
        try {
            showOption = Integer.valueOf(showCustomerOption);
            if (showOption < 1 || showOption > 7) {
                throw new Exception("Invalid show customer option");
            }
        } catch (Exception e) {
            throw new Exception("Invalid show customer option");
        }
        filter.setShowCustomerOption(showOption);
        // Default sort field is period
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 1 (descending)
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

    private void prepareInactivityReport() throws Exception {
        ICustomerInactivityService inactivityService = new CustomerInactivityServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        CustomerInactivityFilter filter = this.buildFilter();
        Long recordCount = inactivityService.getInactivityCount(filter);
        Paging<CustomerInactivityModel> paging = new Paging<CustomerInactivityModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<CustomerInactivityVo> activationVos = inactivityService.getInactivityReport(filter);
        List<CustomerInactivityModel> activationModels = ModelUtils.createListModelFromVo(activationVos, CustomerInactivityModel.class);
        paging.setRecords(activationModels);
        this.setInactivityReport(paging);
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getShowCustomerOption() {
        return showCustomerOption;
    }

    public void setShowCustomerOption(String showCustomerOption) {
        this.showCustomerOption = showCustomerOption;
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

    public Paging<CustomerInactivityModel> getInactivityReport() {
        return inactivityReport;
    }

    public void setInactivityReport(Paging<CustomerInactivityModel> inactivityReport) {
        this.inactivityReport = inactivityReport;
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

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
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
}