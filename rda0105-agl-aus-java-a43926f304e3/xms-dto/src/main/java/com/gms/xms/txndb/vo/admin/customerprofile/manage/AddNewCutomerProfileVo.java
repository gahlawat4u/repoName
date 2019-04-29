package com.gms.xms.txndb.vo.admin.customerprofile.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.SaveCustomerProfileBaseRateVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;

public class AddNewCutomerProfileVo extends BaseVo {
    private static final long serialVersionUID = 85788423537529239L;

    private CustomerProfileVo customerProfile;
    private SaveCustomerProfileBaseRateVo saveCustProfileBaseRate;
    private CustomerCollectionVo collection;

    public CustomerProfileVo getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(CustomerProfileVo customerProfile) {
        this.customerProfile = customerProfile;
    }

    public SaveCustomerProfileBaseRateVo getSaveCustProfileBaseRate() {
        return saveCustProfileBaseRate;
    }

    public void setSaveCustProfileBaseRate(SaveCustomerProfileBaseRateVo saveCustProfileBaseRate) {
        this.saveCustProfileBaseRate = saveCustProfileBaseRate;
    }

    public CustomerCollectionVo getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionVo collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "AddNewCutomerProfileVo [customerProfile=" + customerProfile + ", saveCustProfileBaseRate=" + saveCustProfileBaseRate + ", collection=" + collection + "]";
    }
}
