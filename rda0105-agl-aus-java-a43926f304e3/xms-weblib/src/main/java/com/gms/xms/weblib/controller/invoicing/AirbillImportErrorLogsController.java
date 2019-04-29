package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.invoicing.DuplicateAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.airbillimporterrorlogs.AirbillImportErrorsLogRenderImp;
import com.gms.xms.workflow.render.airbillimporterrorlogs.IAirbillImportErrorLogsRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * File Name: AirbillErrorLogController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.invoicing <br/>
 * Class: AirbillErrorLogController
 */
public class AirbillImportErrorLogsController extends AdminJsonBaseController {

    private static final long serialVersionUID = -8616665327612839037L;

    private String toDate;
    private String fromDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String useCustomDate;

    private Paging<DuplicateAirbillModel> duplicateAirbills;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            preparePageSizes();
            // Set default order field and type.
            this.setOrderField("airbill_number");
            this.setOrderType("0");
            this.setPage("1");
            // Get airbill import error log list.
            prepareAirbillList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            prepareAirbillList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportHtml() {
        try {
            DuplicateAirbillFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IAirbillImportErrorLogsRender render = new AirbillImportErrorsLogRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/airbill_import_error_logs_" + AppUtils.createMessageReference() + ".html";
            render.generateHtmlFile(filter, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportXls() {
        try {
            DuplicateAirbillFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IAirbillImportErrorLogsRender render = new AirbillImportErrorsLogRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/airbill_import_error_logs_" + AppUtils.createMessageReference() + ".xls";
            render.generateXlsFile(filter, outputFilePath);
            this.setFileName("airbill_import_error_logs.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void prepareAirbillList() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IInvoiceService service = new InvoiceServiceImp();
        DuplicateAirbillFilter filter = this.buildFilter();
        long recordCount = service.countDuplicateAirbillByFilter(filter);
        // Get paging info.
        Paging<DuplicateAirbillModel> paging = new Paging<DuplicateAirbillModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<DuplicateAirbillVo> duplicateAirbillVos = service.getDuplicateAirbillByFilter(filter);
        List<DuplicateAirbillModel> duplicateAirbillModels = ModelUtils.createListModelFromVo(duplicateAirbillVos, DuplicateAirbillModel.class);
        // Update note of each record.
        String note;
        for (DuplicateAirbillModel duplicateAirbillModel : duplicateAirbillModels) {
            note = this.getLocalizationText("Error: Duplicate airbill" + " " + duplicateAirbillModel.getAirbillNumber() + ". " + this.getLocalizationText("Will not import."));
            duplicateAirbillModel.setNote(note);
        }
        paging.setRecords(duplicateAirbillModels);
        this.setDuplicateAirbills(paging);
    }

    private DuplicateAirbillFilter buildFilter() throws Exception {
        DuplicateAirbillFilter filter = new DuplicateAirbillFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        int searchType = 0;
        try {
            searchType = Integer.valueOf(this.getUseCustomDate());
        } catch (Exception e) {
        }
        Date fromDate;
        Date toDate;
        if (searchType == 0) {
            Calendar cal = Calendar.getInstance();
            toDate = cal.getTime();
            cal.add(Calendar.DATE, -90);
            fromDate = cal.getTime();
            filter.setFromDate(fromDate);
            filter.setToDate(toDate);
        } else if (searchType == 1) {
            // Get from date.
            try {
                fromDate = StringUtils.isBlank(this.getFromDate()) ? null : DateUtils.convertStringToDate(this.getFromDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            } catch (Exception e) {
                throw new CustomException("Invalid start date.");
            }
            if (fromDate == null) {
                throw new CustomException("Please choose start date.");
            }
            filter.setFromDate(fromDate);
            // Get to date.
            try {
                toDate = StringUtils.isBlank(this.getToDate()) ? null : DateUtils.convertStringToDate(this.getToDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            } catch (Exception e) {
                throw new CustomException("Invalid end date.");
            }
            if (toDate == null) {
                throw new CustomException("Please choose end date.");
            }
            // Check date range.
            if (toDate.before(fromDate)) {
                throw new CustomException("Start date must before End date.");
            }
            filter.setToDate(toDate);
        } else {
            throw new CustomException("Unknown search type.");
        }
        // Default sort field is airbill number
        String field = this.getOrderField();
        field = StringUtils.isBlank(field) ? "airbill_number" : field;
        filter.setOrderField(field);
        // Default sort type is 0 (ascending)
        String order = this.getOrderType();
        order = StringUtils.isBlank(order) ? "0" : order;
        filter.setOrderType(Integer.valueOf(order));
        // Set default page
        String p = this.getPage();
        p = StringUtils.isBlank(p) ? "0" : p;
        filter.setPage(Integer.valueOf(p));
        // Set default page size
        String ps = this.getPageSize();
        ps = StringUtils.isBlank(ps) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : ps;
        filter.setPageSize(Integer.valueOf(ps));
        return filter;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public Paging<DuplicateAirbillModel> getDuplicateAirbills() {
        return duplicateAirbills;
    }

    public void setDuplicateAirbills(Paging<DuplicateAirbillModel> duplicateAirbills) {
        this.duplicateAirbills = duplicateAirbills;
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

    public String getUseCustomDate() {
        return useCustomDate;
    }

    public void setUseCustomDate(String useCustomDate) {
        this.useCustomDate = useCustomDate;
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
