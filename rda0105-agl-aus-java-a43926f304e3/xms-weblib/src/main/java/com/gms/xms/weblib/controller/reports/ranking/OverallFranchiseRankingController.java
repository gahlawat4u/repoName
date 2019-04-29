package com.gms.xms.weblib.controller.reports.ranking;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.ranking.OverallFranchiseRankingFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.ranking.OverallFranchiseRankingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.reports.ranking.OverallFranchiseRankingVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.ranking.IOverallFranchiseRankingRender;
import com.gms.xms.workflow.render.report.ranking.OverallFranchiseRankingRenderImp;
import com.gms.xms.workflow.service.report.ranking.IOverallFranchiseRankingService;
import com.gms.xms.workflow.service.report.ranking.OverallFranchiseRankingServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from OverallFranchiseRankingController.java
 * <p>
 * Author dattrinh 9:51:55 AM
 */
public class OverallFranchiseRankingController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<OverallFranchiseRankingModel> rankingReport;
    private String periodType;
    private List<String> dateRange;
    private List<String> pageSizes;
    private String excludeGST;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareDateRange();
            preparePageSizes();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareReportData();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doDateRange() {
        try {
            prepareDateRange();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/overall_franchise_ranking_" + this.rptTxnId + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                OverallFranchiseRankingFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                Boolean excludeGST = this.excludeGST.equalsIgnoreCase("true") ? Boolean.parseBoolean(this.excludeGST) : false;
                IOverallFranchiseRankingRender render = new OverallFranchiseRankingRenderImp(this.getAddInfoMap());
                render.renderOverallFranchiseRankingsHtmlFile(filter, htmlFilePath, realPath, excludeGST);
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
                this.fileName = "overall_franchise_ranking";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.rptTxnId + ".xls";
                OverallFranchiseRankingFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                Boolean excludeGST = this.excludeGST.equalsIgnoreCase("true") ? Boolean.parseBoolean(this.excludeGST) : false;
                IOverallFranchiseRankingRender render = new OverallFranchiseRankingRenderImp(this.getAddInfoMap());
                render.renderOverallFranchiseRankingsXlsFile(filter, outPutFilePath, excludeGST);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    private void prepareReportData() throws Exception {
        IOverallFranchiseRankingService rankingService = new OverallFranchiseRankingServiceImp(this.getAddInfoMap());
        OverallFranchiseRankingFilter filter = this.buildFilter();
        // Prepare for paging
        Long recordCount = rankingService.getOverallFranchiseRankingCount(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<OverallFranchiseRankingModel> paging = new Paging<OverallFranchiseRankingModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the overall franchise ranking report data
        List<OverallFranchiseRankingVo> rankingVos = rankingService.getOverallFranchiseRankingReport(filter);
        List<OverallFranchiseRankingModel> rankingModels = ModelUtils.createListModelFromVo(rankingVos, OverallFranchiseRankingModel.class);
        paging.setRecords(rankingModels);
        // Set overall franchise ranking report data
        this.setRankingReport(paging);
    }

    private OverallFranchiseRankingFilter buildFilter() throws Exception {
        OverallFranchiseRankingFilter filter = new OverallFranchiseRankingFilter();
        // Generate report transaction id if null
        rptTxnId = StringUtils.isBlank(rptTxnId) ? UUID.randomUUID().toString() : rptTxnId;
        filter.setRptTxnId(rptTxnId);
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
        // Default sort field is rank
        orderField = StringUtils.isBlank(orderField) ? "rank" : orderField;
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

    private void prepareDateRange() throws Exception {
        periodType = StringUtils.isBlank(periodType) ? "1" : periodType;
        int type = Integer.valueOf(periodType);
        if (type == 1) {
            this.setDateRange(this.buildWeeklyDateRange());
        } else if (type == 2) {
            this.setDateRange(buildMonthlyDateRange());
        }
    }

    private List<String> buildWeeklyDateRange() {
        List<String> result = new ArrayList<String>();
        // Get current date.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, (8 - calendar.get(Calendar.DAY_OF_WEEK)));
        SimpleDateFormat formatDate = new SimpleDateFormat(AppConstants.APP_SETTINGS.getDefaultDateFormat());
        for (int i = 0; i < 13; i++) {
            String endDate = formatDate.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String startDate = formatDate.format(calendar.getTime());
            String input = startDate + " - " + endDate;
            result.add(input);
            calendar.add(Calendar.DATE, -1);
        }
        return result;
    }

    private List<String> buildMonthlyDateRange() {
        List<String> result = new ArrayList<String>();
        // Get current date.
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatDate = new SimpleDateFormat(AppConstants.APP_SETTINGS.getDefaultDateFormat());
        Date oldTime;
        String startDate;
        String endDate;
        String input;
        // Get first day of the current month
        calendar.add(Calendar.DATE, -calendar.get(Calendar.DAY_OF_MONTH) + 1);
        for (int i = 0; i < 12; i++) {
            oldTime = calendar.getTime();
            // Get first day of the each month
            startDate = formatDate.format(calendar.getTime());
            // Get end day of the each month
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            endDate = formatDate.format(calendar.getTime());
            input = startDate + " - " + endDate;
            result.add(input);
            // Back to previous month
            calendar.setTime(oldTime);
            calendar.add(Calendar.MONTH, -1);
        }
        return result;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
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

    public Paging<OverallFranchiseRankingModel> getRankingReport() {
        return rankingReport;
    }

    public void setRankingReport(Paging<OverallFranchiseRankingModel> rankingReport) {
        this.rankingReport = rankingReport;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public List<String> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<String> dateRange) {
        this.dateRange = dateRange;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
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

    public String getExcludeGST() {
        return excludeGST;
    }

    public void setExcludeGST(String excludeGST) {
        this.excludeGST = excludeGST;
    }
}
