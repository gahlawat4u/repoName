package com.gms.xms.weblib.controller.invoicing.manageinvoice;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.StatementInvoiceFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.invoicing.manageinvoice.statement.StatementCustomerModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.statement.StatementInvoiceModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.dao.invoicing.StatementDao;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.statement.StatementInvoiceVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.statements.IStatementsRender;
import com.gms.xms.workflow.render.statements.StatementsRenderImp;
import com.gms.xms.workflow.service.customeraging.CustomerAgingServiceImp;
import com.gms.xms.workflow.service.customeraging.ICustomerAgingService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from StatementsController
 * <p>
 * Author dattrinh Mar 16, 2016 10:00:00 AM
 */
public class StatementsController extends AdminJsonBaseController {

    private static final long serialVersionUID = 2401398304018900677L;

    // Filter properties.
    private String franchiseCode;
    private String customerCode;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    // Models.
    private List<StatementCustomerModel> customers;
    private CustomerAgingModel aging;
    private String currentDate;
    private String companyAddress;
    private String billingAddress;
    private String mailPaymentAddress;
    private Paging<StatementInvoiceModel> invoices;
    private StatementInvoiceModel summary;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            this.setCustomers(new ArrayList<StatementCustomerModel>());
            this.prepareFranchises();
            this.preparePageSizes();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String detail() {
        try {
            // Valid customer code.
            if (StringUtils.isBlank(this.getCustomerCode())) {
                throw new CustomException("Please choose a customer.");
            }
            // Get current date.
            Date current = Calendar.getInstance().getTime();
            this.setCurrentDate(DateUtils.convertDateToString(current, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            // Get customer aging.
            this.prepareCustomerAging();
            // Get company address.
            this.prepareCompanyAddress();
            // Get mail payment to address.
            this.prepareMailPaymentAddress();
            // Get billing to address.
            this.prepareBillingAddress();
            // Get invoice list.
            this.prepareInvoices();
            // Get page sizes list.
            this.preparePageSizes();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String invoicePaging() {
        try {
            this.prepareInvoices();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            // Get current date.
            Date current = Calendar.getInstance().getTime();
            String currentDateStr = DateUtils.convertDateToString(current, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            IStatementsRender render = new StatementsRenderImp(this.getAddInfoMap());
            StatementInvoiceFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/statements_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlFile(this.getCustomerCode(), filter, currentDateStr, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportPdf() {
        try {
            if (!StringUtils.isBlank(this.getCustomerCode())) {
                // Get current date.
                Date current = Calendar.getInstance().getTime();
                String currentDateStr = DateUtils.convertDateToString(current, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                this.setFileName("statements_" + currentDateStr + "_" + this.getCustomerCode() + ".pdf");
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/statements_" + currentDateStr + "_" + AppUtils.createMessageReference() + ".html";
                String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/statements_" + currentDateStr + "_" + AppUtils.createMessageReference() + ".pdf";
                IStatementsRender render = new StatementsRenderImp(this.getAddInfoMap());
                StatementInvoiceFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                render.generateHtmlForPdfFile(this.getCustomerCode(), filter, currentDateStr, outputFilePath);
                AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
                this.setStream(new FileInputStream(new File(pdfFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            } else {
                throw new CustomException("Please select a customer to export.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareCustomerAging() throws Exception {
        ICustomerAgingService agingService = new CustomerAgingServiceImp(this.getAddInfoMap());
        CustomerAgingVo agingVo = agingService.selectByCustomerCode(this.getCustomerCode());
        CustomerAgingModel agingModel = ModelUtils.createModelFromVo(agingVo, CustomerAgingModel.class);
        this.setAging(agingModel);
    }

    protected void prepareCompanyAddress() throws Exception {
        String companyAddress = SystemSettingCache.getInstance().getValueByKey("System Address for Tax Invoice");
        companyAddress = StringUtils.replace(companyAddress, "\n", "<br/>");
        // Get customer billing address.
        String customerCode = this.getCustomerCode().substring(0, 3);
        customerCode += "00001";
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        CustomerBillingAddressVo customerBillingAddressVo = billingAddressDao.getByCustomerCode(customerCode);
        // Replace [PHONE_NUMBER] by phone number of the franchise billing
        // address.
        companyAddress = StringUtils.replace(companyAddress, "[PHONE_NUMBER]", customerBillingAddressVo.getBillingPhone());
        this.setCompanyAddress(companyAddress);
    }

    protected void prepareMailPaymentAddress() {
        String mailPaymentTo = "";
        mailPaymentTo = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
        mailPaymentTo = StringUtils.replace(mailPaymentTo, "\n", "<br/>");
        this.setMailPaymentAddress(mailPaymentTo);
    }

    protected void prepareBillingAddress() throws Exception {
        StatementDao dao = new StatementDao();
        StatementCustomerBillingAddressVo billingAddressVo = dao.getBillingAddressByCustCode(this.getCustomerCode());
        String billingTo = "";
        if (billingAddressVo != null) {
            billingTo += billingAddressVo.getCustomerName();
            billingTo += "<br/>" + billingAddressVo.getAddress1();
            if (!StringUtils.isBlank(billingAddressVo.getAddress2())) {
                billingTo += "<br/>" + billingAddressVo.getAddress2();
            }
            billingTo += "<br/>" + billingAddressVo.getPostalCode();
            if (!StringUtils.isBlank(billingAddressVo.getCity())) {
                billingTo += ", " + billingAddressVo.getCity();
            }
            if (!StringUtils.isBlank(billingAddressVo.getCountryName())) {
                billingTo += ", " + billingAddressVo.getCountryName();
            }
            this.setBillingAddress(billingTo);
        }
    }

    protected void prepareInvoices() throws Exception {
        // Get filter.
        StatementInvoiceFilter filter = this.buildFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        StatementDao dao = new StatementDao();
        long recordCount = dao.countInvoiceByCustCode(filter);
        // Build paging object.
        Paging<StatementInvoiceModel> paging = new Paging<StatementInvoiceModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get invoice list.
        List<StatementInvoiceVo> statementInvoiceVos = dao.getInvoiceByCustCode(filter);
        List<StatementInvoiceModel> statementInvoiceModels = ModelUtils.createListModelFromVo(statementInvoiceVos, StatementInvoiceModel.class);
        paging.setRecords(statementInvoiceModels);
        // Get summary record.
        if (statementInvoiceVos != null && statementInvoiceVos.size() > 0) {
            StatementInvoiceVo summaryVo = dao.sumInvoiceByCustCode(filter);
            StatementInvoiceModel summaryModel = ModelUtils.createModelFromVo(summaryVo, StatementInvoiceModel.class);
            this.setSummary(summaryModel);
        }
        // Set invoice list.
        this.setInvoices(paging);
    }

    public String searchCustomers() {
        try {
            this.prepareCustomers();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    protected StatementInvoiceFilter buildFilter() throws Exception {
        StatementInvoiceFilter filter = new StatementInvoiceFilter();
        filter.setCustomerCode(this.getCustomerCode());
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "invoice_code" : this.getOrderField());
        return filter;
    }

    protected void prepareCustomers() throws Exception {
        // Create filter to get customer list by franchise.
        String franchiseList = this.buildFranchiseCodeList(this.getFranchiseCode());
        // Get customer list.
        StatementDao dao = new StatementDao();
        List<StatementCustomerVo> customerVos = dao.getCustomerByFranchises(franchiseList);
        List<StatementCustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, StatementCustomerModel.class);
        this.setCustomers(customerModels);
    }

    public List<StatementCustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<StatementCustomerModel> customers) {
        this.customers = customers;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerAgingModel getAging() {
        return aging;
    }

    public void setAging(CustomerAgingModel aging) {
        this.aging = aging;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getMailPaymentAddress() {
        return mailPaymentAddress;
    }

    public void setMailPaymentAddress(String mailPaymentAddress) {
        this.mailPaymentAddress = mailPaymentAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Paging<StatementInvoiceModel> getInvoices() {
        return invoices;
    }

    public void setInvoices(Paging<StatementInvoiceModel> invoices) {
        this.invoices = invoices;
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

    public StatementInvoiceModel getSummary() {
        return summary;
    }

    public void setSummary(StatementInvoiceModel summary) {
        this.summary = summary;
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

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }
}
