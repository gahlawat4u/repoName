package com.gms.xms.txndb.vo.account.franchises;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseServiceMarkupVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerWebshipVo;
import com.gms.xms.txndb.vo.account.customers.manage.SaveCustomerBaseRateVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;

import java.util.List;

/**
 * Posted from Apr 13, 2016 1:57:21 PM
 * <p>
 * Author huynd
 */
public class SaveManageFranchiseVo extends BaseVo {

    private static final long serialVersionUID = 704271915168611271L;

    private FranchiseVo accountSetup;
    private ManageCustomerAddressVo customerAddress;
    private SaveCustomerBaseRateVo saveFranBaseRate;
    private CustomerCollectionVo collection;
    private CustomerVo customer;
    private FranchiseVo franchise;
    private ManageCustomerBaseRateVo baseRate;
    private FranchiseVo invoiceOption;
    private ManageCustomerWebshipVo webship;
    private List<FranchiseServiceMarkupVo> listServiceMarkup;

    public FranchiseVo getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(FranchiseVo accountSetup) {
        this.accountSetup = accountSetup;
    }

    public ManageCustomerAddressVo getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(ManageCustomerAddressVo customerAddress) {
        this.customerAddress = customerAddress;
    }

    public SaveCustomerBaseRateVo getSaveFranBaseRate() {
        return saveFranBaseRate;
    }

    public void setSaveFranBaseRate(SaveCustomerBaseRateVo saveFranBaseRate) {
        this.saveFranBaseRate = saveFranBaseRate;
    }

    public CustomerCollectionVo getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionVo collection) {
        this.collection = collection;
    }

    public CustomerVo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVo customer) {
        this.customer = customer;
    }

    public FranchiseVo getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseVo franchise) {
        this.franchise = franchise;
    }

    public ManageCustomerBaseRateVo getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(ManageCustomerBaseRateVo baseRate) {
        this.baseRate = baseRate;
    }

    public FranchiseVo getInvoiceOption() {
        return invoiceOption;
    }

    public void setInvoiceOption(FranchiseVo invoiceOption) {
        this.invoiceOption = invoiceOption;
    }

    public ManageCustomerWebshipVo getWebship() {
        return webship;
    }

    public void setWebship(ManageCustomerWebshipVo webship) {
        this.webship = webship;
    }

    public List<FranchiseServiceMarkupVo> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupVo> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
    }

    @Override
    public String toString() {
        return "SaveManageFranchiseVo [accountSetup=" + accountSetup + ", customerAddress=" + customerAddress + ", saveFranBaseRate=" + saveFranBaseRate + ", collection=" + collection + ", customer=" + customer + ", franchise=" + franchise + ", baseRate=" + baseRate + ", invoiceOption=" + invoiceOption + ", webship=" + webship + ", listServiceMarkup=" + listServiceMarkup + "]";
    }
}
