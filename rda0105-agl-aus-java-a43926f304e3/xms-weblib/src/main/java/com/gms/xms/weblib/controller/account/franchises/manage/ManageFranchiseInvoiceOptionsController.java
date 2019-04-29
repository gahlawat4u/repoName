package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.InvoiceTermModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name: ManageFranchiseInvoiceOptionsController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseInvoiceOptionsController
 */
public class ManageFranchiseInvoiceOptionsController extends ManageFranchiseAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;
    protected List<InvoiceSortingOption> invoiceSortingOptions;
    protected List<InvoiceTermModel> invoiceTerms;
    protected List<CustomerModel> invoiceToCustomers;
    protected List<PickupFee> pickupFees;

    public String indexInvoiceOption() {
        try {
            if (this.prepareFranchiseDetail()) {
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

    protected boolean validInvoiceOptions() {
        if (franchise == null) {
            addActionError("Invalid Franchise options");
            return false;
        }
        if (StringUtils.isBlank(franchise.getInvoiceLateFee())) {
            addFieldError("franchise.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(franchise.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("franchise.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("franchise.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
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

    public String save() {
        // Update customer invoice options
        try {
            if (validInvoiceOptions()) {
                FranchiseVo franchiseVo = ModelUtils.createVoFromModel(franchise, FranchiseVo.class);
                IFranchiseService franchiseService = new FranchiseServiceImp();
                franchiseService.update(this.getAddInfoMap(), franchiseVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
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