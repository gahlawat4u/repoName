package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.InvoiceTermModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.InvoiceSortingOption;
import com.gms.xms.txndb.vo.InvoiceTermVo;
import com.gms.xms.txndb.vo.PickupFee;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerProfilesController
 * <p>
 * Author TANDT 21-10-2015
 */
public class CustomerProfilesInvoiceOptionsController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;
    protected List<InvoiceSortingOption> invoiceSortingOptions;
    protected List<InvoiceTermModel> invoiceTerms;
    protected List<CustomerModel> invoiceToCustomers;
    protected List<PickupFee> pickupFees;
    protected String customerCode;
    protected CustomerModel customer;

    public String indexInvoiceOption() {
        try {
            if (this.preapareCustomerDetail()) {
                prepareDataInvoiceOption();
                return "success";
            } else {
                return "error";
            }

        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected void prepareDataInvoiceOption() throws Exception {
        prepareInvoiceSortingOptions();
        prepareInvoiceTerms();
        prepareInvoiceToCustomers();
        preparePickupFees();
    }

    public String save() {
        // Update customer invoice options
        try {
            if (validInvoiceOptions()) {
                CustomerProfileVo customerProfileVoN = ModelUtils.createVoFromModel(cusProfile, CustomerProfileVo.class);
                ICustomerProfileService service = new CustomerProfileServiceImp();
                service.updateCustomerProfiles(this.getAddInfoMap(), customerProfileVoN);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected boolean validInvoiceOptions() {
        if (cusProfile == null) {
            addActionError("Invalid customer invoice options");
            return false;
        }
        if (StringUtils.isBlank(cusProfile.getInvoiceLateFee())) {
            addFieldError("customer.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(cusProfile.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void loadInvoiceOptions() throws Exception {
        if (StringUtils.isBlank(customerCode)) {
            throw new Exception("No customer code");
        }
        ICustomerService customerService = new CustomerServiceImp();
        CustomerVo customerVo = customerService.selectByCode(customerCode);
        CustomerModel customerModel = ModelUtils.createModelFromVo(customerVo, CustomerModel.class);
        this.setCustomer(customerModel);
    }

    protected void prepareInvoiceSortingOptions() {
        List<InvoiceSortingOption> options = new ArrayList<InvoiceSortingOption>();
        options.add(new InvoiceSortingOption(0, "Sort by ship date"));
        options.add(new InvoiceSortingOption(1, "Sort by reference code"));
        options.add(new InvoiceSortingOption(2, "Sort by Freight"));
        options.add(new InvoiceSortingOption(3, "Sort by customer"));
        this.setInvoiceSortingOptions(options);
    }

    protected void preparePickupFees() {
        List<PickupFee> options = new ArrayList<PickupFee>();
        options.add(new PickupFee(0, ""));
        options.add(new PickupFee(1, "On Demand"));
        options.add(new PickupFee(2, "Regular Stop"));
        options.add(new PickupFee(3, "Drop Box"));
        this.setPickupFees(options);
    }

    protected void prepareInvoiceTerms() throws Exception {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<InvoiceTermVo> termVos = invoiceService.selectInvoiceTerms();
        List<InvoiceTermModel> termModels = ModelUtils.createListModelFromVo(termVos, InvoiceTermModel.class);
        for (InvoiceTermModel model : termModels) {
            model.setDays(model.getDays().concat(" days"));
        }
        this.setInvoiceTerms(termModels);
    }

    protected void prepareInvoiceToCustomers() throws Exception {
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

}