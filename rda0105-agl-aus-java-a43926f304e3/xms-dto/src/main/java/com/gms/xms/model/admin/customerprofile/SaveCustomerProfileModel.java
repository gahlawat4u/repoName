package com.gms.xms.model.admin.customerprofile;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.SaveCustomerProfileBaseRateModel;

public class SaveCustomerProfileModel extends BaseModel {
    private static final long serialVersionUID = 8762205137760636592L;
    private CustomerProfileModel accountSetup;
    private SaveCustomerProfileBaseRateModel saveCustProfileBaseRate;
    private CustomerProfileModel invoiceOptions;
    private CustomerCollectionModel collection;

    public CustomerProfileModel getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(CustomerProfileModel accountSetup) {
        this.accountSetup = accountSetup;
    }

    public SaveCustomerProfileBaseRateModel getSaveCustProfileBaseRate() {
        return saveCustProfileBaseRate;
    }

    public void setSaveCustProfileBaseRate(SaveCustomerProfileBaseRateModel saveCustProfileBaseRate) {
        this.saveCustProfileBaseRate = saveCustProfileBaseRate;
    }

    public CustomerProfileModel getInvoiceOptions() {
        return invoiceOptions;
    }

    public void setInvoiceOptions(CustomerProfileModel invoiceOptions) {
        this.invoiceOptions = invoiceOptions;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "SaveCustomerProfileModel [accountSetup=" + accountSetup + ", saveCustProfileBaseRate=" + saveCustProfileBaseRate + ", invoiceOptions=" + invoiceOptions + ", collection=" + collection + "]";
    }
}
