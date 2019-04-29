package com.gms.xms.weblib.controller.reports.ranking;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.ranking.SalesRepRankingFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.ranking.SalesRepRankingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.reports.ranking.SalesRepRankingVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.ranking.ISalesRepRankingRender;
import com.gms.xms.workflow.render.report.ranking.SalesRepRankingRenderImp;
import com.gms.xms.workflow.service.report.ranking.ISalesRepRankingService;
import com.gms.xms.workflow.service.report.ranking.SalesRepRankingServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from SalesRepRankingController.java
 * <p>
 * Author dattrinh 11:20:41 AM
 */
public class SalesRepRankingController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String franchiseCode;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<SalesRepRankingModel> rankingReport;
    private String periodType;
    private List<String> dateRange;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;
    private String excludeGST;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareDateRange();
            preparePageSizes();
            prepareFranchises();
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
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/sale_rep_rankings_" + this.rptTxnId + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                SalesRepRankingFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                Boolean excludeGST = this.excludeGST.equalsIgnoreCase("true") ? Boolean.parseBoolean(this.excludeGST) : false;
                ISalesRepRankingRender render = new SalesRepRankingRenderImp(this.getAddInfoMap());
                render.renderSaleRepsRankingHtmlFile(filter, htmlFilePath, realPath, excludeGST);
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
                this.fileName = "sales_rep_ranking";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.rptTxnId + ".xls";
                SalesRepRankingFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                Boolean excludeGST = this.excludeGST.equalsIgnoreCase("true") ? Boolean.parseBoolean(this.excludeGST) : false;
                ISalesRepRankingRender render = new SalesRepRankingRenderImp(this.getAddInfoMap());
                render.renderSaleRepsRankingXlsFile(filter, outPutFilePath, excludeGST);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
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

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    private void prepareReportData() throws Exception {
        ISalesRepRankingService rankingService = new SalesRepRankingServiceImp(this.getAddInfoMap());
        SalesRepRankingFilter filter = this.buildFilter();
        // Prepare for paging
        Long recordCount = rankingService.getSalesRepRankingCount(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<SalesRepRankingModel> paging = new Paging<SalesRepRankingModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the sales rep ranking report data
        List<SalesRepRankingVo> rankingVos = rankingService.getSalesRepRankingReport(filter);
        List<SalesRepRankingModel> rankingModels = ModelUtils.createListModelFromVo(rankingVos, SalesRepRankingModel.class);
        paging.setRecords(rankingModels);
        // Set the sales rep ranking report data
        this.setRankingReport(paging);
    }

    private SalesRepRankingFilter buildFilter() throws Exception {
        SalesRepRankingFilter filter = new SalesRepRankingFilter();
        // Generate report transaction id if null
        rptTxnId = StringUtils.isBlank(rptTxnId) ? UUID.randomUUID().toString() : rptTxnId;
        filter.setRptTxnId(rptTxnId);
        // Set franchise list
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

    public Paging<SalesRepRankingModel> getRankingReport() {
        return rankingReport;
    }

    public void setRankingReport(Paging<SalesRepRankingModel> rankingReport) {
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

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public String getExcludeGST() {
        return excludeGST;
    }

    public void setExcludeGST(String excludeGST) {
        this.excludeGST = excludeGST;
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
