package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Posted from Apr 14, 2016 10:06:11 AM
 * <p>
 * Author dattrinh
 */
public class AddCustomerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private CustomerVo customer;
    private CustomerAddressVo address;
    private CustomerBillingAddressVo billingAddress;
    private List<CustomerBaseRateVo> customerBaseRates;
    private CustomerCollectionVo collection;
    private NoteVo note;
    @JsonIgnore
    private WebshipVo webship;
    @JsonIgnore
    private List<AccountServiceVo> accountServices;

    public CustomerVo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVo customer) {
        this.customer = customer;
    }

    public CustomerAddressVo getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressVo address) {
        this.address = address;
    }

    public CustomerBillingAddressVo getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustomerBillingAddressVo billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<CustomerBaseRateVo> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateVo> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    public CustomerCollectionVo getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionVo collection) {
        this.collection = collection;
    }

    public NoteVo getNote() {
        return note;
    }

    public void setNote(NoteVo note) {
        this.note = note;
    }

    public WebshipVo getWebship() {
        return webship;
    }

    public void setWebship(WebshipVo webship) {
        this.webship = webship;
    }

    public List<AccountServiceVo> getAccountServices() {
        return accountServices;
    }

    public void setAccountServices(List<AccountServiceVo> accountServices) {
        this.accountServices = accountServices;
    }

    @Override
    public String toString() {
        return "AddCustomerVo [customer=" + customer + ", address=" + address + ", billingAddress=" + billingAddress + ", customerBaseRates=" + customerBaseRates + ", collection=" + collection + ", note=" + note + ", webship=" + webship + ", accountServices=" + accountServices + "]";
    }
}
