package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.customer.invoicedetail.CustomerCreditNoteDetailModel;
import com.gms.xms.model.reports.customer.invoicedetail.CustomerInvoiceDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.customer.invoicedetail.CustomerInvoiceDetailRenderImp;
import com.gms.xms.workflow.render.report.customer.invoicedetail.ICustomerInvoiceDetailRender;
import com.gms.xms.workflow.service.report.customer.invoicedetail.CustomerInvoiceDetailServiceImp;
import com.gms.xms.workflow.service.report.customer.invoicedetail.ICustomerInvoiceDetailService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Posted from CustomerInvoiceDetailController.java
 * <p>
 * Author dattrinh 9:02:51 AM
 */
public class CustomerInvoiceDetailController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String franchiseCode;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;
    private Paging<CustomerInvoiceDetailModel> invoiceDetailReport;
    private CustomerInvoiceDetailModel invoiceDetailSummary;
    private Paging<CustomerCreditNoteDetailModel> creditNoteDetailReport;
    private CustomerCreditNoteDetailModel creditNoteDetailSummary;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareFranchises();
            preparePageSizes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doInvoiceDetailReport() {
        try {
            searchInvoiceDetail();
        } catch (Exception e) {
            handleError(e, e.getMessage());
        }
        return "success";
    }

    public String doCreditNoteDetailReport() {
        try {
            searchCreditNoteDetail();
        } catch (Exception e) {
            handleError(e, e.getMessage());
        }
        return "success";
    }

    public String printInvoice() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_invoice_detail_report_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerInvoiceDetailFilter filter = this.buildInvoiceDetailFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerInvoiceDetailRender render = new CustomerInvoiceDetailRenderImp(this.getAddInfoMap());
                render.renderCustomerInvoiceDetailHtmlFile(filter, htmlFilePath, realPath);
                this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportInvoice() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "customer_invoice_detail_report";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                CustomerInvoiceDetailFilter filter = this.buildInvoiceDetailFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerInvoiceDetailRender render = new CustomerInvoiceDetailRenderImp(this.getAddInfoMap());
                render.renderCustomerInvoiceDetailXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String printCreditNote() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_credit_note_detail_report_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerCreditNoteDetailFilter filter = this.buildCreditNoteDetailFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerInvoiceDetailRender render = new CustomerInvoiceDetailRenderImp(this.getAddInfoMap());
                render.renderCustomerCreditNoteDetailHtmlFile(filter, htmlFilePath, realPath);
                this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String exportCreditNote() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "customer_credit_note_detail";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                CustomerCreditNoteDetailFilter filter = this.buildCreditNoteDetailFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerInvoiceDetailRender render = new CustomerInvoiceDetailRenderImp(this.getAddInfoMap());
                render.renderCustomerCreditNoteDetailXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void searchInvoiceDetail() throws Exception {
        ICustomerInvoiceDetailService invoiceDetailService = new CustomerInvoiceDetailServiceImp(this.getAddInfoMap());
        CustomerInvoiceDetailFilter filter = this.buildInvoiceDetailFilter();
        // Prepare for paging
        Long recordCount = invoiceDetailService.getInvoiceDetailCount(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<CustomerInvoiceDetailModel> paging = new Paging<CustomerInvoiceDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get customer invoice detail report data
        List<CustomerInvoiceDetailVo> invoiceDetailVos = invoiceDetailService.getInvoiceDetailReport(filter);
        List<CustomerInvoiceDetailModel> invoiceDetailModels = ModelUtils.createListModelFromVo(invoiceDetailVos, CustomerInvoiceDetailModel.class);
        paging.setRecords(invoiceDetailModels);
        this.setInvoiceDetailReport(paging);
        // Get summary of customer invoice detail report.
        CustomerInvoiceDetailVo invoiceDetailSummaryVo = invoiceDetailService.sumInvoiceDetailReport(filter);
        CustomerInvoiceDetailModel invoiceDetailSummaryModel = ModelUtils.createModelFromVo(invoiceDetailSummaryVo, CustomerInvoiceDetailModel.class);
        this.setInvoiceDetailSummary(invoiceDetailSummaryModel);
    }

    private void searchCreditNoteDetail() throws Exception {
        ICustomerInvoiceDetailService invoiceDetailService = new CustomerInvoiceDetailServiceImp(this.getAddInfoMap());
        CustomerCreditNoteDetailFilter filter = this.buildCreditNoteDetailFilter();
        // Prepare for paging
        Long recordCount = invoiceDetailService.getCreditNoteDetailCount(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<CustomerCreditNoteDetailModel> paging = new Paging<CustomerCreditNoteDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get customer invoice detail report data
        List<CustomerCreditNoteDetailVo> creditNoteDetailVos = invoiceDetailService.getCreditNoteDetailReport(filter);
        List<CustomerCreditNoteDetailModel> creditNoteDetailModels = ModelUtils.createListModelFromVo(creditNoteDetailVos, CustomerCreditNoteDetailModel.class);
        paging.setRecords(creditNoteDetailModels);
        this.setCreditNoteDetailReport(paging);
        // Get summary of customer credit note detail report.
        CustomerCreditNoteDetailVo creditNoteDetailSummaryVo = invoiceDetailService.sumCreditNoteDetailReport(filter);
        CustomerCreditNoteDetailModel creditNoteDetailSummaryModel = ModelUtils.createModelFromVo(creditNoteDetailSummaryVo, CustomerCreditNoteDetailModel.class);
        this.setCreditNoteDetailSummary(creditNoteDetailSummaryModel);
    }

    private void preparePageSizes() {
        setPageSizes(this.buildPageSizeList());
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

    private CustomerInvoiceDetailFilter buildInvoiceDetailFilter() throws Exception {
        CustomerInvoiceDetailFilter filter = new CustomerInvoiceDetailFilter();
        // Create report id if it's null
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
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
        // Default sort field is invoice code
        orderField = StringUtils.isBlank(orderField) ? "invoice_code" : orderField;
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

    private CustomerCreditNoteDetailFilter buildCreditNoteDetailFilter() throws Exception {
        CustomerCreditNoteDetailFilter filter = new CustomerCreditNoteDetailFilter();
        // Create report id if it's null
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
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
        // Default sort field is invoice code
        orderField = StringUtils.isBlank(orderField) ? "credit_code" : orderField;
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

    public Paging<CustomerInvoiceDetailModel> getInvoiceDetailReport() {
        return invoiceDetailReport;
    }

    public void setInvoiceDetailReport(Paging<CustomerInvoiceDetailModel> invoiceDetailReport) {
        this.invoiceDetailReport = invoiceDetailReport;
    }

    public Paging<CustomerCreditNoteDetailModel> getCreditNoteDetailReport() {
        return creditNoteDetailReport;
    }

    public void setCreditNoteDetailReport(Paging<CustomerCreditNoteDetailModel> creditNoteDetailReport) {
        this.creditNoteDetailReport = creditNoteDetailReport;
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

    public CustomerInvoiceDetailModel getInvoiceDetailSummary() {
        return invoiceDetailSummary;
    }

    public void setInvoiceDetailSummary(CustomerInvoiceDetailModel invoiceDetailSummary) {
        this.invoiceDetailSummary = invoiceDetailSummary;
    }

    public CustomerCreditNoteDetailModel getCreditNoteDetailSummary() {
        return creditNoteDetailSummary;
    }

    public void setCreditNoteDetailSummary(CustomerCreditNoteDetailModel creditNoteDetailSummary) {
        this.creditNoteDetailSummary = creditNoteDetailSummary;
    }
}
