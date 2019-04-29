package com.gms.xms.weblib.controller.invoicing.invoiceediting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.invoicing.ImportTotalReportModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoicing.IImportTotalReportService;
import com.gms.xms.persistence.service.invoicing.ImportTotalReportServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.importtotals.IImportTotalsReportRender;
import com.gms.xms.workflow.render.importtotals.ImportTotalsReportRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from ImportTotalReportController
 * <p>
 * Author dattrinh Mar 9, 2016 2:26:56 PM
 */
public class ImportTotalReportController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Filter property.
    private String carrier;
    private String importDate;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String searchType;

    // Model.
    private List<String> importDateList;
    private List<ServiceModel> services;
    private Paging<ImportTotalReportModel> report;
    private ImportTotalReportModel summary;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            this.prepareImportDateList();
            this.setPageSizes(this.buildPageSizeList());
            this.prepareServices();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            ImportTotalReportFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            IImportTotalReportService reportService = new ImportTotalReportServiceImp();
            long recordCount = reportService.countImportTotalReportByFilter(filter);
            // Build paging object.
            Paging<ImportTotalReportModel> paging = new Paging<ImportTotalReportModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of import total report.
            List<ImportTotalReportVo> importTotalReportVos = reportService.getImportTotalReportByFilter(filter);
            List<ImportTotalReportModel> importTotalReportModels = ModelUtils.createListModelFromVo(importTotalReportVos, ImportTotalReportModel.class);
            paging.setRecords(importTotalReportModels);
            // Get summary record.
            if (importTotalReportVos != null && importTotalReportVos.size() > 0) {
                ImportTotalReportVo summaryVo = reportService.sumImportTotalReportByFilter(filter);
                ImportTotalReportModel summaryModel = ModelUtils.createModelFromVo(summaryVo, ImportTotalReportModel.class);
                this.setSummary(summaryModel);
            }
            // Set import total report.
            this.setReport(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            ImportTotalReportFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IImportTotalsReportRender render = new ImportTotalsReportRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/import_totals_" + AppUtils.createMessageReference() + ".html";
            render.generateHtmlFile(filter, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportXls() {
        try {
            ImportTotalReportFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IImportTotalsReportRender render = new ImportTotalsReportRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/import_totals_" + AppUtils.createMessageReference() + ".xls";
            render.generateXlsFile(filter, outputFilePath);
            this.setFileName("import_totals_report.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareImportDateList() throws Exception {
        IImportTotalReportService reportService = new ImportTotalReportServiceImp();
        List<Date> importDateList = reportService.getImportDateList();
        List<String> dateList = new ArrayList<String>();
        for (Date date : importDateList) {
            dateList.add(DateUtils.convertDateToString(date, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
        dateList.add(this.getLocalizationText("Custom Date Range"));
        this.setImportDateList(dateList);
    }

    protected void prepareServices() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    protected ImportTotalReportFilter buildFilter() throws Exception {
        ImportTotalReportFilter filter = new ImportTotalReportFilter();
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
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Set carrier.
        Long carrier = null;
        try {
            carrier = StringUtils.isBlank(this.getCarrier()) ? null : Long.valueOf(this.getCarrier());
            if (carrier != null && carrier < 0) {
                throw new CustomException("Carrier cannot be a negative number.");
            }
        } catch (Exception e) {
            throw new CustomException("Carrier must be a integer.");
        }
        filter.setCarrier(carrier);
        // Check search type.
        if (searchType == 0) {
            // Search by import date.
            // Set import date.
            Date importDate = null;
            try {
                importDate = DateUtils.convertStringToDate(this.getImportDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (importDate == null) {
                    throw new CustomException("Please choose import date.");
                }
            } catch (Exception e) {
                throw new CustomException("Invalid import date.");
            }
            filter.setStartDate(importDate);
            filter.setEndDate(importDate);
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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

    public List<String> getImportDateList() {
        return importDateList;
    }

    public void setImportDateList(List<String> importDateList) {
        this.importDateList = importDateList;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public Paging<ImportTotalReportModel> getReport() {
        return report;
    }

    public void setReport(Paging<ImportTotalReportModel> report) {
        this.report = report;
    }

    public ImportTotalReportModel getSummary() {
        return summary;
    }

    public void setSummary(ImportTotalReportModel summary) {
        this.summary = summary;
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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
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