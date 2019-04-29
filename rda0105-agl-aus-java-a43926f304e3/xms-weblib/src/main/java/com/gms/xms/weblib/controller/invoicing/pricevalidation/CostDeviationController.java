package com.gms.xms.weblib.controller.invoicing.pricevalidation;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.CostDeviationFilter;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.invoicing.CostDeviationModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.invoicing.CostDeviationDao;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.invoicing.CostDeviationVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.costdeviations.CostDeviationsRenderImp;
import com.gms.xms.workflow.render.costdeviations.ICostDeviationsRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from CostDeviationController
 * <p>
 * Author dattrinh Mar 14, 2016 11:43:36 AM
 */
public class CostDeviationController extends AdminJsonBaseController {
    private static final long serialVersionUID = 1537042182659846561L;

    // Filter properties.
    private String franchiseCode;
    private String invoiceDate;
    private String startDate;
    private String endDate;
    private String searchType;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    // Model.
    private List<String> invoiceDates;
    private Paging<CostDeviationModel> report;
    private CostDeviationModel summary;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            this.prepareFranchises();
            this.preparePageSizes();
            this.prepareDateList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            CostDeviationFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            CostDeviationDao dao = new CostDeviationDao();
            long recordCount = dao.countCostDeviationByFilter(filter);
            // Build paging object.
            Paging<CostDeviationModel> paging = new Paging<CostDeviationModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of cost deviation report.
            List<CostDeviationVo> costDeviationVos = dao.getCostDeviationByFilter(filter);
            List<CostDeviationModel> costDeviationModels = ModelUtils.createListModelFromVo(costDeviationVos, CostDeviationModel.class);
            paging.setRecords(costDeviationModels);
            // Get summary record.
            if (costDeviationVos != null && costDeviationVos.size() > 0) {
                CostDeviationVo summaryVo = dao.sumCostDeviationByFilter(filter);
                CostDeviationModel summaryModel = ModelUtils.createModelFromVo(summaryVo, CostDeviationModel.class);
                this.setSummary(summaryModel);
            }
            // Set cost deviation report.
            this.setReport(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            CostDeviationFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ICostDeviationsRender render = new CostDeviationsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/cost_deviations_" + AppUtils.createMessageReference() + ".html";
            String realPath = WebUtils.getContextPathURL(request);
            render.generateHtmlFile(filter, outputFilePath, realPath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportXls() {
        try {
            CostDeviationFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ICostDeviationsRender render = new CostDeviationsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/cost_deviations_" + AppUtils.createMessageReference() + ".xls";
            render.generateXlsFile(filter, outputFilePath);
            this.setFileName("cost_deviations.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected CostDeviationFilter buildFilter() throws Exception {
        CostDeviationFilter filter = new CostDeviationFilter();
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList(this.getFranchiseCode()));
        // Set search type.
        Integer searchType = null;
        try {
            searchType = StringUtils.isBlank(this.getSearchType()) ? null : Integer.valueOf(this.getSearchType());
            if (searchType != 0 && searchType != 1) {
                throw new CustomException("Invalid search type.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid search type.");
        }
        // Check search type.
        if (searchType == 0) {
            // Search by invoice date.
            // Set invoice date.
            Date invoiceDate = null;
            try {
                invoiceDate = DateUtils.convertStringToDate(this.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (invoiceDate == null) {
                    throw new CustomException("Please choose invoice date.");
                }
            } catch (Exception e) {
                throw new CustomException("Invalid invoice date.");
            }
            filter.setStartDate(invoiceDate);
            filter.setEndDate(invoiceDate);
        } else {
            // Search by date range.
            // Set start date.
            Date startDate = null;
            try {
                startDate = DateUtils.convertStringToDate(this.getStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (startDate == null) {
                    throw new CustomException("Please choose start date.");
                }
            } catch (Exception e) {
                throw new CustomException("Invalid start date.");
            }
            filter.setStartDate(startDate);
            // Set end date.
            Date endDate = null;
            try {
                endDate = DateUtils.convertStringToDate(this.getEndDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (endDate == null) {
                    throw new CustomException("Please choose end date.");
                }
            } catch (Exception e) {
                throw new CustomException("Invalid end date.");
            }
            filter.setEndDate(endDate);
            // Check date range.
            if (endDate.before(startDate)) {
                throw new CustomException("Start date must before End date.");
            }
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

    protected void prepareDateList() throws Exception {
        IInvoiceService service = new InvoiceServiceImp();
        ViewEditInvoiceFilter filter = new ViewEditInvoiceFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        List<InvoiceVo> invoiceVos = service.selectAllInvoiceForViewEdit(filter);
        List<String> invoiceDateList = new ArrayList<String>();
        String invoiceDate;
        for (InvoiceVo invoiceVo : invoiceVos) {
            invoiceDate = DateUtils.convertDateToString(invoiceVo.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            invoiceDateList.add(invoiceDate);
        }
        invoiceDateList.add(this.getLocalizationText("Custom Date Range"));
        this.setInvoiceDates(invoiceDateList);
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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
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

    public List<String> getInvoiceDates() {
        return invoiceDates;
    }

    public void setInvoiceDates(List<String> invoiceDates) {
        this.invoiceDates = invoiceDates;
    }

    public Paging<CostDeviationModel> getReport() {
        return report;
    }

    public void setReport(Paging<CostDeviationModel> report) {
        this.report = report;
    }

    public CostDeviationModel getSummary() {
        return summary;
    }

    public void setSummary(CostDeviationModel summary) {
        this.summary = summary;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
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
