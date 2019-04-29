package com.gms.xms.model.account.franchises;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.FranchiseServiceMarkupModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerBaseRateModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerWebshipModel;
import com.gms.xms.model.account.customers.manage.SaveCustomerBaseRateModel;
import com.gms.xms.model.customer.CustomerCollectionModel;

import java.util.List;

/**
 * Posted from Apr 13, 2016 1:57:21 PM
 * <p>
 * Author huynd
 */
public class SaveManageFranchiseModel extends BaseModel {

    private static final long serialVersionUID = 704271915168611271L;

    private FranchiseModel accountSetup;
    private ManageCustomerAddressModel customerAddress;
    private SaveCustomerBaseRateModel saveFranBaseRate;
    private CustomerCollectionModel collection;
    private CustomerModel customer;
    private FranchiseModel franchise;
    private ManageCustomerBaseRateModel baseRate;
    private FranchiseModel invoiceOption;
    private ManageCustomerWebshipModel webship;
    private List<FranchiseServiceMarkupModel> listServiceMarkup;

    public FranchiseModel getAccountSetup() {
        return accountSetup;
    }

    public void setAccountSetup(FranchiseModel accountSetup) {
        this.accountSetup = accountSetup;
    }

    public ManageCustomerAddressModel getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(ManageCustomerAddressModel customerAddress) {
        this.customerAddress = customerAddress;
    }

    public SaveCustomerBaseRateModel getSaveFranBaseRate() {
        return saveFranBaseRate;
    }

    public void setSaveFranBaseRate(SaveCustomerBaseRateModel saveFranBaseRate) {
        this.saveFranBaseRate = saveFranBaseRate;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public ManageCustomerBaseRateModel getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(ManageCustomerBaseRateModel baseRate) {
        this.baseRate = baseRate;
    }

    public FranchiseModel getInvoiceOption() {
        return invoiceOption;
    }

    public void setInvoiceOption(FranchiseModel invoiceOption) {
        this.invoiceOption = invoiceOption;
    }

    public ManageCustomerWebshipModel getWebship() {
        return webship;
    }

    public void setWebship(ManageCustomerWebshipModel webship) {
        this.webship = webship;
    }

    public List<FranchiseServiceMarkupModel> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupModel> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
    }

    @Override
    public String toString() {
        return "SaveManageFranchiseModel [accountSetup=" + accountSetup + ", customerAddress=" + customerAddress + ", saveFranBaseRate=" + saveFranBaseRate + ", collection=" + collection + ", customer=" + customer + ", franchise=" + franchise + ", baseRate=" + baseRate + ", invoiceOption=" + invoiceOption + ", webship=" + webship + ", listServiceMarkup=" + listServiceMarkup + "]";
    }

}
