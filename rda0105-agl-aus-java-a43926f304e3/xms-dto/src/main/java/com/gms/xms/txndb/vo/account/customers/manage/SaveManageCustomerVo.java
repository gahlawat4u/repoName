package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

/**
 * Posted from Apr 13, 2016 10:11:00 AM
 * <p>
 * Author dattrinh
 */
public class SaveManageCustomerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private CustomerAccountSetupVo accountSetup;
    private ManageCustomerAddressVo customerAddress;
    private SaveCustomerBaseRateVo saveCustBaseRate;
    private CustomerVo invoiceOption;
    private CustomerCollectionVo collection;
    private ManageCustomerWebshipVo webship;

    public CustomerAccountSetupVo getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(CustomerAccountSetupVo accountSetup) {
        this.accountSetup = accountSetup;
    }

    public ManageCustomerAddressVo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(ManageCustomerAddressVo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public SaveCustomerBaseRateVo getSaveCustBaseRate() {
        return saveCustBaseRate;
    }

    public void setSaveCustBaseRate(SaveCustomerBaseRateVo saveCustBaseRate) {
        this.saveCustBaseRate = saveCustBaseRate;
    }

    public CustomerVo getInvoiceOption() {
        return invoiceOption;
    }

    public void setInvoiceOption(CustomerVo invoiceOption) {
        this.invoiceOption = invoiceOption;
    }

    public CustomerCollectionVo getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionVo collection) {
        this.collection = collection;
    }

    public ManageCustomerWebshipVo getWebship() {
        return webship;
    }

    public void setWebship(ManageCustomerWebshipVo webship) {
        this.webship = webship;
    }

    @Override
    public String toString() {
        return "SaveManageCustomerVo [accountSetup=" + accountSetup + ", customerAddress=" + customerAddress + ", saveCustBaseRate=" + saveCustBaseRate + ", invoiceOption=" + invoiceOption + ", collection=" + collection + ", webship=" + webship + "]";
    }
}
