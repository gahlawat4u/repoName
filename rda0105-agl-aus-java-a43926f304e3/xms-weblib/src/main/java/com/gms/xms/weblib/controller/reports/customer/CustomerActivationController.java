package com.gms.xms.weblib.controller.reports.customer;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.customer.activation.CustomerActivationModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.customer.activation.CustomerActivationRenderImp;
import com.gms.xms.workflow.render.report.customer.activation.ICustomerActivationRender;
import com.gms.xms.workflow.service.report.customer.activation.CustomerActivationServiceImp;
import com.gms.xms.workflow.service.report.customer.activation.ICustomerActivationService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Posted from CustomerActivationController.java
 * <p>
 * Author dattrinh 12:01:20 PM
 */
public class CustomerActivationController extends JsonBaseController {

    private static final long serialVersionUID = 7170449525936756884L;

    private String rptTxnId;
    private String startDate;
    private String endDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<CustomerActivationModel> activationReport;
    private List<String> pageSizes;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
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
            prepareActivationReport();
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
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_list_" + this.rptTxnId + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerActivationFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerActivationRender render = new CustomerActivationRenderImp(this.getAddInfoMap());
                render.renderCustomerActivationHtmlFile(filter, htmlFilePath, realPath);
                this.htmlExportString = AppUtils.readUTF8File2String(htmlFilePath);
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String export() {
        try {
            this.fileName = "customer_activation";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
            CustomerActivationFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ICustomerActivationRender render = new CustomerActivationRenderImp(this.getAddInfoMap());
            render.renderCustomerActivationXlsFile(filter, outPutFilePath);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void preparePageSizes() {
        setPageSizes(this.buildPageSizeList());
    }

    private String buildFranchiseCodeList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);

        String franchiseList = "";
        for (FranchiseInfoVo franchise : franchiseInfoVos) {
            franchiseList += franchise.getCode() + ",";
        }
        franchiseList = franchiseList.substring(0, franchiseList.length() - 1);

        return franchiseList;
    }

    private CustomerActivationFilter buildFilter() throws Exception {
        CustomerActivationFilter filter = new CustomerActivationFilter();
        if (StringUtils.isBlank(rptTxnId)) {
            rptTxnId = UUID.randomUUID().toString();
        }
        filter.setRptTxnId(rptTxnId);
        filter.setFranchiseList(this.buildFranchiseCodeList());
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

    private void prepareActivationReport() throws Exception {
        ICustomerActivationService activationService = new CustomerActivationServiceImp(this.getAddInfoMap());
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        CustomerActivationFilter filter = this.buildFilter();
        Long recordCount = activationService.getActivationCount(filter);
        Paging<CustomerActivationModel> paging = new Paging<CustomerActivationModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        List<CustomerActivationVo> activationVos = activationService.getActivationReport(filter);
        List<CustomerActivationModel> activationModels = ModelUtils.createListModelFromVo(activationVos, CustomerActivationModel.class);
        paging.setRecords(activationModels);
        this.setActivationReport(paging);
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
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

    public Paging<CustomerActivationModel> getActivationReport() {
        return activationReport;
    }

    public void setActivationReport(Paging<CustomerActivationModel> activationReport) {
        this.activationReport = activationReport;
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

}