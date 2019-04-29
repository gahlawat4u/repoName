package com.gms.xms.weblib.controller.franchisepayable;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.franchisepayable.FranchisePayablePeriodModel;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.FranchisePayableClient;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Posted from FranchisePayableController.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:51:47 PM
 */
public class FranchisePayableController extends JsonBaseController {

    private static final long serialVersionUID = 1L;
    private Integer page = 0;
    private Integer recordCount = 0;
    private Integer pageCount = 0;
    private String startDate;
    private String endDate;
    private String rptTxnId;
    private String franchiseCode;
    private Integer startRecord = 0;
    private Integer recordSize = 10;
    private String franchiseName;
    private List<FranchiseInfoModel> franchiseList;
    private List<String> dateRange;
    private String frozenMessage;
    private FranchiseInfoModel franchise;
    private String filename;
    private InputStream stream;
    private String htmlContents;
    private List<FranchisePayablePeriodModel> periodList;

    /**
     * build date range for franchise payable report
     *
     * @return - list of date range
     */
    protected List<String> buildDateRange() {
        List<String> result = new ArrayList<String>();

        // Get current date.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, (8 - calendar.get(Calendar.DAY_OF_WEEK)));

        SimpleDateFormat formatDate = new SimpleDateFormat(AppConstants.APP_SETTINGS.getDefaultDateFormat());
        // Date fpbStartDate =
        // DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(),
        // AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        // Date start;

        for (int i = 0; i < 10; i++) {
            String endDate = formatDate.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String startDate = formatDate.format(calendar.getTime());

            // // Not include start date with date before franchise payable
            // configuration date
            // start = DateUtils.convertStringToDate(startDate,
            // AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            //
            // // Check start date not less then franchise payable start date in
            // configuration file
            // if (start.before(fpbStartDate) || start.equals(fpbStartDate)) {
            // continue;
            // }

            String input = startDate + " - " + endDate;
            result.add(input);
            calendar.add(Calendar.DATE, -1);
        }

        result.add("Custom date range");

        return result;
    }

    protected List<String> buildSCDateRange() {
        List<String> result = new ArrayList<String>();

        // Get current date.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, (8 - calendar.get(Calendar.DAY_OF_WEEK)));

        SimpleDateFormat formatDate = new SimpleDateFormat(AppConstants.APP_SETTINGS.getDefaultDateFormat());
        // Date fpbStartDate =
        // DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(),
        // AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        // Date start;
        // String preStartDate = "";
        // String preEndDate = "";
        // Integer periodCount = 0;
        for (int i = 0; i < 10; i++) {
            String endDate = formatDate.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String startDate = formatDate.format(calendar.getTime());

            // // Not include start date with date before franchise payable
            // configuration date
            // start = DateUtils.convertStringToDate(startDate,
            // AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            //
            // // Check start date not less then franchise payable start date in
            // configuration file
            // if (start.before(fpbStartDate) || start.equals(fpbStartDate)) {
            // continue;
            // }

            String input = startDate + " - " + endDate;
            result.add(input);
            calendar.add(Calendar.DATE, -1);
            // if (i == 1) {
            // preEndDate = endDate;
            // }
            // if (i % 4 == 0 && i != 1) {
            // preStartDate = startDate;
            // if (periodCount > 0) {
            // result.add(i - 3,preStartDate + " - " + preEndDate);
            // periodCount ++;
            // }else{
            // result.add(0,preStartDate + " - " + preEndDate);
            // periodCount ++;
            // }
            //
            // preEndDate = formatDate.format(calendar.getTime());
            // }
        }

        result.add("Custom date range");

        return result;
    }

    /**
     * determine frozen message
     *
     * @param context - context
     */
    protected void determineFrozenMessage(ContextBase context) {
        String message = "";
        if (AppConstants.YES_FLAG.equalsIgnoreCase(context.get(Attributes.FRANCHISE_PAYABLE_IS_FROZEN))) {
            message = AppConstants.FRANCHISE_PAYABLE_FROZEN_MESSAGE;
        }
        this.setFrozenMessage(message);
    }

    /**
     * build a franchise list by user login information
     *
     * @throws Exception - on error
     */
    @SuppressWarnings("unchecked")
    protected void buildFranchiseList() throws Exception {
        // Get franchise list managed by logged user
        FranchisePayableClient payableClient = new FranchisePayableClient(this.getAddInfoMap());
        Map<String, Object> resultMap = payableClient.getFranchiseListManagedByUser((String) this.getSession("SESS_XMS_ADMIN_ID"));
        List<FranchiseInfoModel> franchiseList = (List<FranchiseInfoModel>) resultMap.get(Attributes.RESULT);
        this.setFranchiseList(franchiseList);

        // Get franchise name
        this.setFranchiseName("");
        for (FranchiseInfoModel fran : this.getFranchiseList()) {
            if (fran.getCode().equalsIgnoreCase(this.getFranchiseCode())) {
                this.setFranchiseName(fran.getName());
                break;
            }
        }
    }

    /**
     * prepare data for filter
     *
     * @throws Exception
     */
    protected void prepareDataForFilter() throws Exception {
        this.setDateRange(buildDateRange());
        buildFranchiseList();
    }

    protected void prepareDataForFilterSC() throws Exception {
        this.setDateRange(buildSCDateRange());
        buildFranchiseList();
    }

    /**
     * build franchise payable filter
     *
     * @return - FranchisePayableFilter
     * @throws Exception - on error
     */
    protected FranchisePayableFilter buildFilter() throws Exception {
        // Get configuration franchise payable start date
        Date fpbStartDate = DateUtils.convertStringToDate(AppConstants.APP_SETTINGS.getFranchisePayableStartDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Date start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Date end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);

        // Check end date not greater than start date
        if (start.after(end)) {
            throw new Exception("Start date must be before end date");
        }

        // Check start date not less then franchise payable start date in
        // configuration file
        if (start.before(fpbStartDate) || start.equals(fpbStartDate)) {
            throw new Exception("No support for report before " + DateUtils.convertDateToString(fpbStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }

        if (franchiseList == null) {
            buildFranchiseList();
        }
        FranchisePayableFilter filter = new FranchisePayableFilter();
        if (page == null || page < 0) {
            page = 1;
        }
        if (recordSize == null || recordSize <= 0) {
            recordSize = 25;
        }

        filter.setStartDate(start);
        filter.setEndDate(DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        filter.setFranchiseCodeList(buildFranchiseCodeList());

        int startRecord = (page - 1) * recordSize;
        filter.setStartRecord(startRecord);
        filter.setRecordSize(recordSize);
        filter.setRptTxnId(rptTxnId);

        return filter;
    }

    /**
     * build a franchise code list
     *
     * @return - list of franchise list
     */
    protected List<String> buildFranchiseCodeList() {
        List<String> franchiseCodeList = new ArrayList<String>();
        if (StringUtils.isBlank(franchiseCode)) {
            for (FranchiseInfoModel franchise : franchiseList) {
                franchiseCodeList.add(franchise.getCode());
            }
        } else {
            franchiseCodeList.add(franchiseCode);
        }

        return franchiseCodeList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public List<FranchiseInfoModel> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<FranchiseInfoModel> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public List<String> getDateRange() {
        return dateRange;
    }

    public void setDateRange(List<String> dateRange) {
        this.dateRange = dateRange;
    }

    public String getFrozenMessage() {
        return frozenMessage;
    }

    public void setFrozenMessage(String frozenMessage) {
        this.frozenMessage = frozenMessage;
    }

    public FranchiseInfoModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseInfoModel franchise) {
        this.franchise = franchise;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlContents() {
        return htmlContents;
    }

    public void setHtmlContents(String htmlContents) {
        this.htmlContents = htmlContents;
    }

    public List<FranchisePayablePeriodModel> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<FranchisePayablePeriodModel> periodList) {
        this.periodList = periodList;
    }

    public boolean getEnableNonCentralizedTab() {
        boolean enable = true;
        try {
            enable = Boolean.parseBoolean(AppConstants.APP_SETTINGS.getEnableNonCentralizedTab());
        } catch (Exception e) {
            enable = false;
        }
        return enable;
    }
}