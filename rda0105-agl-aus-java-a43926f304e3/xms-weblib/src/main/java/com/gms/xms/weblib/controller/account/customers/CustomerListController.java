package com.gms.xms.weblib.controller.account.customers;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.account.customers.CustomerListEntryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.customers.CustomerListEntryVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.customer.CustomerListRenderImp;
import com.gms.xms.workflow.render.customer.ICustomerListRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerListController
 * <p>
 * Author DatTV Sep 8, 2015
 */
public class CustomerListController extends JsonBaseController {

    private static final long serialVersionUID = -2731895645307440660L;

    // Filter properties.
    private String orderType;
    private String orderField;
    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private Paging<CustomerListEntryModel> customers;
    private List<FranchiseInfoModel> franchises;
    private String franchiseCode;
    private String salesRepId;
    private List<UserModel> saleReps;

    private String htmlExportString;
    private InputStream stream;
    private String fileName;

    public String show() {
        try {
            pageSizes = this.buildPageSizeList();
            prepareFranchiseList();
            prepareSaleRepList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            doSearch();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    public String listSaleReps() {
        try {
            prepareSaleRepList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/customer_list_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                CustomerFilter filter = this.buildFilter();
                // Set page and page size is null to get all records.
                filter.setPage(null);
                filter.setPageSize(null);
                ICustomerListRender renderService = new CustomerListRenderImp(this.getAddInfoMap());
                renderService.renderCustomerListHtmlFile(filter, htmlFilePath, realPath);
                this.htmlExportString = AppUtils.readUTF8File2String(htmlFilePath);
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String export() {
        try {
            this.fileName = "customer_list";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
            CustomerFilter filter = this.buildFilter();
            // Set page and page size is null to get all records.
            filter.setPage(null);
            filter.setPageSize(null);
            ICustomerListRender renderService = new CustomerListRenderImp(this.getAddInfoMap());
            renderService.renderCustomerListXlsFile(filter, outPutFilePath);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void doSearch() throws Exception {
        ICustomerService customerService = new CustomerServiceImp();
        CustomerFilter filter = this.buildFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        long recordCount = customerService.countCustomerList(filter);
        // Set paging info
        Paging<CustomerListEntryModel> paging = new Paging<CustomerListEntryModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get data of page
        List<CustomerListEntryVo> customerDetailVos = customerService.selectCustomerList(filter);
        List<CustomerListEntryModel> customerDetailModels = ModelUtils.createListModelFromVo(customerDetailVos, CustomerListEntryModel.class);
        paging.setRecords(customerDetailModels);
        this.setCustomers(paging);
    }

    private CustomerFilter buildFilter() throws Exception {
        CustomerFilter filter = new CustomerFilter();
        filter.setFranchiseCodeList(this.buildFranchiseCodeList(franchiseCode));
        filter.setSalesRepId(StringUtils.isBlank(salesRepId) ? null : Long.valueOf(salesRepId));
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "customer_code" : this.getOrderField());
        return filter;
    }

    private List<String> buildFranchiseCodeList(String franCode) throws Exception {
        List<String> franchiseCodeList = new ArrayList<String>();
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            if (franchises == null) {
                prepareFranchiseList();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseCodeList.add(franchise.getCode());
            }
        } else {
            franchiseCodeList.add(franCode);
        }

        return franchiseCodeList;
    }

    private void prepareFranchiseList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    private void prepareSaleRepList() throws Exception {
        IUserService userService = new UserServiceImp();
        List<UserVo> userVos = userService.getSaleReps(this.buildFranchiseCodeList(franchiseCode));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSaleReps(userModels);
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

    public Paging<CustomerListEntryModel> getCustomers() {
        return customers;
    }

    public void setCustomers(Paging<CustomerListEntryModel> customers) {
        this.customers = customers;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public List<UserModel> getSaleReps() {
        return saleReps;
    }

    public void setSaleReps(List<UserModel> saleReps) {
        this.saleReps = saleReps;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
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
}
