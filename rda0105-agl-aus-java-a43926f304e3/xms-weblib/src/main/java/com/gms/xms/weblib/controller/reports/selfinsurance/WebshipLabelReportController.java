package com.gms.xms.weblib.controller.reports.selfinsurance;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.selfinsurance.WebshipLabelFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;
import com.gms.xms.txndb.model.reports.selfinsurance.WebshipLabelColumnFlagsModel;
import com.gms.xms.txndb.model.reports.selfinsurance.WebshipLabelModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.WebshipLabelColumnFlagsVo;
import com.gms.xms.txndb.vo.reports.selfinsurance.WebshipLabelVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.webshiplabel.IWebshipLabelReportRender;
import com.gms.xms.workflow.render.report.webshiplabel.WebshipLabelReportRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from WebshipLabelReportController
 * <p>
 * Author dattrinh Mar 18, 2016 4:33:00 PM
 */
public class WebshipLabelReportController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1537042182659846561L;

    // Filter properties.
    private String searchType;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    // Model.
    private Paging<WebshipLabelModel> report;
    private WebshipLabelColumnFlagsModel columnFlags;

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
            WebshipLabelFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            SelfInsuranceDao dao = new SelfInsuranceDao();
            long recordCount = dao.countWebshipLabelReport(filter);
            // Build paging object.
            Paging<WebshipLabelModel> paging = new Paging<WebshipLabelModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of webship labels report.
            List<WebshipLabelVo> webshipLabelVos = dao.getWebshipLabelReport(filter);
            List<WebshipLabelModel> webshipLabelModels = ModelUtils.createListModelFromVo(webshipLabelVos, WebshipLabelModel.class);
            paging.setRecords(webshipLabelModels);
            // Set webship labels report.
            this.setReport(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/webship_history_report_" + AppUtils.createMessageReference() + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                WebshipLabelFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                SelfInsuranceDao dao = new SelfInsuranceDao();
                List<WebshipLabelVo> webshipLabelVos = dao.getWebshipLabelReport(filter);
                List<WebshipLabelModel> webshipLabelModels = ModelUtils.createListModelFromVo(webshipLabelVos, WebshipLabelModel.class);
                IWebshipLabelReportRender render = new WebshipLabelReportRenderImp(this.getAddInfoMap());
                WebshipLabelColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), WebshipLabelColumnFlagsVo.class);
                render.generateHTML(webshipLabelModels, columnFlagsVo, realPath, htmlFilePath);
                this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doExportPDF() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "webship_history_report.pdf";
                String htmlFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/webship_history_report_" + AppUtils.createMessageReference() + ".html";
                String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/webship_history_report_" + AppUtils.createMessageReference() + ".pdf";
                WebshipLabelFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                SelfInsuranceDao dao = new SelfInsuranceDao();
                List<WebshipLabelVo> webshipLabelVos = dao.getWebshipLabelReport(filter);
                List<WebshipLabelModel> webshipLabelModels = ModelUtils.createListModelFromVo(webshipLabelVos, WebshipLabelModel.class);
                IWebshipLabelReportRender render = new WebshipLabelReportRenderImp(this.getAddInfoMap());
                WebshipLabelColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), WebshipLabelColumnFlagsVo.class);
                render.generatePDF(webshipLabelModels, columnFlagsVo, htmlFilePath, pdfFilePath);
                this.setStream(new FileInputStream(new File(pdfFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String doExportXLS() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "webship_history_report.xls";
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/webship_history_report_" + AppUtils.createMessageReference() + ".pdf";
                WebshipLabelFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                SelfInsuranceDao dao = new SelfInsuranceDao();
                List<WebshipLabelVo> webshipLabelVos = dao.getWebshipLabelReport(filter);
                List<WebshipLabelModel> webshipLabelModels = ModelUtils.createListModelFromVo(webshipLabelVos, WebshipLabelModel.class);
                IWebshipLabelReportRender render = new WebshipLabelReportRenderImp(this.getAddInfoMap());
                WebshipLabelColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.getColumnFlags(), WebshipLabelColumnFlagsVo.class);
                render.generateXLS(webshipLabelModels, columnFlagsVo, outputFilePath);
                this.setStream(new FileInputStream(new File(outputFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected WebshipLabelFilter buildFilter() throws Exception {
        WebshipLabelFilter filter = new WebshipLabelFilter();
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Get search type.
        Integer searchType = null;
        try {
            searchType = Integer.valueOf(this.getSearchType());
        } catch (Exception e) {
            throw new CustomException("Invalid search type.");
        }
        if (searchType > 3 || searchType < 1) {
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

    public Paging<WebshipLabelModel> getReport() {
        return report;
    }

    public void setReport(Paging<WebshipLabelModel> report) {
        this.report = report;
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

    public WebshipLabelColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(WebshipLabelColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
    }
}
