package com.gms.xms.weblib.controller.account.customers;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.CustomerDetailModel;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.account.customers.SearchCustomerColumnFlagsModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.persistence.service.webshipgroup.IWebshipGroupService;
import com.gms.xms.persistence.service.webshipgroup.WebshipGroupServiceImp;
import com.gms.xms.txndb.vo.CustomerDetailVo;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.customers.SearchCustomerColumnFlagsVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.render.customer.CustomerListRenderImp;
import com.gms.xms.workflow.render.customer.ICustomerListRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from SearchCustomerController
 * <p>
 * Author DatTV Sep 8, 2015
 */
public class SearchCustomerController extends JsonBaseController {

    private static final long serialVersionUID = -987094121341787480L;

    private String franchiseCode;
    private String customerCode;
    private String startDate;
    private String endDate;
    private String dhl;
    private String dhlInbound;
    private String salesRepId;
    private String webshipGroupId;
    private String customerName;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;
    private List<String> pageSizes;
    private List<FranchiseInfoModel> franchises;
    private List<UserModel> saleReps;
    private List<WebshipGroupModel> webshipGroups;
    private Paging<CustomerDetailModel> customers;
    private SearchCustomerColumnFlagsModel columnFlags;

    private InputStream stream;
    private String fileName;

    public String show() {
        try {
            setPage("1");
            setOrderType("0");
            setOrderField("customer_code");
            prepareData();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }

        return "success";
    }

    public String search() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String listSaleReps() {
        try {
            prepareSaleRepList();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String export() {
        try {
            CustomerFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            SearchCustomerColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.columnFlags, SearchCustomerColumnFlagsVo.class);

            this.fileName = "customer_list";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
            ICustomerListRender renderService = new CustomerListRenderImp(this.getAddInfoMap());
            renderService.renderSearchCustomerXLSFile(filter, outPutFilePath, columnFlagsVo);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void doSearch() throws Exception {
        ICustomerService customerService = new CustomerServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        CustomerFilter filter = this.buildFilter();
        long recordCount = customerService.searchCustomersCount(filter);

        // Set paging info
        Paging<CustomerDetailModel> paging = new Paging<CustomerDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());

        // Get data of page
        List<CustomerDetailVo> customerDetailVos = customerService.searchCustomers(filter);
        List<CustomerDetailModel> customerDetailModels = ModelUtils.createListModelFromVo(customerDetailVos, CustomerDetailModel.class);
        paging.setRecords(customerDetailModels);
        this.setCustomers(paging);
    }

    private CustomerFilter buildFilter() throws Exception {
        CustomerFilter filter = new CustomerFilter();
        filter.setFranchiseCodeList(this.buildFranchiseCodeList(franchiseCode));
        Long lCustomerCode = StringUtils.isBlank(customerCode) ? null : Long.valueOf(customerCode);
        Date start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Date end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        Integer iWebshipGroupId = StringUtils.isBlank(webshipGroupId) ? null : Integer.valueOf(webshipGroupId);
        Long lSalesRepId = StringUtils.isBlank(salesRepId) ? null : Long.valueOf(salesRepId);
        filter.setCustomerCode(lCustomerCode);
        filter.setSubmitStartDate(start);
        filter.setSubmitEndDate(end);
        filter.setDhlInternationalAccount(dhl);
        filter.setDhlInboundAccount(dhlInbound);
        filter.setSalesRepId(lSalesRepId);
        filter.setWebshipGroupId(iWebshipGroupId);
        filter.setCustomerName(customerName);
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

    private void prepareData() throws Exception {
        prepareFranchiseList();
        prepareSaleRepList();
        prepareWebshipGroupList();
        pageSizes = this.buildPageSizeList();
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

    private void prepareWebshipGroupList() throws Exception {
        IWebshipGroupService webshipGroupService = new WebshipGroupServiceImp();
        List<WebshipGroupVo> groupVos = webshipGroupService.selectAll();
        List<WebshipGroupModel> groupModels = ModelUtils.createListModelFromVo(groupVos, WebshipGroupModel.class);
        this.setWebshipGroups(groupModels);
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

    public String getDhl() {
        return dhl;
    }

    public void setDhl(String dhl) {
        this.dhl = dhl;
    }

    public String getDhlInbound() {
        return dhlInbound;
    }

    public void setDhlInbound(String dhlInbound) {
        this.dhlInbound = dhlInbound;
    }

    public String getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(String webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<WebshipGroupModel> getWebshipGroups() {
        return webshipGroups;
    }

    public void setWebshipGroups(List<WebshipGroupModel> webshipGroups) {
        this.webshipGroups = webshipGroups;
    }

    public Paging<CustomerDetailModel> getCustomers() {
        return customers;
    }

    public void setCustomers(Paging<CustomerDetailModel> customers) {
        this.customers = customers;
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

    public List<UserModel> getSaleReps() {
        return saleReps;
    }

    public void setSaleReps(List<UserModel> saleReps) {
        this.saleReps = saleReps;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public SearchCustomerColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(SearchCustomerColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
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
