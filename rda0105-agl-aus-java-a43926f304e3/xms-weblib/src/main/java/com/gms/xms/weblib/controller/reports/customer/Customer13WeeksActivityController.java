package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.customer.thirdteenweeks.CustomerWeekActivityModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.report.customer.thirdteenweeks.CustomerWeekActivityServiceImp;
import com.gms.xms.persistence.service.report.customer.thirdteenweeks.ICustomerWeekActivityService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.reports.customer.thirdteenweeks.CustomerWeekActivityVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.customer.thirdteenweeks.CustomerWeekActivityRenderImp;
import com.gms.xms.workflow.render.report.customer.thirdteenweeks.ICustomerWeekActivityRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Posted from Customer13WeeksActivityController.java
 * <p>
 * Author dattrinh 5:00:36 PM
 */
public class Customer13WeeksActivityController extends JsonBaseController {
    private static final long serialVersionUID = -1752874371694580504L;

    private String franchiseCode;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<CustomerWeekActivityModel> weekActivityReport;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;
    private String totalRecord;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
        /*if (determineUserLevel() > 2) {
		throw new Exception(this.getLocalizationText("You don't have permission to do this action"));
	    }*/
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
	    /*if (determineUserLevel() > 2) {
		throw new Exception(this.getLocalizationText("You don't have permission to do this action"));
	    }*/
            prepareWeekActivityReport();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String print() {
        try {
	    /*if (determineUserLevel() > 2) {
		throw new Exception(this.getLocalizationText("You don't have permission to do this action"));
	    }*/
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_13week_activity" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerWeekActivityFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerWeekActivityRender render = new CustomerWeekActivityRenderImp(this.getAddInfoMap());
                render.renderCustomerWeekActivityHtmlFile(filter, htmlFilePath, realPath);
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
	    /*if (determineUserLevel() > 2) {
		throw new Exception(this.getLocalizationText("You don't have permission to do this action"));
	    }*/
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "customer_13week_activity";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                CustomerWeekActivityFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerWeekActivityRender render = new CustomerWeekActivityRenderImp(this.getAddInfoMap());
                render.renderCustomerWeekActivityXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    /*private Integer determineUserLevel() throws Exception {
	ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
	Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
	Double userLevel = client.getUserLevel(userLevelId);
	return userLevel.intValue();
    }*/

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
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

    private CustomerWeekActivityFilter buildFilter() throws Exception {
        CustomerWeekActivityFilter filter = new CustomerWeekActivityFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Default sort field is period
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 1 (descending)
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

    private void prepareWeekActivityReport() throws Exception {
        ICustomerWeekActivityService activityService = new CustomerWeekActivityServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        CustomerWeekActivityFilter filter = this.buildFilter();
        Long recordCount = activityService.getWeekActivityCount(filter);
        NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultIntNumberFormat());
        this.setTotalRecord(formatter.format(recordCount));
        Paging<CustomerWeekActivityModel> paging = new Paging<CustomerWeekActivityModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<CustomerWeekActivityVo> activationVos = activityService.getWeekActivityReport(filter);
        List<CustomerWeekActivityModel> activationModels = ModelUtils.createListModelFromVo(activationVos, CustomerWeekActivityModel.class);
        paging.setRecords(activationModels);
        this.setWeekActivityReport(paging);
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    public Paging<CustomerWeekActivityModel> getWeekActivityReport() {
        return weekActivityReport;
    }

    public void setWeekActivityReport(Paging<CustomerWeekActivityModel> weekActivityReport) {
        this.weekActivityReport = weekActivityReport;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public String getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(String totalRecord) {
        this.totalRecord = totalRecord;
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