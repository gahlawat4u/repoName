package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.InvoiceTermModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.InvoiceSortingOption;
import com.gms.xms.txndb.vo.InvoiceTermVo;
import com.gms.xms.txndb.vo.PickupFee;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerInvoiceOptionsController
 * <p>
 * Author DatTV Sep 22, 2015
 */
public class CustomerInvoiceOptionsController extends JsonBaseController {

    private static final long serialVersionUID = -1443989244060568432L;

    private String customerCode;
    private CustomerModel customer;
    private List<InvoiceSortingOption> invoiceSortingOptions;
    private List<InvoiceTermModel> invoiceTerms;
    private List<CustomerModel> invoiceToCustomers;
    private List<PickupFee> pickupFees;

    public String show() {
        try {
            prepareData();
            loadInvoiceOptions();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String save() {
        // Update customer invoice options
        try {
            if (validInvoiceOptions()) {
                CustomerVo customerVo = ModelUtils.createVoFromModel(customer, CustomerVo.class);
                ICustomerService customerService = new CustomerServiceImp();
                customerService.updateCustomer(this.getAddInfoMap(), customerVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        // Load data
        try {
            prepareData();
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
        }
        return "success";
    }

    private boolean validInvoiceOptions() {
        if (customer == null) {
            addActionError("Invalid customer invoice options");
            return false;
        }
        if (StringUtils.isBlank(customer.getInvoiceLateFee())) {
            addFieldError("customer.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(customer.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("customer.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("customer.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void prepareData() throws Exception {
        prepareInvoiceSortingOptions();
        prepareInvoiceTerms();
        prepareInvoiceToCustomers();
        preparePickupFees();
    }

    private void loadInvoiceOptions() throws Exception {
        if (StringUtils.isBlank(customerCode)) {
            throw new Exception("No customer code");
        }
        ICustomerService customerService = new CustomerServiceImp();
        CustomerVo customerVo = customerService.selectByCode(customerCode);
        CustomerModel customerModel = ModelUtils.createModelFromVo(customerVo, CustomerModel.class);
        this.setCustomer(customerModel);
    }

    private void prepareInvoiceSortingOptions() {
        List<InvoiceSortingOption> options = new ArrayList<InvoiceSortingOption>();
        options.add(new InvoiceSortingOption(0, "Sort by ship date"));
        options.add(new InvoiceSortingOption(1, "Sort by reference code"));
        options.add(new InvoiceSortingOption(2, "Sort by Freight"));
        options.add(new InvoiceSortingOption(3, "Sort by customer"));
        this.setInvoiceSortingOptions(options);
    }

    private void preparePickupFees() {
        List<PickupFee> options = new ArrayList<PickupFee>();
        options.add(new PickupFee(0, ""));
        options.add(new PickupFee(1, "On Demand"));
        options.add(new PickupFee(2, "Regular Stop"));
        options.add(new PickupFee(3, "Drop Box"));
        this.setPickupFees(options);
    }

    private void prepareInvoiceTerms() throws Exception {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<InvoiceTermVo> termVos = invoiceService.selectInvoiceTerms();
        List<InvoiceTermModel> termModels = ModelUtils.createListModelFromVo(termVos, InvoiceTermModel.class);
        this.setInvoiceTerms(termModels);
    }

    private void prepareInvoiceToCustomers() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ICustomerService customerService = new CustomerServiceImp();
        List<CustomerVo> customerVos = customerService.selectByLogin(userId);
        List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
        CustomerModel customerModel;
        for (CustomerVo customerVo : customerVos) {
            customerModel = new CustomerModel();
            customerModel.setCustomerCode(String.valueOf(customerVo.getCustomerCode()));
            customerModels.add(customerModel);
        }
        this.setInvoiceToCustomers(customerModels);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public List<InvoiceSortingOption> getInvoiceSortingOptions() {
        return invoiceSortingOptions;
    }

    public void setInvoiceSortingOptions(List<InvoiceSortingOption> invoiceSortingOptions) {
        this.invoiceSortingOptions = invoiceSortingOptions;
    }

    public List<InvoiceTermModel> getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(List<InvoiceTermModel> invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public List<CustomerModel> getInvoiceToCustomers() {
        return invoiceToCustomers;
    }

    public void setInvoiceToCustomers(List<CustomerModel> invoiceToCustomers) {
        this.invoiceToCustomers = invoiceToCustomers;
    }

    public List<PickupFee> getPickupFees() {
        return pickupFees;
    }

    public void setPickupFees(List<PickupFee> pickupFees) {
        this.pickupFees = pickupFees;
    }
}
