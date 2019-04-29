package com.gms.xms.weblib.controller.reports.selfinsurance;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.model.reports.selfinsurance.InvoiceHistoryColumnFlagsModel;
import com.gms.xms.txndb.model.reports.selfinsurance.InvoicedAirbillModel;
import com.gms.xms.txndb.model.reports.selfinsurance.SummaryInvoicedAirbillModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.InvoiceHistoryColumnFlagsVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.render.report.invoicehistory.IInvoiceHistoryReportRender;
import com.gms.xms.workflow.render.report.invoicehistory.InvoiceHistoryReportRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from InvoicedAirbillReportController
 * <p>
 * Author dattrinh Mar 19, 2016 5:32:12 PM
 */
public class InvoicedAirbillReportController extends AdminJsonBaseController {
    private static final long serialVersionUID = 1537042182659846561L;

    // Filter properties.
    private String rptTxnId;
    private String searchType;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    // Model.
    private Paging<InvoicedAirbillModel> report;
    private SummaryInvoicedAirbillModel summary;
    private InvoiceHistoryColumnFlagsModel columnFlags;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            this.preparePageSizes();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            InvoicedAirbillFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Create context to call work flow to get invoiced airbill report
            // data.
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.INVOICED_AIRBILL_REPORT_FILTER, filter);
            context.put(Attributes.NLINKS, nLinks);
            context.put(Attributes.WFP_NAME, "Wfl-GetInvoicedAirbillReport");
            WorkFlowManager2.getInstance().process(context);
            if (!ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                // Get invoiced airbill report.
                Paging<InvoicedAirbillModel> paging = context.get(Attributes.INVOICED_AIRBILL_REPORT);
                this.setReport(paging);
                // Get summary invoiced airbills report.
                SummaryInvoicedAirbillModel summary = context.get(Attributes.SUMMARY_INVOICED_AIRBILL_REPORT);
                this.setSummary(summary);
                // Re-assign rpt txn id.
                InvoicedAirbillFilter newFilter = context.get(Attributes.INVOICED_AIRBILL_REPORT_FILTER);
                this.setRptTxnId(newFilter.getRptTxnId());
            } else {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoice_history_report_" + AppUtils.createMessageReference() + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                InvoicedAirbillFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                filter.setRptTxnId(this.getRptTxnId());
                Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.INVOICED_AIRBILL_REPORT_FILTER, filter);
                context.put(Attributes.NLINKS, nLinks);
                context.put(Attributes.WFP_NAME, "Wfl-GetInvoicedAirbillReport");
                WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    SummaryInvoicedAirbillModel summary = context.get(Attributes.SUMMARY_INVOICED_AIRBILL_REPORT);
                    List<InvoicedAirbillModel> invoicedAirbillModels = context.get(Attributes.INVOICED_AIRBILL_REPORT_FULL);
                    IInvoiceHistoryReportRender render = new InvoiceHistoryReportRenderImp(this.getAddInfoMap());
                    InvoiceHistoryColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), InvoiceHistoryColumnFlagsVo.class);
                    render.generateHTML(invoicedAirbillModels, summary, columnFlagsVo, realPath, htmlFilePath);
                    this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                    return "export";
                } else {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                }

            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doExportPDF() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "invoice_history_report.pdf";
                String htmlFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoice_history_report_" + AppUtils.createMessageReference() + ".html";
                String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoice_history_report_" + AppUtils.createMessageReference() + ".pdf";
                InvoicedAirbillFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                filter.setRptTxnId(this.getRptTxnId());
                Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.INVOICED_AIRBILL_REPORT_FILTER, filter);
                context.put(Attributes.NLINKS, nLinks);
                context.put(Attributes.WFP_NAME, "Wfl-GetInvoicedAirbillReport");
                WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    SummaryInvoicedAirbillModel summary = context.get(Attributes.SUMMARY_INVOICED_AIRBILL_REPORT);
                    List<InvoicedAirbillModel> invoicedAirbillModels = context.get(Attributes.INVOICED_AIRBILL_REPORT_FULL);
                    IInvoiceHistoryReportRender render = new InvoiceHistoryReportRenderImp(this.getAddInfoMap());
                    InvoiceHistoryColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), InvoiceHistoryColumnFlagsVo.class);
                    render.generatePDF(invoicedAirbillModels, summary, columnFlagsVo, htmlFilePath, pdfFilePath);
                    this.setStream(new FileInputStream(new File(pdfFilePath)));
                    response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                    return "export";
                } else {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doExportXLS() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "invoice_history_report.xls";
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/invoice_history_report_" + AppUtils.createMessageReference() + ".pdf";
                InvoicedAirbillFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                filter.setRptTxnId(this.getRptTxnId());
                Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
                ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.INVOICED_AIRBILL_REPORT_FILTER, filter);
                context.put(Attributes.NLINKS, nLinks);
                context.put(Attributes.WFP_NAME, "Wfl-GetInvoicedAirbillReport");
                WorkFlowManager2.getInstance().process(context);
                if (!ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                    SummaryInvoicedAirbillModel summary = context.get(Attributes.SUMMARY_INVOICED_AIRBILL_REPORT);
                    List<InvoicedAirbillModel> invoicedAirbillModels = context.get(Attributes.INVOICED_AIRBILL_REPORT_FULL);
                    IInvoiceHistoryReportRender render = new InvoiceHistoryReportRenderImp(this.getAddInfoMap());
                    InvoiceHistoryColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), InvoiceHistoryColumnFlagsVo.class);
                    render.generateXLS(invoicedAirbillModels, summary, columnFlagsVo, outputFilePath);
                    this.setStream(new FileInputStream(new File(outputFilePath)));
                    response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                    return "export";
                } else {
                    throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected InvoicedAirbillFilter buildFilter() throws Exception {
        InvoicedAirbillFilter filter = new InvoicedAirbillFilter();
        // Set rpt txn id.
        filter.setRptTxnId(this.getRptTxnId());
        // Set currency symbol.
        filter.setCurrencySymbol(this.getCurrencySymbol());
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Get search type.
        Integer searchType = null;
        try {
            searchType = Integer.valueOf(this.getSearchType());
        } catch (Exception e) {
            throw new CustomException("Invalid search type.");
        }
        if (searchType < 1 || searchType > 3) {
            throw new CustomException("Invalid search type.");
        }
        filter.setSearchType(searchType);
        // Set start date.
        Date startDate = null;
        try {
            startDate = StringUtils.isBlank(this.getStartDate()) ? null : DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
            throw new CustomException("Invalid start date.");
        }
        if (startDate == null) {
            throw new CustomException("Please choose start date.");
        }
        filter.setStartDate(startDate);
        // Set end date.
        Date endDate = null;
        try {
            endDate = StringUtils.isBlank(this.getEndDate()) ? null : DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
            throw new CustomException("Invalid end date.");
        }
        if (endDate == null) {
            throw new CustomException("Please choose end date.");
        }
        filter.setEndDate(endDate);
        // Check create date range.
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            throw new CustomException("Start date must before End date.");
        }
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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Paging<InvoicedAirbillModel> getReport() {
        return report;
    }

    public void setReport(Paging<InvoicedAirbillModel> report) {
        this.report = report;
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

    public InvoiceHistoryColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(InvoiceHistoryColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
    }

    public SummaryInvoicedAirbillModel getSummary() {
        return summary;
    }

    public void setSummary(SummaryInvoicedAirbillModel summary) {
        this.summary = summary;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }
}
