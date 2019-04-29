package com.gms.xms.txndb.vo.account.franchises;

import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

/**
 * Posted from Apr 15, 2016 5:06:00 PM
 * <p>
 * Author huynd
 */
public class AddFranchiseVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private FranchiseVo franchise;
    private CustomerAddressVo address;
    private CustomerBillingAddressVo billingAddress;
    private List<CustomerBaseRateVo> customerBaseRates;
    private CustomerCollectionVo collection;
    private NoteVo note;
    private List<FranchiseServiceMarkupVo> listServiceMarkup;
    @JsonIgnore
    private WebshipVo webship;
    @JsonIgnore
    private List<AccountServiceVo> accountServices;

    public FranchiseVo getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseVo franchise) {
        this.franchise = franchise;
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

    public List<FranchiseServiceMarkupVo> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupVo> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
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
        return "AddFranchiseVo [franchise=" + franchise + ", address=" + address + ", billingAddress=" + billingAddress + ", customerBaseRates=" + customerBaseRates + ", collection=" + collection + ", note=" + note + ", listServiceMarkup=" + listServiceMarkup + ", webship=" + webship + ", accountServices=" + accountServices + "]";
    }
}
