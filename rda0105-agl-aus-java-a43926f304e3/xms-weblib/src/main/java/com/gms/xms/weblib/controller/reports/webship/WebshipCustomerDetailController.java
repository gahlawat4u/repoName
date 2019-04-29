package com.gms.xms.weblib.controller.reports.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.reports.webship.WebshipCustomerDetailModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.report.webship.IWebshipCustomerDetailService;
import com.gms.xms.persistence.service.report.webship.WebshipCustomerDetailServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.reports.webship.WebshipCustomerDetailVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.webship.IWebshipCustomerDetailRender;
import com.gms.xms.workflow.render.report.webship.WebshipCustomerDetailRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from WebshipCustomerDetailController
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private String franchiseCode;
    private String customerCode;
    private String serviceId;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<WebshipCustomerDetailModel> report;
    private List<CustomerModel> customers;
    private List<ServiceModel> services;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    public String show() {
        try {
            prepareFranchises();
            preparePageSizes();
            prepareCustomers(null);
            prepareServices();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareReportData();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String doCustomers() {
        try {
            prepareCustomers(this.getFranchiseCode());
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/webship_customer_detail_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                WebshipCustomerDetailFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IWebshipCustomerDetailRender render = new WebshipCustomerDetailRenderImp(this.getAddInfoMap());
                render.renderWebshipCustomerDetailHtmlFile(filter, htmlFilePath, realPath);
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
                this.fileName = "webship_customer_detail";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                WebshipCustomerDetailFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IWebshipCustomerDetailRender render = new WebshipCustomerDetailRenderImp(this.getAddInfoMap());
                render.renderWebshipCustomerDetailXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void prepareReportData() throws Exception {
        IWebshipCustomerDetailService customerDetailService = new WebshipCustomerDetailServiceImp();
        WebshipCustomerDetailFilter filter = this.buildFilter();
        // Prepare for paging
        Long recordCount = customerDetailService.countWebshipCustomerDetailReport(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<WebshipCustomerDetailModel> paging = new Paging<WebshipCustomerDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the webship customer detail report data
        List<WebshipCustomerDetailVo> detailVos = customerDetailService.getWebshipCustomerDetailReport(filter);
        List<WebshipCustomerDetailModel> detailModels = ModelUtils.createListModelFromVo(detailVos, WebshipCustomerDetailModel.class);
        paging.setRecords(detailModels);
        // Set the webship customer detail report data
        this.setReport(paging);
    }

    private void prepareCustomers(String franchiseCode) throws Exception {
        if ("All".equalsIgnoreCase(franchiseCode) || StringUtils.isBlank(franchiseCode)) {
            List<CustomerModel> customerList = new ArrayList<CustomerModel>();
            this.setCustomers(customerList);
        } else {
            ICustomerService customerService = new CustomerServiceImp();
            List<CustomerVo> customerVos = customerService.getCustomersByFranchise(franchiseCode);
            List<CustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, CustomerModel.class);
            this.setCustomers(customerModels);
        }
    }

    private void prepareServices() throws Exception {
        IServiceService service = new ServiceServiceImp();
        List<ServiceVo> serviceVos = service.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    private WebshipCustomerDetailFilter buildFilter() throws Exception {
        WebshipCustomerDetailFilter filter = new WebshipCustomerDetailFilter();
        // Set franchise code
        franchiseCode = StringUtils.isBlank(franchiseCode) ? "All" : franchiseCode;
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set customer code
        filter.setCustomerCode(customerCode);
        // Set service id (carrier)
        serviceId = StringUtils.isBlank(serviceId) ? null : serviceId;
        Integer carrier = serviceId == null ? null : Integer.valueOf(serviceId);
        filter.setServiceId(carrier);
        // Set start date
        if (StringUtils.isBlank(startDate)) {
            throw new Exception("Please choose the start date");
        }
        Date start = null;
        Date end = null;
        try {
            start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (start == null) {
            throw new Exception("Invalid start date format");
        }
        // Set end date
        if (StringUtils.isBlank(endDate)) {
            throw new Exception("Please choose the end date");
        }
        try {
            end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (end == null) {
            throw new Exception("Invalid end date format");
        }
        // The end date must be after start date
        if (end.before(start)) {
            throw new Exception("The start date must be before the end date");
        }
        filter.setStartDate(start);
        filter.setEndDate(end);
        // Default sort field is customer_code
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 0 (ascending)
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        // Set default page
        page = StringUtils.isBlank(page) ? "0" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));
        return filter;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public Paging<WebshipCustomerDetailModel> getReport() {
        return report;
    }

    public void setReport(Paging<WebshipCustomerDetailModel> report) {
        this.report = report;
    }

    public List<CustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerModel> customers) {
        this.customers = customers;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
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
