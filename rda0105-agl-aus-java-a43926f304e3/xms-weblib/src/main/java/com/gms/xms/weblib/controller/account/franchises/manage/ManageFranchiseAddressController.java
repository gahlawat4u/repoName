package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * File Name: ManageFranchiseAddressController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 24-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseAddressController
 */
public class ManageFranchiseAddressController extends JsonBaseController {

    private static final long serialVersionUID = 220572068641780994L;
    private String customerCode;
    private ManageCustomerAddressModel customer;
    private List<CountryModel> countries;

    public String show() {
        try {
            prepareData();
            if (loadCustomerAddress()) {
                return "success";
            } else {
                return "error";
            }

        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String save() {
        // Update customer address
        try {
            if (isValidCustomerAddressInfo()) {
                ICustomerService customerService = new CustomerServiceImp();
                ManageCustomerAddressVo customerVo = ModelUtils.createVoFromModel(customer, ManageCustomerAddressVo.class);
                customerService.saveCustomerAddress(this.getAddInfoMap(), customerVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected boolean isValidCustomerAddressInfo() {
        if (customer == null || customer.getAddress() == null || customer.getBillingAddress() == null) {
            addActionError("Invalid customer address and billing customer address");
            return false;
        }
        if (customer.getAddress().getUserType() == null) {
            customer.getAddress().setUserType("0");
        }
        if (StringUtils.isBlank(customer.getAddress().getCustomerName())) {
            addFieldError("customer.address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getContactName())) {
            addFieldError("customer.address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getAddress1())) {
            addFieldError("customer.address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCity())) {
            addFieldError("customer.address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCountry())) {
            addFieldError("customer.address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getAddress().getCountry())) {
                addFieldError("customer.address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(customer.getAddress().getPhone())) {
            addFieldError("customer.address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getAddress().getEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getEmail())) {
                addFieldError("customer.address.email", "Invalid email address.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCustomerName())) {
            addFieldError("customer.billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingContactName())) {
            addFieldError("customer.billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingAddress1())) {
            addFieldError("customer.billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCity())) {
            addFieldError("customer.billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCountry())) {
            addFieldError("customer.billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getBillingAddress().getBillingCountry())) {
                addFieldError("customer.billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingPhone())) {
            addFieldError("customer.billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getBillingAddress().getBillingEmail())) {
            if (!EmailUtils.isValidEmail(customer.getBillingAddress().getBillingEmail())) {
                addFieldError("customer.billingAddress.billingEmail", "Invalid billing email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOwnerEmail())) {
                addFieldError("customer.address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getApEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getApEmail())) {
                addFieldError("customer.address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOtherEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOtherEmail())) {
                addFieldError("customer.address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOther2Email())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOther2Email())) {
                addFieldError("customer.address.other2Email", "Invalid other 2 email address.");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private boolean loadCustomerAddress() throws Exception {
        if (StringUtils.isEmpty(customerCode)) {
            setErrorMessage("Please choice a Franchise.");
            addActionError("Please choice a Franchise.");
            addFieldError("customerCode", "Please choice a Franchise.");
            setErrorCode(ErrorCode.ACTION_ERROR);
        } else {
            ICustomerService customerService = new CustomerServiceImp();
            ManageCustomerAddressVo customerVo = customerService.getManageFranchiseAddressInfo(customerCode);
            ManageCustomerAddressModel customerModel = ModelUtils.createModelFromVo(customerVo, ManageCustomerAddressModel.class);
            this.setCustomer(customerModel);
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void prepareData() throws Exception {
        prepareCountryList();
    }

    private void prepareCountryList() throws Exception {
        ICountryService countryService = new CountryServiceImp();
        List<CountryVo> countryVos = countryService.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        this.setCountries(countryModels);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public ManageCustomerAddressModel getCustomer() {
        return customer;
    }

    public void setCustomer(ManageCustomerAddressModel customer) {
        this.customer = customer;
    }

    public List<CountryModel> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
    }
}
