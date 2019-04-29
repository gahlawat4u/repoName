package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.account.customers.manage.ManageCustomerInvoiceFilter;
import com.gms.xms.model.CustomerDetailModel;
import com.gms.xms.model.InvoicePaymentModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.customers.manage.ManageCustomerInvoiceDetailModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerInvoiceModel;
import com.gms.xms.model.receivables.customeraging.CustomerAgingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.persistence.service.payment.IPaymentService;
import com.gms.xms.persistence.service.payment.PaymentServiceImp;
import com.gms.xms.txndb.vo.CustomerDetailVo;
import com.gms.xms.txndb.vo.InvoicePaymentVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceDetailVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerInvoiceVo;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.service.customeraging.CustomerAgingServiceImp;
import com.gms.xms.workflow.service.customeraging.ICustomerAgingService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from CustomerPaymentsController
 * <p>
 * Author DatTV Sep 23, 2015
 */
public class CustomerPaymentsController extends JsonBaseController {

    private static final long serialVersionUID = 4547500751474184995L;

    private String invoiceCode;
    private String customerCode;
    private String filterType;
    private CustomerAgingModel aging;
    private CustomerDetailModel customerDetail;
    private Paging<ManageCustomerInvoiceModel> invoices;
    private ManageCustomerInvoiceDetailModel invoiceDetail;
    private List<InvoicePaymentModel> payments;
    private List<String> pageSizes;
    private String page;
    private String pageSize;
    private String orderBy;

    public String show() {
        try {
            preparePageSizes();
            if (StringUtils.isBlank(customerCode)) {
                setErrorMessage("Please enter franchise name");
                setErrorCode(ErrorCode.ACTION_ERROR);
                addActionError("Please enter franchise name");
                return "error";
            }
            loadCustomerAging();
            loadCustomerDetail();
            loadInvoices();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String searchInvoices() {
        try {
            loadInvoices();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showInvoiceDetail() {
        try {
            loadInvoiceDetail();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showPayments() {
        try {
            loadPayments();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void loadCustomerAging() throws Exception {
        ICustomerAgingService agingService = new CustomerAgingServiceImp(this.getAddInfoMap());
        CustomerAgingVo agingVo = agingService.selectByCustomerCode(customerCode);
        CustomerAgingModel agingModel = ModelUtils.createModelFromVo(agingVo, CustomerAgingModel.class);
        this.setAging(agingModel);
    }

    private void loadCustomerDetail() throws Exception {
        ICustomerService customerService = new CustomerServiceImp();
        CustomerDetailVo detailVo = customerService.getCustomerDetailByCode(customerCode);
        CustomerDetailModel detailModel = ModelUtils.createModelFromVo(detailVo, CustomerDetailModel.class);
        this.setCustomerDetail(detailModel);
    }

    private void loadInvoices() throws Exception {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
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

        ManageCustomerInvoiceFilter filter = this.buildFilter();
        long recordCount = invoiceService.getCustomerInvoiceCount(filter);

        // Set paging info
        Paging<ManageCustomerInvoiceModel> paging = new Paging<ManageCustomerInvoiceModel>(iPage, nLinks, pSize, recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());

        // Get data of page
        List<ManageCustomerInvoiceVo> invoiceVos = invoiceService.getCustomerInvoices(filter);
        for (ManageCustomerInvoiceVo invoice : invoiceVos) {
            Double cusTotal = invoice.getTotal() == null ? 0.0 : invoice.getTotal();
            Double cusLateFee = invoice.getLateFee() == null ? 0.0 : invoice.getLateFee();
            invoice.setTotalCost(cusTotal + cusLateFee);
        }
        List<ManageCustomerInvoiceModel> invoiceModels = ModelUtils.createListModelFromVo(invoiceVos, ManageCustomerInvoiceModel.class);
        paging.setRecords(invoiceModels);
        this.setInvoices(paging);
    }

    private void loadInvoiceDetail() throws Exception {
        if (StringUtils.isBlank(invoiceCode)) {
            throw new CustomException("No invoice code");
        }
        IInvoiceService invoiceService = new InvoiceServiceImp();
        ManageCustomerInvoiceDetailVo detailVo = invoiceService.getByInvoiceDetailCode(invoiceCode);
        detailVo.setTotalPaidCredit(detailVo.getCustomerPaid() + detailVo.getCreditTotal());
        ManageCustomerInvoiceDetailModel detailModel = ModelUtils.createModelFromVo(detailVo, ManageCustomerInvoiceDetailModel.class);
        this.setInvoiceDetail(detailModel);
    }

    private void loadPayments() throws Exception {
        if (StringUtils.isBlank(invoiceCode)) {
            throw new CustomException("No invoice code");
        }
        IPaymentService paymentService = new PaymentServiceImp();
        List<InvoicePaymentVo> paymentVos = paymentService.selectByInvoiceCode(invoiceCode);
        List<InvoicePaymentModel> paymentModels = ModelUtils.createListModelFromVo(paymentVos, InvoicePaymentModel.class);
        this.setPayments(paymentModels);
    }

    private ManageCustomerInvoiceFilter buildFilter() {
        ManageCustomerInvoiceFilter filter = new ManageCustomerInvoiceFilter();
        Integer type = StringUtils.isBlank(filterType) ? null : Integer.valueOf(filterType);
        filter.setFilterType(type);
        filter.setCustomerCode(customerCode);
        return filter;
    }

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerAgingModel getAging() {
        return aging;
    }

    public void setAging(CustomerAgingModel aging) {
        this.aging = aging;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public CustomerDetailModel getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetailModel customerDetail) {
        this.customerDetail = customerDetail;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Paging<ManageCustomerInvoiceModel> getInvoices() {
        return invoices;
    }

    public void setInvoices(Paging<ManageCustomerInvoiceModel> invoices) {
        this.invoices = invoices;
    }

    public ManageCustomerInvoiceDetailModel getInvoiceDetail() {
        return invoiceDetail;
    }

    public void setInvoiceDetail(ManageCustomerInvoiceDetailModel invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public List<InvoicePaymentModel> getPayments() {
        return payments;
    }

    public void setPayments(List<InvoicePaymentModel> payments) {
        this.payments = payments;
    }
}
