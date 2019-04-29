package com.gms.xms.weblib.controller.reports.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.webship.WebshipQuoteHistoryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.report.webship.IWebshipQuoteHistoryService;
import com.gms.xms.persistence.service.report.webship.WebshipQuoteHistoryServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.reports.webship.WebshipQuoteHistoryVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.webship.IWebshipQuoteHistoryRender;
import com.gms.xms.workflow.render.report.webship.WebshipQuoteHistoryRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from WebshipQuoteHistoryController.java
 * <p>
 * Author dattrinh 1:52:30 PM
 */
public class WebshipQuoteHistoryController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String franchiseCode;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<WebshipQuoteHistoryModel> report;
    private List<CustomerModel> customers;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareFranchises();
            prepareCustomers(this.getFranchiseCode());
            preparePageSizes();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareReportData();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doCustomers() {
        try {
            prepareCustomers(this.getFranchiseCode());
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/webship_quote_history_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                WebshipQuoteHistoryFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IWebshipQuoteHistoryRender render = new WebshipQuoteHistoryRenderImp(this.getAddInfoMap());
                render.renderWebshipQuoteHistoryHtmlFile(filter, htmlFilePath, realPath);
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
                this.fileName = "webship_quote_history";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                WebshipQuoteHistoryFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IWebshipQuoteHistoryRender render = new WebshipQuoteHistoryRenderImp(this.getAddInfoMap());
                render.renderWebshipQuoteHistoryXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void prepareCustomers(String franCode) throws DaoException, Exception {
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            List<CustomerModel> customers = new ArrayList<CustomerModel>();
            this.setCustomers(customers);
        } else {
            ICustomerService customerService = new CustomerServiceImp();
            List<CustomerVo> customerVos = customerService.getQuotedCustomersByFranchises(franCode);
            List<CustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, CustomerModel.class);
            this.setCustomers(customerModels);
        }
    }

    private void prepareReportData() throws Exception {
        IWebshipQuoteHistoryService historyService = new WebshipQuoteHistoryServiceImp();
        WebshipQuoteHistoryFilter filter = this.buildFilter();
        // Prepare for paging
        Long recordCount = historyService.countWebshipQuoteHistoryReport(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<WebshipQuoteHistoryModel> paging = new Paging<WebshipQuoteHistoryModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the webship quote history report data
        List<WebshipQuoteHistoryVo> historyVos = historyService.getWebshipQuoteHistoryReport(filter);
        List<WebshipQuoteHistoryModel> historyModels = ModelUtils.createListModelFromVo(historyVos, WebshipQuoteHistoryModel.class);
        paging.setRecords(historyModels);
        // Set the webship quote history report data
        this.setReport(paging);
    }

    private WebshipQuoteHistoryFilter buildFilter() throws Exception {
        WebshipQuoteHistoryFilter filter = new WebshipQuoteHistoryFilter();
        // Set franchise list
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set customer code
        filter.setCustomerCode(customerCode);
        // Set start date
        if (StringUtils.isBlank(startDate)) {
            throw new Exception("Please choose the start date");
        }
        Date start = null;
        Date end = null;
        try {
            start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (start == null) {
            throw new Exception("Invalid start date format");
        }
        // Set end date
        if (StringUtils.isBlank(endDate)) {
            throw new Exception("Please choose the end date");
        }
        try {
            end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (end == null) {
            throw new Exception("Invalid end date format");
        }
        // The end date must be after start date
        if (end.before(start)) {
            throw new Exception("The start date must be before the end date");
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
        page = StringUtils.isBlank(page) ? "0" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        return filter;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public Paging<WebshipQuoteHistoryModel> getReport() {
        return report;
    }

    public void setReport(Paging<WebshipQuoteHistoryModel> report) {
        this.report = report;
    }

    public List<CustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerModel> customers) {
        this.customers = customers;
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
