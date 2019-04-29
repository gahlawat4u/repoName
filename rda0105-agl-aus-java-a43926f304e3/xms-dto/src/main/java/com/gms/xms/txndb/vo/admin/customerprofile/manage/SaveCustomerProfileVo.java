package com.gms.xms.txndb.vo.admin.customerprofile.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.SaveCustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;

public class SaveCustomerProfileVo extends BaseVo {
    private static final long serialVersionUID = -500692285254593592L;

    private CustomerProfileVo accountSetup;
    private SaveCustomerProfileBaseRateVo saveCustProfileBaseRate;
    private CustomerProfileVo invoiceOptions;
    private CustomerCollectionVo collection;

    public CustomerProfileVo getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(CustomerProfileVo accountSetup) {
        this.accountSetup = accountSetup;
    }

    public SaveCustomerProfileBaseRateVo getSaveCustProfileBaseRate() {
        return saveCustProfileBaseRate;
    }

    public void setSaveCustProfileBaseRate(SaveCustomerProfileBaseRateVo saveCustProfileBaseRate) {
        this.saveCustProfileBaseRate = saveCustProfileBaseRate;
    }

    public CustomerProfileVo getInvoiceOptions() {
        return invoiceOptions;
    }

    public void setInvoiceOptions(CustomerProfileVo invoiceOptions) {
        this.invoiceOptions = invoiceOptions;
    }

    public CustomerCollectionVo getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionVo collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "SaveCustomerProfileVo [accountSetup=" + accountSetup + ", saveCustProfileBaseRate=" + saveCustProfileBaseRate + ", invoiceOptions=" + invoiceOptions + ", collection=" + collection + "]";
    }
}
