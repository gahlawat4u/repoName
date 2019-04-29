package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.customer.CustomerCollectionModel;

/**
 * Posted from Apr 13, 2016 10:11:00 AM
 * <p>
 * Author dattrinh
 */
public class SaveManageCustomerModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private CustomerAccountSetupModel accountSetup;
    private ManageCustomerAddressModel customerAddress;
    private SaveCustomerBaseRateModel saveCustBaseRate;
    private CustomerModel invoiceOption;
    private CustomerCollectionModel collection;
    private ManageCustomerWebshipModel webship;

    public CustomerAccountSetupModel getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(CustomerAccountSetupModel accountSetup) {
        this.accountSetup = accountSetup;
    }

    public ManageCustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(ManageCustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }

    public SaveCustomerBaseRateModel getSaveCustBaseRate() {
        return saveCustBaseRate;
    }

    public void setSaveCustBaseRate(SaveCustomerBaseRateModel saveCustBaseRate) {
        this.saveCustBaseRate = saveCustBaseRate;
    }

    public CustomerModel getInvoiceOption() {
        return invoiceOption;
    }

    public void setInvoiceOption(CustomerModel invoiceOption) {
        this.invoiceOption = invoiceOption;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    public ManageCustomerWebshipModel getWebship() {
        return webship;
    }

    public void setWebship(ManageCustomerWebshipModel webship) {
        this.webship = webship;
    }

    @Override
    public String toString() {
        return "SaveManageCustomerModel [accountSetup=" + accountSetup + ", customerAddress=" + customerAddress + ", saveCustBaseRate=" + saveCustBaseRate + ", invoiceOption=" + invoiceOption + ", collection=" + collection + ", webship=" + webship + "]";
    }
}
