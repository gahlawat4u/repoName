package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.*;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;

import java.util.List;

/**
 * Posted from Apr 14, 2016 10:06:11 AM
 * <p>
 * Author dattrinh
 */
public class AddCustomerModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private CustomerModel customer;
    private CustomerAddressModel address;
    private CustomerBillingAddressModel billingAddress;
    private List<CustomerBaseRateModel> customerBaseRates;
    private CustomerCollectionModel collection;
    private NoteModel note;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "AddCustomerModel [customer=" + customer + ", address=" + address + ", billingAddress=" + billingAddress + ", customerBaseRates=" + customerBaseRates + ", collection=" + collection + ", note=" + note + "]";
    }
}
