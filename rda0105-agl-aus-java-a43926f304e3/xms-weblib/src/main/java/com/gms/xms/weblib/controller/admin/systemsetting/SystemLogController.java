package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.EventLogFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.EventLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.EventLogServiceImp;
import com.gms.xms.persistence.service.admin.IEventLogService;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.systemlogs.ISystemLogsRender;
import com.gms.xms.workflow.render.systemlogs.SystemLogsRenderImp;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Posted from SystemLogController
 * <p>
 * Author TANDT
 */
public class SystemLogController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private Paging<EventLogModel> eventLogModels;
    private String toDate;
    private String fromDate;
    private String changesMode;
    private String userName;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void doSearch() {
        IEventLogService service = new EventLogServiceImp();
        EventLogFilter filter = new EventLogFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        try {
            validateFilters();
            filter = this.buildFilter();
            long recordCount = service.selectCountListByFilter(filter);

            // Set paging info
            Paging<EventLogModel> paging = new Paging<EventLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            // Get data of page
            List<EventLogVo> eventLogVos = service.selectListByFilter(filter);
            List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
            for (EventLogModel modelN : eventLogModels) {
                if (StringUtils.isNotEmpty(modelN.getChangesMode())) {
                    modelN.setChangesMode(StringUtils.replace(modelN.getChangesMode(), "@,@", "<br/>"));
                }
                if (StringUtils.isNotEmpty(modelN.getFilter())) {
                    modelN.setFilter(StringUtils.replace(modelN.getFilter(), " and ", " and<br/>"));
                }
            }
            paging.setRecords(eventLogModels);
            this.setEventLogModels(paging);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public String doPrint() {
        try {
            IEventLogService service = new EventLogServiceImp();
            EventLogFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            // Get record count to validate max export process records.
            Long recordCount = service.selectCountListByFilter(filter);
            this.validateTotalRecords(recordCount);
            // Get export data.
            List<EventLogVo> eventLogVos = service.selectListByFilter(filter);
            List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
            ISystemLogsRender render = new SystemLogsRenderImp(this.getAddInfoMap());
            String realPath = WebUtils.getContextPathURL(request);
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/print_system_logs_" + AppUtils.createMessageReference() + ".html";
            render.generatePrintHtml(eventLogModels, realPath, outputFilePath);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void validateTotalRecords(Long recordCount) throws CustomException {
        Long maxProcess = Long.valueOf(AppConstants.APP_SETTINGS.getMaxExportProcessRecord());
        if (recordCount > maxProcess) {
            String errMess = "The total records is too large. <br/>Maximum records can be exported is {maxRecord}.";
            throw new CustomException(errMess.replace("{maxRecord}", String.valueOf(maxProcess)));
        }
    }

    public String doExport() {
        try {
            IEventLogService service = new EventLogServiceImp();
            EventLogFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            // Get record count to validate max export process records.
            Long recordCount = service.selectCountListByFilter(filter);
            this.validateTotalRecords(recordCount);
            // Get export data.
            List<EventLogVo> eventLogVos = service.selectListByFilter(filter);
            List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
            for (EventLogModel modelN : eventLogModels) {
                if (StringUtils.isNotEmpty(modelN.getChangesMode())) {
                    modelN.setChangesMode(StringUtils.replace(modelN.getChangesMode(), "@,@", "\n"));
                }
                if (StringUtils.isNotEmpty(modelN.getFilter())) {
                    modelN.setFilter(StringUtils.replace(modelN.getFilter(), " and ", " and\n"));
                }
            }
            ISystemLogsRender render = new SystemLogsRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/export_system_logs_" + AppUtils.createMessageReference() + ".xls";
            render.generateXlSFile(eventLogModels, outputFilePath);
            this.setFileName("system_logs.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private EventLogFilter buildFilter() throws Exception {
        EventLogFilter filter = new EventLogFilter();
        int pSize = 0;
        try {
            pSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int iPage = 0;
        try {
            iPage = Integer.parseInt(this.page);
        } catch (Exception ex) {
            iPage = 1;
        }
        if (!StringUtils.isBlank(changesMode)) {
            filter.setChangesMode(changesMode);
        }
        if (!StringUtils.isBlank(userName)) {
            filter.setUserName(userName);
        }
        if (!StringUtils.isBlank(toDate)) {
            filter.setToDate(DateUtils.convertStringToDate(toDate.concat(" 00:00:00"), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        }
        if (!StringUtils.isBlank(fromDate)) {
            filter.setFromDate(DateUtils.convertStringToDate(fromDate.concat(" 00:00:00"), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        }
        filter.setPage(iPage);
        filter.setPageSize(pSize);
        return filter;
    }

    protected void validateFilters() throws CustomException {
        if (StringUtils.isBlank(toDate) || StringUtils.isBlank(fromDate)) {
            throw new CustomException("Please select end date and start date");
        }
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

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Paging<EventLogModel> getEventLogModels() {
        return eventLogModels;
    }

    public void setEventLogModels(Paging<EventLogModel> eventLogModels) {
        this.eventLogModels = eventLogModels;
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

    public String getChangesMode() {
        return changesMode;
    }

    public void setChangesMode(String changesMode) {
        this.changesMode = changesMode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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