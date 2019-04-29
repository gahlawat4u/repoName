package com.gms.xms.model.account.franchises;

import com.gms.xms.model.*;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;

import java.util.List;

/**
 * Posted from Apr 15, 2016 5:04:38 PM
 * <p>
 * Author huynd
 */
public class AddFranchiseModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private FranchiseModel franchise;
    private CustomerAddressModel address;
    private CustomerBillingAddressModel billingAddress;
    private List<CustomerBaseRateModel> customerBaseRates;
    private CustomerCollectionModel collection;
    private NoteModel note;
    private List<FranchiseServiceMarkupModel> listServiceMarkup;

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public CustomerAddressModel getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressModel address) {
        this.address = address;
    }

    public CustomerBillingAddressModel getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustomerBillingAddressModel billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<CustomerBaseRateModel> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateModel> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel note) {
        this.note = note;
    }

    public List<FranchiseServiceMarkupModel> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupModel> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
    }

    @Override
    public String toString() {
        return "AddFranchiseModel [franchise=" + franchise + ", address=" + address + ", billingAddress=" + billingAddress + ", customerBaseRates=" + customerBaseRates + ", collection=" + collection + ", note=" + note + ", listServiceMarkup=" + listServiceMarkup + "]";
    }

}
